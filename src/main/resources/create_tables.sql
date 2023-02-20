CREATE TABLE consultants
(
    id             serial PRIMARY KEY,
    name           VARCHAR(255),
    specialization VARCHAR(255),
    place          VARCHAR(255)
);

CREATE TABLE consultants_availability
(
    id             serial PRIMARY KEY,
    consultant_id  serial,
    available_from timestamp,
    available_to   timestamp,
    available      boolean,
    FOREIGN KEY (consultant_id) references consultants (id)
);

CREATE TABLE customers
(
    id          serial PRIMARY KEY,
    name        VARCHAR(255),
    place       VARCHAR(255),
    phone_number VARCHAR(255)
);