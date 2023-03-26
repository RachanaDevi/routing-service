INSERT INTO consultants (name, specialization_id, place)
VALUES ('Tom', 1, 'Pune');
INSERT INTO consultants (name, specialization_id, place)
VALUES ('Kate', '2, 'Mumbai');


INSERT INTO consultants_availability (consultant_id, available_from, available_to, available)
VALUES (1, '2023-02-18 01:24', '2023-02-22 01:24:23.0', TRUE);
INSERT INTO consultants_availability (consultant_id, available_from, available_to, available)
VALUES (1, '2023-02-11 00:00', '2023-02-22 01:24:23.0', TRUE);
INSERT INTO consultants_availability (consultant_id, available_from, available_to, available)
VALUES (2, '2023-02-10 01:24:23', '2023-03-10 01:24:23.0', FALSE);

