<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <title>CathedraProfile</title>
</head>
<body>
<h1><b>Информация о кафедре</b></h1>
<table>
    <tr>
        <th>Название</th>
        <td><b><c:out value="${сathedra.title}"/></b></td>
    </tr>
    <tr>
        <th>Информация</th>
        <td><c:out value="${сathedra.information}"/>/td>
    </tr>
    <tr>
        <th>Декан</th>
        <td><c:out value="${сathedra.boss}"/></td>
    </tr>
    <tr>
        <th>Контактная инф.</th>
        <td><c:out value="${сathedra.contact_inf}"/></td>
    </tr>
    <tr>
        <th>Программы обучения</th>
        <td><c:out value="${сathedra.programs}"/></td>
    </tr>
    <tr>
        <th>Преподаватели</th>
        <td><c:out value="${сathedra.lecturers}"/></td>
    </tr>
    <tr>
        <th>Студенты</th>
        <td><c:out value="${сathedra.students}"/></td>
    </tr>
    <tr>
        <th>Факультет</th>
        <td><c:out value="${сathedra.faculty}"/></td>
    </tr>
    <tr>
        <th>Группы</th>
        <td><c:out value="${сathedra.group_classes}"/></td>
    </tr>
</table>
</body>
</html>