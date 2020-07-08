create table tb_park
(
    park_id   int auto_increment
        primary key,
    park_name varchar(200) not null,
    constraint tb_park_park_name_uindex
        unique (park_name)
);

create table tb_spot
(
    spot_id          int auto_increment
        primary key,
    longitude        decimal(10, 7) not null,
    latitude         decimal(10, 7) not null,
    spot_name        varchar(200)   not null,
    spot_type        int default 0  not null,
    realtime_people  int default 0  null,
    suggested_people int            not null,
    constraint tb_spot_pk
        unique (longitude, latitude)
);

create table tb_notice
(
    notice_id     int auto_increment
        primary key,
    spot_id       int          not null,
    notice_title  varchar(50)  not null,
    notice_detail varchar(500) null,
    issue_date    date         not null,
    constraint tb_notice_tb_spot_spot_id_fk
        foreign key (spot_id) references tb_spot (spot_id)
            on delete cascade
);

create table tb_spot_order_time
(
    spot_order_time_id int auto_increment
        primary key,
    spot_id            int             not null,
    start_time         time            not null,
    end_time           time            not null,
    suggested_people   int default 100 not null,
    constraint table_spot_order_time_pk_2
        unique (spot_id, start_time),
    constraint table_spot_order_time_pk_3
        unique (spot_id, end_time),
    constraint table_spot_order_time_tb_spot_spot_id_fk
        foreign key (spot_id) references tb_spot (spot_id)
);

create table tb_spot_wish_time
(
    spot_wish_time_id int auto_increment
        primary key,
    spot_id           int  not null,
    start_time        time not null,
    end_time          time not null,
    constraint tb_spot_wish_time_pk_2
        unique (spot_id, start_time),
    constraint tb_spot_wish_time_pk_3
        unique (end_time, spot_id),
    constraint tb_spot_wish_time_tb_spot_spot_id_fk
        foreign key (spot_id) references tb_spot (spot_id)
            on delete cascade
);

create table tb_user
(
    user_id   int auto_increment
        primary key,
    user_name varchar(200) not null,
    constraint tb_user_user_name_uindex
        unique (user_name)
);

create table tb_order
(
    order_id     int auto_increment
        primary key,
    order_time   int           not null,
    order_date   date          not null,
    user_id      int           not null,
    order_status int default 0 not null,
    note         varchar(200)  null,
    constraint tb_order_pk
        unique (order_time, order_date, user_id),
    constraint tb_order_tb_spot_order_time_spot_order_time_id_fk
        foreign key (order_time) references tb_spot_order_time (spot_order_time_id),
    constraint tb_order_tb_user_user_id_fk
        foreign key (user_id) references tb_user (user_id)
);

create table tb_user_location
(
    user_id     int      not null
        primary key,
    spot_id     int      not null,
    update_time datetime not null,
    constraint tb_user_location_tb_spot_spot_id_fk
        foreign key (spot_id) references tb_spot (spot_id)
            on delete cascade,
    constraint tb_user_location_tb_user_user_id_fk
        foreign key (user_id) references tb_user (user_id)
);

create table tb_wish
(
    wish_id   int auto_increment
        primary key,
    user_id   int  not null,
    wish_time int  not null,
    wish_date date not null,
    constraint tb_wish_pk_2
        unique (user_id, wish_time, wish_date),
    constraint tb_wish_tb_spot_wish_time_spot_wish_time_id_fk
        foreign key (wish_time) references tb_spot_wish_time (spot_wish_time_id)
            on delete cascade,
    constraint tb_wish_tb_user_user_id_fk
        foreign key (user_id) references tb_user (user_id)
            on delete cascade
);