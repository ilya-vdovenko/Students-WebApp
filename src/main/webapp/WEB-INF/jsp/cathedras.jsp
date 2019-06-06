<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!Doctype html>
<html>
<head>
    <title>Cathedras</title>
</head>
<body>
<h1><spring:url value="faculties/{facultyId}" var="facUrl">
        <spring:param name="facultyId" value="${faculty.id}"/>
    </spring:url>
    <a href="${facUrl}"><c:out value="${faculty.title}"/></a>. Список кафедр:
</h1>
<table>
    <thead>
    <tr>
        <th>Название</th>
        <th>Декан</th>
        <th>Преподаватели</th>
        <th>Студенты</th>
        <th>Группы</th>
    </tr>
    </thead>
    <tbody>
    <%--suppress ELValidationInJSP --%>
    <c:forEach items="${cathedra_list}" var="cathedra">
        <tr>
            <td><c:out value="${cathedra.title}"/></td>
            <td>
                <spring:url value="employees/{employeId}" var="empUrl">
                    <spring:param name="employeId" value="${сathedra.boss.id}"/>
                </spring:url>
                <a href="${empUrl}"> <c:out value="${сathedra.boss.fio}"/> </a>
            </td>

            <!--TODO: set of information and value = "..." -->
            <td><c:out value="${сathedra.lecturers}"/></td>
            <td><c:out value="${сathedra.students}"/></td>
            <td><c:out value="${сathedra.group_classes}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>