CREATE TABLE IF NOT EXISTS consultants
(
    id    serial PRIMARY KEY,
    name  VARCHAR(255),
    place VARCHAR(255)
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

CREATE TABLE IF NOT EXISTS consultants_specializations
(
    id                serial PRIMARY KEY,
    consultant_id     serial,
    specialization_id serial,
    FOREIGN KEY (consultant_id) references consultants (id)
);

INSERT INTO consultants (name, place)
VALUES ('Tom', 'Pune');
INSERT INTO consultants (name, place)
VALUES ('Kate', 'Mumbai');


INSERT INTO consultants_availability (consultant_id, available_from, available_to, available)
VALUES (1, '2023-02-11 01:24:23.0', '2023-02-22 01:24:23.0', TRUE);
INSERT INTO consultants_availability (consultant_id, available_from, available_to, available)
VALUES (1, '2023-02-11 00:00:00.0', '2023-02-22 01:24:23.0', TRUE);
INSERT INTO consultants_availability (consultant_id, available_from, available_to, available)
VALUES (2, '2023-02-10 01:24:23.0', '2023-03-10 01:24:23.0', FALSE);

INSERT INTO consultants_specializations(consultant_id, specialization_id)
VALUES (1, 1);
INSERT INTO consultants_specializations(consultant_id, specialization_id)
VALUES (2, 2);