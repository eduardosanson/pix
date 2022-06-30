create table holder
(
    id        uuid         not null primary key,
    last_name varchar(45),
    name      varchar(30) not null,
    type      varchar(1)
);

create table account
(
    id        uuid not null primary key,
    agency    integer,
    number    integer,
    type      varchar(10),
    holder_id uuid constraint fk_account_holder_id references holder
);

create table pix_key
(
    id          uuid not null primary key,
    created_at  timestamp,
    disabled_at timestamp,
    type        varchar(9),
    value       varchar(77) not null,
    account_id  uuid constraint fk_pix_key_account_id references account
);

ALTER TABLE pix_key ADD CONSTRAINT u_constrainte UNIQUE NULLS NOT DISTINCT (value, disabled_at);
