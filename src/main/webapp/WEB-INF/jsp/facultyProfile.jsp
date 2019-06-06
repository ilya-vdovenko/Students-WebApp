<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <title>FacultyProfile</title>
</head>
<body>
<h1><b>Информация о факультете</b></h1>
<table>
    <tr>
        <th>Название</th>
        <td><b><c:out value="${faculty.title}"/></b></td>
    </tr>
    <tr>
        <th>Информация</th>
        <td><c:out value="${faculty.information}"/></td>
    </tr>
    <tr>
        <th>Декан</th>
        <td>
            <spring:url value="employees/{employeId}" var="empUrl">
                <spring:param name="employeId" value="${faculty.boss.id}"/>
            </spring:url>
            <a href="${empUrl}"> <c:out value="${faculty.boss.fio}"/> </a>
        </td>
    </tr>
    <tr>
        <th>Контактная инф.</th>
        <td><c:out value="${faculty.contact_inf}"/></td>
    </tr>

    <tr>
        <th>Кафедры</th>
        <td>
            <spring:url value="/{facultyId}/cathedras" var="catUrl">
                <spring:param name="facultyId" value="${faculty.id}"/>
            </spring:url>
            <a href="${catUrl}"> <c:out value="..."/></a>
        </td>
    </tr>

    <!--TODO: set of information and value = "..."-->
    <tr>
        <th>Совет</th>
        <td><c:out value="${faculty.soviet}"/></td>
    </tr>
    <tr>
        <th>Студенты</th>
        <td><c:out value="${faculty.students}"/></td>
    </tr>
    <tr>
        <th>Сотрудники</th>
        <td><c:out value="${faculty.employees}"/></td>
    </tr>
</table>
</body>
</html>