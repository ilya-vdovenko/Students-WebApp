DROP TABLE students IF EXISTS;

CREATE TABLE students (
  id            INTEGER IDENTITY PRIMARY KEY,
  fio           VARCHAR(30),
  birthday      DATE,
  sex           VARCHAR(1),
  fact_address  VARCHAR(30),
  address       VARCHAR(30),
  telephone     VARCHAR(11),
  faculty       VARCHAR(30),
  group_class   VARCHAR(10),
  fos           VARCHAR(10)
);