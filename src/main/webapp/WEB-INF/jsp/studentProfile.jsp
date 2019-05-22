<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <title>StudentProfile</title>
</head>
<body>
<h1><b>Информация о студенте</b></h1>
<table>
    <tr>
        <th>ФИО</th>
        <td><b><c:out value="${student.fio}"/></b></td>
    </tr>
    <tr>
        <th>Дата рождения</th>
        <td><c:out value="${student.birthday}"/>/td>
    </tr>
    <tr>
        <th>Пол</th>
        <td><c:out value="${student.sex}"/></td>
    </tr>
    <tr>
        <th>Факт. адрес</th>
        <td><c:out value="${student.fact_address}"/></td>
    </tr>
    <tr>
        <th>Адрес</th>
        <td><c:out value="${student.address}"/></td>
    </tr>
    <tr>
        <th>Контактный тел.</th>
        <td><c:out value="${student.telephone}"/></td>
    </tr>
    <tr>
        <th>Факультет</th>
        <td>
            <spring:url value="{facultyId}/" var="facUrl">
                <spring:param name="facultyId" value="${faculty.id}"/>
            </spring:url>
            <a href="${facUrl}"> <c:out value="${student.faculty}"/></a>
        </td>
    </tr>
    <tr>
        <th>Группа</th>
        <td><c:out value="${student.group_class}"/></td>
    </tr>
    <tr>
        <th>Форма обучения</th>
        <td><c:out value="${student.fos}"/></td>
    </tr>
</table>
<div>
    <spring:url value="{studentId}/edit" var="editUrl">
        <spring:param name="studentId" value="${student.id}"/>
    </spring:url>
    <a href="${editUrl}">Редактировать профиль</a>
</div>

</body>
</html>