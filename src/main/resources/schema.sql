CREATE DATABASE consultant;
CREATE TABLE consultants
(
    id             serial PRIMARY KEY,
    name           VARCHAR(255),
--     specialization_id serial,
    place          VARCHAR(255)
);
-- specializationId is same productCategoryId (in a way foreign key constraint),
-- how do you maintain that consistency of naming?

CREATE TABLE consultants_specializations
(
    id             serial PRIMARY KEY,
    consultant_id  serial,
    specialization_id serial,
    FOREIGN KEY (consultant_id) REFERENCES consultants(id)
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

-- creating another table potraying a consultant can have many specializations and that is the productCategoryId from product table