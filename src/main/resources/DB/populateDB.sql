INSERT INTO faculties VALUES(1, 'Факультет энергетики и систем управления', 'Некоторая информация о факультете...', null, '394066, Московский проспект, 179, корпус 3, каб. 319, 321');
INSERT INTO faculties VALUES(2, 'Факультет информационных технологий и компьютерной безопасности', 'Некоторая информация о факультете...', null, '394066, Московский проспект, 179, корпус 3, каб. 213');

INSERT INTO cathedras VALUES(1, 'Кафедра электропривода, автоматики и управления в технических системах', 'Некоторая информация о кафедре...', null, '394066, г.Воронеж, Московский проспект, 179к3, 3-й учебный корпус, каб.118', 'Программы обучения...', 1);
INSERT INTO cathedras VALUES(2, 'Кафедра электромеханических систем и электроснабжения', 'Некоторая информация о кафедре...', null,'394066, г.Воронеж, Московский проспект, 179 3-й учебный корпус, каб. 232', 'Программы обучения...', 1);
INSERT INTO cathedras VALUES(3, 'Кафедра компьютерных интеллектуальных технологий проектирования','Некоторая информация о кафедре...', null,'394000 г.Воронеж, Плехановская 11','Программы обучения...', 2);

INSERT INTO group_classes VALUES(1, 'АТ-121', 'Очная', 1, 1);
INSERT INTO group_classes VALUES(2, 'АП-131', 'Заочная', 1, 1);
INSERT INTO group_classes VALUES(3, 'ПТ-111', 'Очная', 2, 1);
INSERT INTO group_classes VALUES(4, 'ЭМ-121', 'Очная', 3, 2);

INSERT INTO employees VALUES(1, 'Бурковский Виктор Леонидович', 'Заведущий кафедрой', 'Доктор технических наук, профессор', 1, 1);
INSERT INTO employees VALUES(2, 'Гусев Константин Юрьевич', 'Доцент', 'Кандидат технических наук', 1, 1);
INSERT INTO employees VALUES(3, 'Литвиненко Александр Михайлович', 'Профессор', 'Доктор технических наук, профессор', 1, 1);
INSERT INTO employees VALUES(4, 'Бурковский Александр Викторович', 'Декан', 'Кандидат технических наук, доцент', null, 1);
INSERT INTO employees VALUES(5, 'Киселёва Ольга Алексеевна', 'Секретарь деканата', '-', null, 1);
INSERT INTO employees VALUES(6, 'Сапоненко Татьяна Николаевна', 'Секретарь деканата', '-', null, 1);
INSERT INTO employees VALUES(7, 'Коротков Виктор Николаевич', 'Электроник 1 кат.', '-', 1, 1);
INSERT INTO employees VALUES(8, 'Левина Оксана Георгиевна', 'Электроник 1 кат.', '-', 1, 1);
INSERT INTO employees VALUES(9, 'Пивоварова Анна Григорьевна', 'Ведущий инженер', '-', 1, 1);
INSERT INTO employees VALUES(10, 'Юршин Александр Вячеславович', 'Ведущий электроник', '-', 1, 1);
INSERT INTO employees VALUES(11, 'Шелякин Валерий Петрович', 'Заведущий кафедрой', 'Кандидат технических наук, доцент', 2, 1);
INSERT INTO employees VALUES(12, 'Нюхин Олег Николаевич', 'Заведующий лабораториями', '-', 2, 1);
INSERT INTO employees VALUES(13, 'Терещенко Игорь Олегович', 'Ведущий инженер', '-', 2, 1);
INSERT INTO employees VALUES(14, 'Попова Татьяна Владимировна', 'Старший преподватель', '-', 1, 1);
INSERT INTO employees VALUES(15, 'Белозоров Сергей Алеександрович', 'Доцент', 'Кандидат технических наук', 2, 1);
INSERT INTO employees VALUES(16, 'Видулин Петр Петрович', 'Старший преподватель', '-', 2, 1);
INSERT INTO employees VALUES(17, 'Савельева Елена Леонидовна', 'Старший преподватель', '-', 2, 1);
INSERT INTO employees VALUES(18, 'Пасмурнов Сергей Михайлович', 'Декан', 'Кандидат технических наук, доцент', null, 2);
INSERT INTO employees VALUES(19, 'Яскевич Ольга Георгиевна', 'Доцент, заместитель декана', 'Кандидат технических наук, доцент', null, 2);
INSERT INTO employees VALUES(20, 'Сафронов Виталий Владимирович', 'Заведующий лабораторией', 'Кандидат технических наук', null, 2);
INSERT INTO employees VALUES(21, 'Чижов Михаил Иванович', 'Заведующий кафедрой', 'Доктор технических наук, профессор', 3, 2);
INSERT INTO employees VALUES(22, 'Дмитриев Александр Николаевич', 'Заведующий лабораторией', '-', 3, 2);
INSERT INTO employees VALUES(23, 'Попов Алексей Васильевич', 'Старший преподватель', '-', 3, 2);
INSERT INTO employees VALUES(24, 'Школьникова Юлия Михайловна', 'Ассистент', '-', 3, 2);
INSERT INTO employees VALUES(25, 'Собенина Ольга Валерьевна', 'Доцент', 'Кандидат технических наук', 3, 2);
INSERT INTO employees VALUES(26, 'Филимонова Анастасия Анатольевна', 'Старший преподватель', '-', 3, 2);

UPDATE faculties SET boss = 4 WHERE id = 1;
UPDATE faculties SET boss = 18 WHERE id = 2;
UPDATE cathedras SET boss = 1 WHERE id = 1;
UPDATE cathedras SET boss = 11 WHERE id = 2;
UPDATE cathedras SET boss = 21 WHERE id = 3;

INSERT INTO students VALUES (1, 'Вдоенко Илья Сергеевич', '1994-09-26', 'муж', 'г.Воронеж, Московский пр-кт 141', '-', '89518719254', 1, 1, 1);
INSERT INTO students VALUES (2, 'Валько Мария Сергеевна', '1994-11-15', 'жен', 'г.Воронеж, ул. Владимира Невского 56', '-', '83248764354', 1, 1, 1);
INSERT INTO students VALUES (4, 'Кудрявцев Авраам Федорович', '1994-10-10', 'муж', 'г.Воронеж, Ленинский Пр., дом 114, кв. 46', 'г. Яблоновка, ул. Воронцовский Переулок, дом 46, квартира 243', '89515555515', 1, 1, 1);
INSERT INTO students VALUES (5, 'Капустин Панкрат Геннадиевич', '1994-06-23', 'муж', 'г.Воронеж, Новгородская, дом 135, кв. 7', 'г. Москва, въезд Чехова, 97', '89045554854', 1, 1, 1);
INSERT INTO students VALUES (6, 'Орлов Макар Макарович', '1991-04-10', 'муж', 'г.Воронеж, Переверткина, дом 6, кв. 81', 'г. Кашира, пр. Ленина, 76', '89535556673', 2, 1, 1);
INSERT INTO students VALUES (7, 'Борисов Бенедикт Ефимович', '1993-11-15', 'муж', 'г.Воронеж, Ростовская, дом 66, кв. 62', '-', '89525554212', 2, 1, 1);
INSERT INTO students VALUES (8, 'Константинов Аполлон Улебович', '1992-08-06', 'муж', 'г.Воронеж, Карла/маркса, дом 78/А, кв. 9', '-', '89535552863', 2, 1, 1);
INSERT INTO students VALUES (9, 'Зимин Богдан Валерьевич', '1993-02-12', 'муж', 'г.Воронеж, Медицинская Ул., дом 9, кв. 127', 'г. Домодедово, шоссе 1905 ', '89515554408', 2, 1, 1);
INSERT INTO students VALUES (10, 'Калинин Никифор Владиславович', '1992-09-30', 'муж', 'г.Воронеж, Московский Пр., дом 82, кв. 156', '-', '89045552784', 3, 2, 1);
INSERT INTO students VALUES (11, 'Комиссаров Марк Вадимович', '1995-09-27', 'муж', 'г.Воронеж, Московский проспект, 179в', 'г. Серебряные Пруды, въезд Космонавтов, 13', '89355519790', 3, 2, 1);
INSERT INTO students VALUES (12, 'Тимофеев Харитон Миронович', '1992-03-17', 'муж', 'г.Воронеж, Московский проспект, 179в', 'г. Шатура, шоссе Ладыгина, 67', '89355577080', 3, 2, 1);
INSERT INTO students VALUES (13, 'Киселёва Герда Альбертовна', '1993-03-03', 'жен', 'г.Воронеж, Московский проспект, 179в', 'г. Воскресенск, пл. Балканская, 75', '89095551864', 3, 2, 1);
INSERT INTO students VALUES (14, 'Николаева Лия Иосифовна', '1995-08-04', 'жен', 'г.Воронеж, Московский проспект, 179в', 'г. Солнечногорск, проезд Ленина, 00', '89085557268', 3, 2, 1);
INSERT INTO students VALUES (15, 'Рогова Ирэна Андреевна', '1996-10-30', 'жен', 'г.Воронеж, Московский проспект, 179в', 'г. Видное, спуск 1905 года, 28', '89355525803', 2, 1, 1);
INSERT INTO students VALUES (16, 'Сысоева Венера Оскаровна', '1994-06-21', 'жен', 'г.Воронеж, Московский проспект, 179в', 'г. Мытищи, пл. Гоголя, 12', '89255501467', 1, 1, 1);
INSERT INTO students VALUES (17, 'Гордеева Каролина Владленовна', '1995-02-24', 'жен', 'г.Воронеж, Московский проспект, 179в', 'г. Лотошино, пл. Славы, 28', '89255511844', 4, 3, 2);
INSERT INTO students VALUES (18, 'Максимова Кармелитта Макаровна', '1992-07-20', 'жен', 'г.Воронеж, Московский проспект, 179в', 'г. Сергиев Посад, проезд Гоголя, 75', '89045550199', 4, 3, 2);
INSERT INTO students VALUES (19, 'Савина Элиза Игнатьевна', '1991-11-19', 'жен', 'г.Воронеж, Московский проспект, 179в', 'г. Шаховская, въезд Космонавтов, 05', '89255571478', 4, 3, 2);
INSERT INTO students VALUES (20, 'Федосеева Томила Семеновна', '1996-10-17', 'жен', 'г.Воронеж, Московский проспект, 179в', 'г. Чехов, шоссе Ладыгина, 46', '89065550474', 4, 3, 2);