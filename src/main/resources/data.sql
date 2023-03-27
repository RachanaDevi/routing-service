INSERT INTO consultants (name, place)
VALUES ('Tom', 'Pune');
INSERT INTO consultants (name, place)
VALUES ('Kate', 'Mumbai');

INSERT INTO consultants_specializations(consultant_id, specialization_id)
VALUES(1,1);
INSERT INTO consultants_specializations(consultant_id, specialization_id)
VALUES(2,2);


INSERT INTO consultants_availability (consultant_id, available_from, available_to, available)
VALUES (1, '2023-02-18 01:24', '2023-02-22 01:24:23.0', TRUE);
INSERT INTO consultants_availability (consultant_id, available_from, available_to, available)
VALUES (1, '2023-02-11 00:00', '2023-02-22 01:24:23.0', TRUE);
INSERT INTO consultants_availability (consultant_id, available_from, available_to, available)
VALUES (2, '2023-02-10 01:24:23', '2023-03-10 01:24:23.0', FALSE);

