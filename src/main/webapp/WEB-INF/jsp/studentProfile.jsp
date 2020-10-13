<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!Doctype html>
<html>
<head>
    <title>StudentProfile</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style/profile.css">
</head>
<body>
<h1><b><c:out value="${student.fio}"/></b></h1>
<table>
    <tr>
        <th>Дата рождения</th>
        <td><c:out value="${student.birthday}"/></td>
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
        <th>Группа</th>
        <td>
            <spring:url value="/faculties/{facultyId}/cathedras/{cathedraId}/group_classes/{group_classId}"
                        var="grclUrl">
                <spring:param name="facultyId" value="${student.faculty.id}"/>
                <spring:param name="cathedraId" value="${student.cathedra.id}"/>
                <spring:param name="group_classId" value="${student.group_class.id}"/>
            </spring:url>
            <a href="${grclUrl}"> <c:out value="${student.group_class.title}"/></a>
        </td>
    </tr>
    <tr>
        <th>Кафедра</th>
        <td>
            <spring:url value="/faculties/{facultyId}/cathedras/{cathedraId}" var="catUrl">
                <spring:param name="facultyId" value="${student.faculty.id}"/>
                <spring:param name="cathedraId" value="${student.cathedra.id}"/>
            </spring:url>
            <a href="${catUrl}"> <c:out value="${student.cathedra.title}"/></a>
        </td>
    </tr>
    <tr>
        <th>Факультет</th>
        <td>
            <spring:url value="/faculties/{facultyId}" var="facUrl">
                <spring:param name="facultyId" value="${student.faculty.id}"/>
            </spring:url>
            <a href="${facUrl}"> <c:out value="${student.faculty.title}"/></a>
        </td>
    </tr>
</table>
<div id="UpdateButton">
    <spring:url value="/students/{studentId}/edit" var="editUrl">
        <spring:param name="studentId" value="${student.id}"/>
    </spring:url>
    <a href="${editUrl}">Редактировать профиль</a>
</div>
</body>
</html>
