package com.partola.productsapij.repository.specification;

import static java.util.Optional.ofNullable;

import com.partola.productsapij.model.criteria.ProductCriteria;
import com.partola.productsapij.model.entity.Product;
import com.partola.productsapij.model.entity.Product.Fields;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;

@RequiredArgsConstructor(staticName = "of")
public class ProductSpecification implements Specification<Product> {

    private final ProductCriteria criteria;

    @Override
    public Predicate toPredicate(
            @NotNull Root<Product> root,
            @NotNull CriteriaQuery<?> query,
            @NotNull CriteriaBuilder criteriaBuilder) {
        final Predicate[] predicates = Stream.of(
                        getNamePredicate(root, criteriaBuilder),
                        getCategoriesPredicate(root))
                .flatMap(Optional::stream)
                .toArray(Predicate[]::new);
        return criteriaBuilder.and(predicates);
    }

    private Optional<Predicate> getNamePredicate(Root<Product> root, CriteriaBuilder criteriaBuilder) {
        return ofNullable(criteria.name())
                .filter(StringUtils::isNotBlank)
                .map(name -> {
                    final String lowercaseName = "%" + name.toLowerCase() + "%";
                    return criteriaBuilder.like(criteriaBuilder.lower(root.get(Fields.name)), lowercaseName);
                });
    }

    private Optional<Predicate> getCategoriesPredicate(Root<Product> root) {
        return ofNullable(criteria.categoryIds()).map(ids -> root.get(Fields.categoryId).in(ids));
    }
}
