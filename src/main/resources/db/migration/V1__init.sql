create table order_items (
       id bigint not null AUTO_INCREMENT,
        description varchar(255) not null,
        quantity integer not null,
        unit_price decimal(19,2) not null,
        order_id bigint,
        primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table orders (
       id bigint not null AUTO_INCREMENT,
        address varchar(255) not null,
        confirmation_date datetime not null,
        status varchar(255) not null,
        primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table order_items
       add constraint FK_order_item_order_id
       foreign key (order_id)
       references orders (id);