DROP TABLE students IF EXISTS;
DROP TABLE faculties IF EXISTS;
DROP TABLE cathedras IF EXISTS;
DROP TABLE Group_classes IF EXISTS;
DROP TABLE employees IF EXISTS;

CREATE TABLE faculties (
    id          INTEGER IDENTITY PRIMARY KEY,
    title       VARCHAR(120),
    information VARCHAR(400),
    boss        VARCHAR(45),
    contact_inf VARCHAR(350)
);

CREATE TABLE cathedras (
    id          INTEGER IDENTITY PRIMARY KEY,
    title       VARCHAR(120),
    information VARCHAR(400),
    boss        VARCHAR(45),
    contact_inf VARCHAR(350),
    programs    VARCHAR(550),
    faculty_id  INTEGER NOT NULL
);
ALTER TABLE cathedras ADD CONSTRAINT fk_cathedras_faculties FOREIGN KEY (faculty_id) REFERENCES faculties (id);

CREATE TABLE group_classes (
    id          INTEGER IDENTITY PRIMARY KEY,
    name        VARCHAR(10),
    fos         VARCHAR(10),
    cathedra_id INTEGER NOT NULL,
    faculty_id  INTEGER NOT NULL
);
ALTER TABLE group_classes ADD CONSTRAINT fk_group_classes_cathedras FOREIGN KEY (cathedra_id) REFERENCES cathedras (id);
ALTER TABLE group_classes ADD CONSTRAINT fk_group_classes_faculties FOREIGN KEY (faculty_id) REFERENCES faculties (id);

CREATE TABLE students (
    id             INTEGER IDENTITY PRIMARY KEY,
    fio            VARCHAR(30),
    birthday       DATE,
    sex            VARCHAR(1),
    fact_address   VARCHAR(30),
    address        VARCHAR(30),
    telephone      VARCHAR(11),
    group_class_id INTEGER NOT NULL,
    cathedra_id    INTEGER NOT NULL,
    faculty_id     INTEGER NOT NULL
);
ALTER TABLE students ADD CONSTRAINT fk_students_group_classes FOREIGN KEY (group_class_id) REFERENCES group_classes (id);
ALTER TABLE students ADD CONSTRAINT fk_students_cathedras FOREIGN KEY (cathedra_id) REFERENCES cathedras (id);
ALTER TABLE students ADD CONSTRAINT fk_students_faculties FOREIGN KEY (faculty_id) REFERENCES faculties (id);

CREATE TABLE employees (
    id             INTEGER IDENTITY PRIMARY KEY,
    fio            VARCHAR(60),
    position       VARCHAR(30),
    degree         VARCHAR(40),
    cathedra_id    INTEGER NOT NULL,
    faculty_id     INTEGER NOT NULL
);
ALTER TABLE employees ADD CONSTRAINT fk_employees_cathedras FOREIGN KEY (cathedra_id) REFERENCES cathedras (id);
ALTER TABLE employees ADD CONSTRAINT fk_employees_faculties FOREIGN KEY (faculty_id) REFERENCES faculties (id);