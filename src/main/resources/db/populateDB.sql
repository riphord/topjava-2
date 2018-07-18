DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (description, calories, date_time,user_id) VALUES
  ('Завтрак', 500, TIMESTAMP '2015-05-30 10:00:00',100000),
  ('Обед', 1000, TIMESTAMP '2015-05-30 13:00:00',100000),
  ('Ужин', 500, TIMESTAMP '2015-05-30 20:00:00',100000),
  ('Завтрак', 1000, TIMESTAMP '2015-05-31 10:00:00',100000),
  ('Обед', 500, TIMESTAMP '2015-05-31 13:00:00',100000),
  ('Ужин', 510, TIMESTAMP '2015-05-31 20:00:00',100000),
  ('Админ ланч', 510, TIMESTAMP '2015-06-1 14:00:00',100001),
  ('Админ ужин', 1500, TIMESTAMP '2015-06-1 21:00:00',100001);

