<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!Doctype html>
<html>
<head>
    <title>EmployeeProfile</title>
    <link rel="stylesheet" type="text/css" href="/resources/style/profile.css">
</head>
<body>
<h1><b><c:out value="${employee.fio}"/></b></h1>
<table>
    <tr>
        <th>Должность</th>
        <td><c:out value="${employee.position}"/></td>
    </tr>
    <tr>
        <th>Уч.степень</th>
        <td><c:out value="${employee.degree}"/></td>
    </tr>
    <tr>
        <th>Кафедра</th>
        <td>
            <spring:url value="/faculties/{facultyId}/cathedras/{cathedraId}" var="catUrl">
                <spring:param name="facultyId" value="${employee.faculty.id}"/>
                <spring:param name="cathedraId" value="${employee.cathedra.id}"/>
            </spring:url>
            <a href="${catUrl}"> <c:out value="${employee.cathedra.title}"/></a>
        </td>
    </tr>
    <tr>
        <th>Факультет</th>
        <td>
            <spring:url value="/faculties/{facultyId}" var="facUrl">
                <spring:param name="facultyId" value="${employee.faculty.id}"/>
            </spring:url>
            <a href="${facUrl}"> <c:out value="${employee.faculty.title}"/></a>
        </td>
    </tr>
</table>
</body>
</html>