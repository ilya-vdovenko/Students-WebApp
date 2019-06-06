<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!Doctype html>
<html>
<head>
    <title>Employees</title>
</head>
<body>
<h1>Список всех сотрудников института</h1>
<table>
    <thead>
    <tr>
        <th>ФИО</th>
        <th>Должность</th>
        <th>Кафеда</th>
        <th>Факультет</th>
    </tr>
    </thead>
    <tbody>
    <%--suppress ELValidationInJSP --%>
    <c:forEach items="${employee_list}" var="employee">
        <tr>
            <td>
                <spring:url value="employees/{employeId}" var="empUrl">
                    <spring:param name="employeId" value="${employee.id}"/>
                </spring:url>
                <a href="${empUrl}"> <c:out value="${employee.fio}"/> </a>
            </td>
            <td> <c:out value="${employee.position}"/> </td>
            <td>
                <spring:url value="/{facultyId}/cathedras/{cathedraId}" var="catUrl">
                    <spring:param name="facultyId" value="${employee.faculty.id}"/>
                    <spring:param name="cathedraId" value="${employee.cathedra.id}"/>
                </spring:url>
                <a href="${catUrl}"> <c:out value="${employee.cathedra.title}"/></a>
            </td>
            <td>
                <spring:url value="/faculties/{facultyId}" var="facUrl">
                    <spring:param name="facultyId" value="${employee.faculty.id}"/>
                </spring:url>
                <a href="${facUrl}"> <c:out value="${employee.faculty.title}"/></a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>