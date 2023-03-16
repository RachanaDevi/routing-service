CREATE TABLE IF NOT EXISTS consultants
(
    id             serial PRIMARY KEY,
    name           VARCHAR(255),
    specialization VARCHAR(255),
    place          VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS consultants_availability
(
    id             serial PRIMARY KEY,
    consultant_id  serial,
    available_from timestamp,
    available_to   timestamp,
    available      boolean,
    FOREIGN KEY (consultant_id) references consultants (id)
);

CREATE TABLE IF NOT EXISTS customers
(
    id          serial PRIMARY KEY,
    name        VARCHAR(255),
    place       VARCHAR(255),
    phone_number VARCHAR(255)
);