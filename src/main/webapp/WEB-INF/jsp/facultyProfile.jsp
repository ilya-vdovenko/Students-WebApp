<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <title>FacultyProfile</title>
    <link rel="stylesheet" type="text/css" href="/resources/style/profile.css">
</head>
<body>
<h1><b><c:out value="${faculty.title}"/></b></h1>
<table>
    <tr>
        <th>Информация</th>
        <td><c:out value="${faculty.information}"/></td>
    </tr>
    <tr>
        <th>Декан</th>
        <td>
            <spring:url value="/employees/{employeId}" var="empUrl">
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
        <th>Совет</th>
        <td>
            <spring:url value="/faculties/{facultyId}/soviet" var="sovUrl">
                <spring:param name="facultyId" value="${faculty.id}"/>
            </spring:url>
            <a href="${sovUrl}"> <c:out value="..."/></a>
    </tr>
    <tr>
        <th>Кафедры</th>
        <td>
            <spring:url value="/faculties/{facultyId}/cathedras" var="catUrl">
                <spring:param name="facultyId" value="${faculty.id}"/>
            </spring:url>
            <a href="${catUrl}"> <c:out value="..."/></a>
        </td>
    </tr>
    <tr>
        <th>Сотрудники</th>
        <td>
            <spring:url value="/faculties/{facultyId}/employees" var="emplUrl">
                <spring:param name="facultyId" value="${faculty.id}"/>
            </spring:url>
            <a href="${emplUrl}"> <c:out value="..."/></a>
        </td>
    </tr>
</table>
</body>
</html>