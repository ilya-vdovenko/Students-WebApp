<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!Doctype html>
<html>
<head>
    <title>Faculties</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/style/list.css">
</head>
<body>
<h1>Список всех факультетов института</h1>
<div style="overflow-x:auto;">
    <table>
        <thead>
        <tr>
            <th>Название</th>
            <th>Декан</th>
            <th>Совет</th>
            <th>Кафедры</th>
            <th>Сотрудники</th>
        </tr>
        </thead>
        <tbody>
        <%--suppress ELValidationInJSP --%>
        <c:forEach items="${faculty_list}" var="faculty">
            <tr>
                <td>
                    <spring:url value="/faculties/{facultyId}" var="facUrl">
                        <spring:param name="facultyId" value="${faculty.id}"/>
                    </spring:url>
                    <a href="${facUrl}"> <c:out value="${faculty.title}"/> </a>
                </td>

                <td>
                    <spring:url value="/employees/{employeId}" var="empUrl">
                        <spring:param name="employeId" value="${faculty.boss.id}"/>
                    </spring:url>
                    <a href="${empUrl}"> <c:out value="${faculty.boss.fio}"/> </a>
                </td>
                <td class="center">
                    <spring:url value="/faculties/{facultyId}/soviet" var="sovUrl">
                        <spring:param name="facultyId" value="${faculty.id}"/>
                    </spring:url>
                    <a href="${sovUrl}"> <c:out value="..."/></a>
                <td class="center">
                    <spring:url value="/faculties/{facultyId}/cathedras" var="catUrl">
                        <spring:param name="facultyId" value="${faculty.id}"/>
                    </spring:url>
                    <a href="${catUrl}"> <c:out value="..."/></a>
                </td>
                <td class="center">
                    <spring:url value="/faculties/{facultyId}/employees" var="emplUrl">
                        <spring:param name="facultyId" value="${faculty.id}"/>
                    </spring:url>
                    <a href="${emplUrl}"> <c:out value="..."/></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
