DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO public.meals (user_id, datetime, description, calories) VALUES (100000,'2017-05-30 10:00', 'Завтрак', 600);
INSERT INTO public.meals (user_id, datetime, description, calories) VALUES (100000,'2017-05-30 13:00', 'Обед', 900);
INSERT INTO public.meals (user_id, datetime, description, calories) VALUES (100000,'2017-05-30 20:00', 'Ужин', 500);
INSERT INTO public.meals (user_id, datetime, description, calories) VALUES (100000,'2017-05-31 10:00', 'Завтрак', 1000);
INSERT INTO public.meals (user_id, datetime, description, calories) VALUES (100000,'2017-05-31 13:00', 'Обед', 1200);
INSERT INTO public.meals (user_id, datetime, description, calories) VALUES (100000,'2017-05-31 20:00', 'Ужин', 510);

INSERT INTO public.meals (user_id, datetime, description, calories) VALUES (100001,'2017-05-30 10:00', 'Завтрак', 500);
INSERT INTO public.meals (user_id, datetime, description, calories) VALUES (100001,'2017-05-30 13:00', 'Обед', 1000);
INSERT INTO public.meals (user_id, datetime, description, calories) VALUES (100001,'2017-05-30 20:00', 'Ужин', 500);
INSERT INTO public.meals (user_id, datetime, description, calories) VALUES (100001,'2017-05-31 10:00', 'Завтрак', 1000);
INSERT INTO public.meals (user_id, datetime, description, calories) VALUES (100001,'2017-05-31 13:00', 'Обед', 500);
INSERT INTO public.meals (user_id, datetime, description, calories) VALUES (100001,'2017-05-31 20:00', 'Ужин', 510);
