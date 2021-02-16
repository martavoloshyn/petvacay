INSERT INTO categories (category_id, category_name) VALUES (1,'Великі тварини'),(2,'Тварини середнього розміру'),(3,'Малі тварини'),(4,'Екзотичні тварини'),(5,'Тварини для подвір`я'),(6,'Тварини, що потребують лікування'),(7,'Ветеринарна освіта');
INSERT INTO feedbacks (feedback_id, date, stars, text) VALUES (1,'2021-02-07 12:43:32',5,'Чудовий догляд за твариною!');
INSERT INTO order_statuses (order_status_id, order_status_name) VALUES (1,'Створено'),(2,'Прийнято'),(3,'Сплачено'),(4,'Виконується'),(5,'Підтверджено: Виконавець'),(6,'Підтверджено: Замовник'),(7,'Завершено');
INSERT INTO pet_check_categories (pet_check_category_id, pet_check_category_name) VALUES (1,'Фото спального місця'),(2,'Відео з прогулянки'),(3,'Фото їжі для тварини');
INSERT INTO pet_check_statuses (pet_check_status_id, pet_check_status_name) VALUES (1,'Створено'),(2,'Надіслано відповідь'),(3,'Підтверджено');
INSERT INTO pet_sizes (pet_size_id, pet_size_name) VALUES (1,'Малий'),(2,'Середній'),(3,'Великий');
INSERT INTO pricing (pricing_id, price, big_pet_bonus, comment, date, exotic_pet_bonus, is_pedigreed_bonus, medium_pet_bonus, need_special_care_bonus, need_walking_bonus) VALUES (1,150,100,NULL,'2021-02-16 00:00:00',150,40,70,100,70);
INSERT INTO roles (role_id, role_name) VALUES (1,'Customer'),(2,'Performer'),(3,'Moderator');
INSERT INTO user_statuses (user_status_id, user_status_name) VALUES (1,'ACTIVATED'),(2,'NOT_ACTIVATED');
INSERT INTO users (user_id, email, first_name, last_name, password, role_id, user_status_id) VALUES (1,'martavoloshyn73@gmail.com','Marta','Voloshyn','pass',1,1),(2,'annkozak@gmail.com','Anna','Kozak','pass',2,1),(3,'nastyapyshyeva@gail.com','Anastasia','Pyshyeva','pass',3,1);
INSERT INTO customers (about_info, city, phone_number, user_id) VALUES ('Замовник','Львів','+380966088975',1);
INSERT INTO performers (about_info, apartment, birth_date, building, city, phone_number, street, user_id, pricing_id, card_number) VALUES ('Виконавець','13','2000-10-04 00:00:00','165','Львів','+380681314188','Городоцька',2,1,'4149876545673212');
INSERT INTO performer_works_with_category (performer_id, category_id) VALUES (2,1),(2,2),(2,5);
INSERT INTO vacations (vacation_id, end_date, start_date, performer_id) VALUES (1,'2021-02-25 00:00:00','2021-02-18 00:00:00',2),(2,'2021-03-05 00:00:00','2021-03-04 00:00:00',2);
INSERT INTO pets (pet_id, birth_date, breed, is_exotic, is_pedigreed, name, need_special_care, need_walking, type, customer_id, pet_size_id) VALUES (1,'2016-12-02 00:00:00','Такса',_binary '\0',_binary '','Монетка',_binary '\0',_binary '','Собака',1,2),(2,'2019-08-06 00:00:00',NULL,_binary '\0',_binary '\0','Лазло',_binary '\0',_binary '\0','Кіт',1,1);
INSERT INTO orders (order_id, comment, end_date, start_date, customer_id, feedback_id, order_status_id, performer_id) VALUES (1,'Замовлення 1','2021-02-18 00:00:00','2021-02-13 00:00:00',1,NULL,4,2),(2,'Замовлення 2','2021-01-29 00:00:00','2021-01-27 00:00:00',1,1,7,2);
INSERT INTO pets_in_order (pet_id, order_id) VALUES (1,1),(2,2);
INSERT INTO pet_checks (pet_check_id, request_comment, response_comment, order_id, pet_check_category_id, pet_check_status_id) VALUES (1,'Потрібне підтвердження',NULL,1,1,1),(2,'Потрібне підтвердження','Надіслано відповідь',2,2,3);
