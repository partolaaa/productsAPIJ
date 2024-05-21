package com.partola.productsapij.model.criteria;

import java.util.List;
import java.util.UUID;

public record ProductCriteria(
        String name,
        List<UUID> categoryIds) {

}
