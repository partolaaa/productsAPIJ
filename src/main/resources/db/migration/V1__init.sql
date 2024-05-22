drop table if exists products cascade;
drop table if exists categories cascade;
drop table if exists transactions cascade;
drop table if exists users cascade;

create table categories
(
    id          uuid primary key,
    name        varchar(255) not null,
    description text         not null
);

create table users
(
    id        uuid primary key,
    created   timestamp    not null,
    updated   timestamp    not null,
    wallet_id text         not null,
    username  varchar(255) not null
);

create table products
(
    id            uuid primary key,
    name          varchar(255) not null,
    description   text         not null,
    price         numeric      not null,
    resource_link text         not null,
    category_id   uuid         not null,
    user_id       uuid         not null,
    datetime      timestamp    not null,
    created       timestamp    not null,
    updated       timestamp    not null,
    foreign key (category_id) references categories (id),
    foreign key (user_id) references users (id)
);

create table transactions
(
    id         uuid primary key,
    created    timestamp not null,
    updated    timestamp not null,
    tx_id      text      not null,
    buyer_id   uuid      not null,
    seller_id  uuid      not null,
    product_id uuid      not null,
    foreign key (seller_id) references users (id),
    foreign key (buyer_id) references users (id),
    foreign key (product_id) references products (id)
);

-- Indices and additional constraints
create index idx_product_user_id on products (user_id);
create index idx_product_category_id on products (category_id);
create index idx_transaction_seller_id on transactions (seller_id);
create index idx_transaction_buyer_id on transactions (buyer_id);
create index idx_transaction_product_id on transactions (product_id);

-- Inserting data into categories
insert into categories(id, name, description)
values ('cd0052ab-9972-436f-a3e3-282276d64a95', 'Coupon', 'Discounts or benefits provided through vouchers or codes during purchases');

insert into categories(id, name, description)
values ('d4c55e41-b766-4dce-bca6-79bab042ef14', 'Intellectual Property', 'Patents, copyrights, trademarks, and other intellectual rights');

insert into categories(id, name, description)
values ('8b2adba3-90b2-4dde-80ed-9a0e1fb3bf7c', 'Assets', 'Digital assets like images, sounds, videos, or graphics');

insert into categories(id, name, description)
values ('8e65d3da-bc37-408c-b1d8-380d212be4b1', 'Source Code', 'Code for various projects and applications, ready for use or modification');
