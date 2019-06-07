<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <title>CathedraProfile</title>
</head>
<body>
<h1><b><c:out value="${cathedra.title}"/></b></h1>
<table>
    <tr>
        <th>Информация</th>
        <td><c:out value="${cathedra.information}"/><td>
    </tr>
    <tr>
        <th>Декан</th>
        <td>
            <spring:url value="/employees/{employeId}" var="empUrl">
                <spring:param name="employeId" value="${cathedra.boss.id}"/>
            </spring:url>
            <a href="${empUrl}"> <c:out value="${cathedra.boss.fio}"/> </a>
        </td>
    </tr>
    <tr>
        <th>Факультет</th>
        <td>
            <spring:url value="/faculties/{facultyId}" var="facUrl">
                <spring:param name="facultyId" value="${cathedra.faculty.id}"/>
            </spring:url>
            <a href="${facUrl}"> <c:out value="${cathedra.faculty.title}"/> </a>
    </tr>
    <tr>
        <th>Контактная инф.</th>
        <td><c:out value="${cathedra.contact_inf}"/></td>
    </tr>
    <tr>
        <th>Программы обучения</th>
        <td><c:out value="${cathedra.programs}"/></td>
    </tr>

    <!--TODO: set of information and value = "..." -->
    <tr>
        <th>Преподаватели</th>
        <td><c:out value="${cathedra.lecturers}"/></td>
    </tr>
    <tr>
        <th>Группы</th>
        <td><c:out value="${cathedra.group_classes}"/></td>
    </tr>
</table>
</body>
</html>