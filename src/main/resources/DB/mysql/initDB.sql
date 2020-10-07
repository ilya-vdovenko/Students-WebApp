/*only for dev*/
/*TODO replace*/
DROP DATABASE IF EXISTS institute;
/**/
CREATE DATABASE IF NOT EXISTS institute;

ALTER DATABASE institute
    DEFAULT CHARACTER SET utf8
    DEFAULT COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS institute.employees
(
    id          INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    fio         VARCHAR(60),
    position    VARCHAR(30),
    degree      VARCHAR(40),
    cathedra_id INT(4) UNSIGNED,
    faculty_id  INT(4) UNSIGNED NOT NULL
) engine = InnoDB;

CREATE TABLE IF NOT EXISTS institute.faculties
(
    id          INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title       VARCHAR(120),
    information VARCHAR(400),
    boss        INT(4) UNSIGNED,
    contact_inf VARCHAR(350),
    FOREIGN KEY (boss) REFERENCES employees (id)
) engine = InnoDB;

CREATE TABLE IF NOT EXISTS institute.cathedras
(
    id           INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title        VARCHAR(120),
    information  VARCHAR(400),
    boss         INT(4) UNSIGNED,
    contact_inf  VARCHAR(350),
    edu_programs VARCHAR(550),
    faculty_id   INT(4) UNSIGNED NOT NULL,
    FOREIGN KEY (boss) REFERENCES employees (id),
    FOREIGN KEY (faculty_id) REFERENCES faculties (id)
) engine = InnoDB;

ALTER TABLE institute.employees
    ADD FOREIGN KEY (cathedra_id) REFERENCES cathedras (id);
ALTER TABLE institute.employees
    ADD FOREIGN KEY (faculty_id) REFERENCES faculties (id);

CREATE TABLE IF NOT EXISTS institute.group_classes
(
    id          INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title       VARCHAR(7),
    edu_form    VARCHAR(10),
    cathedra_id INT(4) UNSIGNED NOT NULL,
    FOREIGN KEY (cathedra_id) REFERENCES cathedras (id)
) engine = InnoDB;

CREATE TABLE IF NOT EXISTS institute.students
(
    id             INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    fio            VARCHAR(40),
    birthday       DATE,
    sex            VARCHAR(3),
    fact_address   VARCHAR(100),
    address        VARCHAR(100),
    telephone      VARCHAR(11),
    group_class_id INT(4) UNSIGNED NOT NULL,
    cathedra_id    INT(4) UNSIGNED NOT NULL,
    faculty_id     INT(4) UNSIGNED NOT NULL,
    FOREIGN KEY (group_class_id) REFERENCES group_classes (id),
    FOREIGN KEY (cathedra_id) REFERENCES cathedras (id),
    FOREIGN KEY (faculty_id) REFERENCES faculties (id)
) engine = InnoDB;

CREATE TABLE IF NOT EXISTS institute.facultyWorker
(
    faculty_id  INT(4) UNSIGNED NOT NULL,
    employee_id INT(4) UNSIGNED NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employees (id),
    FOREIGN KEY (faculty_id) REFERENCES faculties (id)
) engine = InnoDB;

CREATE TABLE IF NOT EXISTS institute.facultySoviet
(
    faculty_id  INT(4) UNSIGNED NOT NULL,
    employee_id INT(4) UNSIGNED NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employees (id),
    FOREIGN KEY (faculty_id) REFERENCES faculties (id)
) engine = InnoDB;

CREATE TABLE IF NOT EXISTS institute.cathedraLectures
(
    cathedra_id INT(4) UNSIGNED,
    employee_id INT(4) UNSIGNED NOT NULL,
    FOREIGN KEY (cathedra_id) REFERENCES cathedras (id),
    FOREIGN KEY (employee_id) REFERENCES employees (id)
) engine = InnoDB;
