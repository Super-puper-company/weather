create table user
(
    id               int(10) unsigned not null auto_increment,
    telegram_user_id varchar(36) not null,
    name             varchar(64),
    created_at       timestamp  not null default current_timestamp on update current_timestamp,
    primary key (id),
    unique key uk_telegram_user_id (telegram_user_id),
    key idx_user__telegram_user_id (telegram_user_id)
) engine = innodb default charset = utf8;