DROP SCHEMA PUBLIC CASCADE;

CREATE TABLE faculties (
    id              INTEGER IDENTITY PRIMARY KEY,
    title           VARCHAR(120),
    information     VARCHAR(400),
    boss            INTEGER,
    contact_inf     VARCHAR(350)
);

CREATE TABLE cathedras (
    id              INTEGER IDENTITY PRIMARY KEY,
    title           VARCHAR(120),
    information     VARCHAR(400),
    boss            INTEGER,
    contact_inf     VARCHAR(350),
    edu_programs    VARCHAR(550),
    faculty_id      INTEGER NOT NULL
);
ALTER TABLE cathedras ADD CONSTRAINT fk_cathedras_faculties FOREIGN KEY (faculty_id) REFERENCES faculties (id);

CREATE TABLE group_classes (
    id              INTEGER IDENTITY PRIMARY KEY,
    title           VARCHAR(7),
    edu_form        VARCHAR(10),
    cathedra_id     INTEGER NOT NULL
);
ALTER TABLE group_classes ADD CONSTRAINT fk_group_classes_cathedras FOREIGN KEY (cathedra_id) REFERENCES cathedras (id);

CREATE TABLE students (
    id             INTEGER IDENTITY PRIMARY KEY,
    fullName       VARCHAR(40),
    birthday       DATE,
    sex            VARCHAR(3),
    actualAddress  VARCHAR(100),
    address        VARCHAR(100),
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
    fullName       VARCHAR(60),
    position       VARCHAR(30),
    degree         VARCHAR(40),
    cathedra_id    INTEGER,
    faculty_id     INTEGER NOT NULL
);
ALTER TABLE faculties ADD CONSTRAINT fk_faculties_employees FOREIGN KEY (boss) REFERENCES employees (id);
ALTER TABLE cathedras ADD CONSTRAINT fk_cathedras_employees FOREIGN KEY (boss) REFERENCES employees (id);
ALTER TABLE employees ADD CONSTRAINT fk_employees_cathedras FOREIGN KEY (cathedra_id) REFERENCES cathedras (id);
ALTER TABLE employees ADD CONSTRAINT fk_employees_faculties FOREIGN KEY (faculty_id) REFERENCES faculties (id);

CREATE TABLE facultyWorker (
    faculty_id     INTEGER NOT NULL,
    employee_id    INTEGER NOT NULL
);
ALTER TABLE facultyWorker ADD CONSTRAINT fk_facultyWorker_faculties FOREIGN KEY (faculty_id) REFERENCES faculties (id);
ALTER TABLE facultyWorker ADD CONSTRAINT fk_facultyWorker_employees FOREIGN KEY (employee_id) REFERENCES employees (id);

CREATE TABLE facultyBoard (
    faculty_id     INTEGER NOT NULL,
    employee_id    INTEGER NOT NULL
);
ALTER TABLE facultyBoard ADD CONSTRAINT fk_facultyBoard_faculties FOREIGN KEY (faculty_id) REFERENCES faculties (id);
ALTER TABLE facultyBoard ADD CONSTRAINT fk_facultyBoard_employees FOREIGN KEY (employee_id) REFERENCES employees (id);

CREATE TABLE cathedraLectures (
    cathedra_id    INTEGER NOT NULL,
    employee_id    INTEGER NOT NULL
);
ALTER TABLE cathedraLectures ADD CONSTRAINT fk_cathedraLectures_cathedras FOREIGN KEY (cathedra_id) REFERENCES cathedras (id);
ALTER TABLE cathedraLectures ADD CONSTRAINT fk_cathedraLectures_employees FOREIGN KEY (employee_id) REFERENCES employees (id);
