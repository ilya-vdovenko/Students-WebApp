<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!Doctype html>
<html>
<head>
    <title>Faculties</title>
</head>
<body>
<h1>Список всех факультетов института</h1>
<table>
    <thead>
    <tr>
        <th>Название</th>
        <th>Информация</th>
        <th>Декан</th>
        <th>Контактная инф.</th>
        <th>Совет</th>
        <th>Кафедры</th>
        <th>Студенты</th>
        <th>Сотрудники</th>
        <th>Группы</th>
    </tr>
    </thead>
    <tbody>
    <%--suppress ELValidationInJSP --%>
    <c:forEach items="${faculty_list}" var="faculty">
        <tr>
            <td>
                <spring:url value="faculties/{facultyId}" var="facUrl">
                    <spring:param name="facultyId" value="${faculty.id}"/>
                </spring:url>
                <a href="${facUrl}"> <c:out value="${faculty.title}"/> </a>
            </td>
            <td> <c:out value="${faculty.information}"/> </td>
            <td> <c:out value="${faculty.boss}"/> </td>
            <td> <c:out value="${faculty.contact_inf}"/> </td>
            <td> <c:out value="${faculty.soviet}"/> </td>
            <td> <c:out value="${faculty.cathedras}"/> </td>
            <td> <c:out value="${faculty.students}"/> </td>
            <td> <c:out value="${faculty.employees}"/> </td>
            <td> <c:out value="${faculty.group_classes}"/> </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>