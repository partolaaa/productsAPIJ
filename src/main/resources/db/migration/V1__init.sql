create table products
(
    id            uuid primary key,
    created       timestamp    not null,
    updated       timestamp    not null,
    name          varchar(255) not null,
    description   text         not null,
    price         numeric      not null,
    resource_link text         not null,
    category_id   uuid         not null,
    user_id       uuid         not null
);

create table categories
(
    id          uuid primary key,
    created     timestamp    not null,
    updated     timestamp    not null,
    name        varchar(255) not null,
    description text         not null
);

create table transactions
(
    id         uuid primary key,
    created    timestamp not null,
    updated    timestamp not null,
    tx_id      bigint    not null,
    buyer_id   uuid      not null,
    seller_id  uuid      not null,
    product_id uuid      not null
);

create table users
(
    id        uuid primary key,
    created   timestamp    not null,
    updated   timestamp    not null,
    wallet_id text         not null,
    username  varchar(255) not null
);

-- TODO: add relations and indexes, verify types, add constraints if necessary
