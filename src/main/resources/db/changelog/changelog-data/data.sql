--liquibase formatted sql


--changeset umid:1
INSERT INTO roles (name)
VALUES ('USER'), ('ADMIN'), ('OPERATOR');



--changeset umid:2
INSERT INTO users (username, password_hash)
VALUES
    ('user1', 'pass'), ('user2', 'pass'), ('user3', 'pass'), ('user4', 'pass'),
    ('admin1', 'pass'), ('admin2', 'pass'), ('operator1', 'pass'), ('operator2', 'pass'),
    ('su', 'pass');



--changeset umid:3
INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1), (2, 1), (3, 1), (4, 1), (5, 2), (6, 2), (7, 3), (8, 3), (9, 1), (9, 2), (9, 3);


--changeset umid:4
insert into applications (name, text, phone_number, user_id, status, created_at) values (1, 1, '512-101-9114', 2, 'DRAFT', '2023-05-12');
insert into applications (name, text, phone_number, user_id, status, created_at) values (2, 2, '610-380-2428', 3, 'ACCEPTED', '2023-11-13');
insert into applications (name, text, phone_number, user_id, status, created_at) values (3, 3, '537-658-6659', 4, 'ACCEPTED', '2023-10-19');
insert into applications (name, text, phone_number, user_id, status, created_at) values (4, 4, '656-995-9473', 1, 'ACCEPTED', '2023-08-05');
insert into applications (name, text, phone_number, user_id, status, created_at) values (5, 5, '478-368-4437', 2, 'SENT', '2023-09-21');
insert into applications (name, text, phone_number, user_id, status, created_at) values (6, 6, '566-282-8841', 3, 'DECLINED', '2023-11-30');
insert into applications (name, text, phone_number, user_id, status, created_at) values (7, 7, '682-795-4648', 4, 'SENT', '2024-02-08');
insert into applications (name, text, phone_number, user_id, status, created_at) values (8, 8, '875-709-8728', 1, 'ACCEPTED', '2024-02-28');
insert into applications (name, text, phone_number, user_id, status, created_at) values (9, 9, '173-487-7120', 2, 'DECLINED', '2023-12-05');
insert into applications (name, text, phone_number, user_id, status, created_at) values (10, 10, '343-230-9273', 3, 'DECLINED', '2023-08-13');
insert into applications (name, text, phone_number, user_id, status, created_at) values (11, 11, '549-583-7157', 4, 'SENT', '2023-03-31');
insert into applications (name, text, phone_number, user_id, status, created_at) values (12, 12, '514-621-2268', 1, 'SENT', '2023-11-30');
insert into applications (name, text, phone_number, user_id, status, created_at) values (13, 13, '196-295-8689', 2, 'DRAFT', '2024-02-24');
insert into applications (name, text, phone_number, user_id, status, created_at) values (14, 14, '603-979-5093', 3, 'ACCEPTED', '2023-12-12');
insert into applications (name, text, phone_number, user_id, status, created_at) values (15, 15, '150-607-6695', 4, 'DRAFT', '2023-06-16');
insert into applications (name, text, phone_number, user_id, status, created_at) values (16, 16, '581-165-2936', 1, 'ACCEPTED', '2023-05-02');
insert into applications (name, text, phone_number, user_id, status, created_at) values (17, 17, '164-894-1151', 2, 'ACCEPTED', '2023-06-28');
insert into applications (name, text, phone_number, user_id, status, created_at) values (18, 18, '980-894-9955', 3, 'DRAFT', '2023-09-08');
insert into applications (name, text, phone_number, user_id, status, created_at) values (19, 19, '328-535-1738', 4, 'DRAFT', '2023-04-05');
insert into applications (name, text, phone_number, user_id, status, created_at) values (20, 20, '867-915-5279', 1, 'DECLINED', '2024-02-17');
insert into applications (name, text, phone_number, user_id, status, created_at) values (21, 21, '338-997-7805', 2, 'ACCEPTED', '2023-11-03');
insert into applications (name, text, phone_number, user_id, status, created_at) values (22, 22, '169-223-8619', 3, 'ACCEPTED', '2024-02-01');
insert into applications (name, text, phone_number, user_id, status, created_at) values (23, 23, '685-569-1273', 4, 'DRAFT', '2023-04-02');
insert into applications (name, text, phone_number, user_id, status, created_at) values (24, 24, '762-825-2382', 1, 'SENT', '2023-12-29');
insert into applications (name, text, phone_number, user_id, status, created_at) values (25, 25, '423-481-4399', 2, 'DRAFT', '2023-04-11');
insert into applications (name, text, phone_number, user_id, status, created_at) values (26, 26, '571-580-5418', 3, 'DECLINED', '2023-07-25');
insert into applications (name, text, phone_number, user_id, status, created_at) values (27, 27, '762-191-8784', 4, 'DECLINED', '2023-11-19');
insert into applications (name, text, phone_number, user_id, status, created_at) values (28, 28, '157-314-5866', 1, 'DECLINED', '2024-01-29');
insert into applications (name, text, phone_number, user_id, status, created_at) values (29, 29, '953-308-4499', 2, 'DRAFT', '2023-08-20');
insert into applications (name, text, phone_number, user_id, status, created_at) values (30, 30, '276-779-2003', 3, 'DECLINED', '2023-11-09');
insert into applications (name, text, phone_number, user_id, status, created_at) values (31, 31, '249-958-0291', 4, 'ACCEPTED', '2023-12-18');
insert into applications (name, text, phone_number, user_id, status, created_at) values (32, 32, '329-674-3399', 1, 'DRAFT', '2023-09-17');
insert into applications (name, text, phone_number, user_id, status, created_at) values (33, 33, '490-416-8455', 2, 'DRAFT', '2023-08-01');
insert into applications (name, text, phone_number, user_id, status, created_at) values (34, 34, '491-176-6882', 3, 'ACCEPTED', '2024-01-19');
insert into applications (name, text, phone_number, user_id, status, created_at) values (35, 35, '375-711-7445', 4, 'DECLINED', '2024-02-23');
insert into applications (name, text, phone_number, user_id, status, created_at) values (36, 36, '741-805-6896', 1, 'DECLINED', '2023-06-08');
insert into applications (name, text, phone_number, user_id, status, created_at) values (37, 37, '233-420-6339', 2, 'DECLINED', '2023-07-06');
insert into applications (name, text, phone_number, user_id, status, created_at) values (38, 38, '261-154-4532', 3, 'DECLINED', '2023-08-31');
insert into applications (name, text, phone_number, user_id, status, created_at) values (39, 39, '980-463-2824', 4, 'SENT', '2023-07-18');
insert into applications (name, text, phone_number, user_id, status, created_at) values (40, 40, '356-114-7034', 1, 'DECLINED', '2023-12-26');
insert into applications (name, text, phone_number, user_id, status, created_at) values (41, 41, '322-949-5398', 2, 'SENT', '2023-06-05');
insert into applications (name, text, phone_number, user_id, status, created_at) values (42, 42, '121-114-3334', 3, 'ACCEPTED', '2023-10-05');
insert into applications (name, text, phone_number, user_id, status, created_at) values (43, 43, '559-988-8634', 4, 'DRAFT', '2024-01-19');
insert into applications (name, text, phone_number, user_id, status, created_at) values (44, 44, '212-985-4181', 1, 'ACCEPTED', '2023-09-01');
insert into applications (name, text, phone_number, user_id, status, created_at) values (45, 45, '183-611-8878', 2, 'ACCEPTED', '2023-10-24');
insert into applications (name, text, phone_number, user_id, status, created_at) values (46, 46, '506-573-7259', 3, 'DRAFT', '2023-10-15');
insert into applications (name, text, phone_number, user_id, status, created_at) values (47, 47, '273-902-1517', 4, 'DECLINED', '2023-11-12');
insert into applications (name, text, phone_number, user_id, status, created_at) values (48, 48, '542-336-8558', 1, 'SENT', '2023-03-25');
insert into applications (name, text, phone_number, user_id, status, created_at) values (49, 49, '801-168-7551', 2, 'ACCEPTED', '2023-03-28');
insert into applications (name, text, phone_number, user_id, status, created_at) values (50, 50, '807-384-3169', 3, 'SENT', '2023-03-13');
insert into applications (name, text, phone_number, user_id, status, created_at) values (51, 51, '523-499-7580', 4, 'SENT', '2023-12-09');
insert into applications (name, text, phone_number, user_id, status, created_at) values (52, 52, '167-182-5745', 1, 'DECLINED', '2023-11-08');
insert into applications (name, text, phone_number, user_id, status, created_at) values (53, 53, '163-673-9660', 2, 'DRAFT', '2023-06-02');
insert into applications (name, text, phone_number, user_id, status, created_at) values (54, 54, '685-120-6430', 3, 'DECLINED', '2023-04-24');
insert into applications (name, text, phone_number, user_id, status, created_at) values (55, 55, '672-222-0197', 4, 'DECLINED', '2023-09-08');
insert into applications (name, text, phone_number, user_id, status, created_at) values (56, 56, '454-771-1767', 1, 'SENT', '2024-01-01');
insert into applications (name, text, phone_number, user_id, status, created_at) values (57, 57, '172-581-0572', 2, 'DECLINED', '2023-10-18');
insert into applications (name, text, phone_number, user_id, status, created_at) values (58, 58, '846-650-0260', 3, 'DRAFT', '2023-03-11');
insert into applications (name, text, phone_number, user_id, status, created_at) values (59, 59, '325-292-3279', 4, 'DECLINED', '2023-10-17');
insert into applications (name, text, phone_number, user_id, status, created_at) values (60, 60, '289-250-1780', 1, 'ACCEPTED', '2023-12-11');
insert into applications (name, text, phone_number, user_id, status, created_at) values (61, 61, '220-601-1730', 2, 'DECLINED', '2023-11-21');
insert into applications (name, text, phone_number, user_id, status, created_at) values (62, 62, '179-348-8519', 3, 'SENT', '2023-08-28');
insert into applications (name, text, phone_number, user_id, status, created_at) values (63, 63, '589-470-3217', 4, 'SENT', '2023-10-16');
insert into applications (name, text, phone_number, user_id, status, created_at) values (64, 64, '648-444-5360', 1, 'SENT', '2023-04-02');
insert into applications (name, text, phone_number, user_id, status, created_at) values (65, 65, '226-154-5564', 2, 'DECLINED', '2024-01-11');
insert into applications (name, text, phone_number, user_id, status, created_at) values (66, 66, '529-854-8187', 3, 'ACCEPTED', '2023-12-11');
insert into applications (name, text, phone_number, user_id, status, created_at) values (67, 67, '683-923-8806', 4, 'ACCEPTED', '2023-09-08');
insert into applications (name, text, phone_number, user_id, status, created_at) values (68, 68, '495-310-3611', 1, 'ACCEPTED', '2023-08-14');
insert into applications (name, text, phone_number, user_id, status, created_at) values (69, 69, '923-404-1417', 2, 'ACCEPTED', '2023-03-31');
insert into applications (name, text, phone_number, user_id, status, created_at) values (70, 70, '820-100-7307', 3, 'ACCEPTED', '2023-12-20');
insert into applications (name, text, phone_number, user_id, status, created_at) values (71, 71, '539-849-5049', 4, 'ACCEPTED', '2023-08-29');
insert into applications (name, text, phone_number, user_id, status, created_at) values (72, 72, '408-952-5397', 1, 'SENT', '2023-10-15');
insert into applications (name, text, phone_number, user_id, status, created_at) values (73, 73, '521-687-1902', 2, 'SENT', '2023-07-07');
insert into applications (name, text, phone_number, user_id, status, created_at) values (74, 74, '922-728-0840', 3, 'ACCEPTED', '2024-02-06');
insert into applications (name, text, phone_number, user_id, status, created_at) values (75, 75, '583-794-2154', 4, 'ACCEPTED', '2023-09-28');
insert into applications (name, text, phone_number, user_id, status, created_at) values (76, 76, '318-330-5924', 1, 'DECLINED', '2023-03-15');
insert into applications (name, text, phone_number, user_id, status, created_at) values (77, 77, '734-126-9289', 2, 'DECLINED', '2023-12-08');
insert into applications (name, text, phone_number, user_id, status, created_at) values (78, 78, '150-920-8510', 3, 'DECLINED', '2023-12-12');
insert into applications (name, text, phone_number, user_id, status, created_at) values (79, 79, '636-121-4285', 4, 'SENT', '2023-05-31');
insert into applications (name, text, phone_number, user_id, status, created_at) values (80, 80, '171-765-8802', 1, 'DECLINED', '2023-09-11');
insert into applications (name, text, phone_number, user_id, status, created_at) values (81, 81, '373-660-0329', 2, 'ACCEPTED', '2023-06-09');
insert into applications (name, text, phone_number, user_id, status, created_at) values (82, 82, '675-735-2852', 3, 'DECLINED', '2023-10-21');
insert into applications (name, text, phone_number, user_id, status, created_at) values (83, 83, '609-554-8987', 4, 'DECLINED', '2023-06-01');
insert into applications (name, text, phone_number, user_id, status, created_at) values (84, 84, '669-287-4928', 1, 'DRAFT', '2023-06-06');
insert into applications (name, text, phone_number, user_id, status, created_at) values (85, 85, '493-796-9619', 2, 'ACCEPTED', '2023-03-30');
insert into applications (name, text, phone_number, user_id, status, created_at) values (86, 86, '295-827-8498', 3, 'DECLINED', '2023-12-12');
insert into applications (name, text, phone_number, user_id, status, created_at) values (87, 87, '477-815-4457', 4, 'DRAFT', '2023-06-29');
insert into applications (name, text, phone_number, user_id, status, created_at) values (88, 88, '365-820-2422', 1, 'DECLINED', '2023-12-25');
insert into applications (name, text, phone_number, user_id, status, created_at) values (89, 89, '830-987-7791', 2, 'ACCEPTED', '2023-03-23');
insert into applications (name, text, phone_number, user_id, status, created_at) values (90, 90, '217-826-6481', 3, 'DRAFT', '2023-12-31');
insert into applications (name, text, phone_number, user_id, status, created_at) values (91, 91, '243-426-3833', 4, 'ACCEPTED', '2023-11-07');
insert into applications (name, text, phone_number, user_id, status, created_at) values (92, 92, '949-969-6425', 1, 'ACCEPTED', '2023-05-30');
insert into applications (name, text, phone_number, user_id, status, created_at) values (93, 93, '676-454-7643', 2, 'DECLINED', '2023-12-07');
insert into applications (name, text, phone_number, user_id, status, created_at) values (94, 94, '140-572-7374', 3, 'ACCEPTED', '2023-10-24');
insert into applications (name, text, phone_number, user_id, status, created_at) values (95, 95, '240-617-7705', 4, 'SENT', '2023-09-04');
insert into applications (name, text, phone_number, user_id, status, created_at) values (96, 96, '790-534-2512', 1, 'DRAFT', '2023-11-25');
insert into applications (name, text, phone_number, user_id, status, created_at) values (97, 97, '237-238-5039', 2, 'ACCEPTED', '2023-06-25');
insert into applications (name, text, phone_number, user_id, status, created_at) values (98, 98, '313-806-0131', 3, 'ACCEPTED', '2023-10-03');
insert into applications (name, text, phone_number, user_id, status, created_at) values (99, 99, '892-835-2500', 4, 'ACCEPTED', '2023-08-22');
insert into applications (name, text, phone_number, user_id, status, created_at) values (100, 100, '897-737-0148', 1, 'DECLINED', '2023-04-17');