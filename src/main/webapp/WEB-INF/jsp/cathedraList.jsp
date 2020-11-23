<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!Doctype html>
<html>
<head>
    <title>Cathedras</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/style/list.css">
</head>
<body>
<h1><spring:url value="/faculties/{facultyId}" var="facUrl">
    <spring:param name="facultyId" value="${faculty.id}"/>
</spring:url>
    <a href="${facUrl}"><c:out value="${faculty.title}"/></a>. Список кафедр:
</h1>
<div style="overflow-x:auto;">
    <table>
        <thead>
        <tr>
            <th>Название</th>
            <th>Заведующий кафедрой</th>
            <th>Преподаватели</th>
            <th>Группы</th>
        </tr>
        </thead>
        <tbody>
        <%--suppress ELValidationInJSP --%>
        <c:forEach items="${cathedra_list}" var="cathedra">
            <tr>
                <td>
                    <spring:url value="/faculties/{facultyId}/cathedras/{cathedraId}" var="catUrl">
                        <spring:param name="facultyId" value="${cathedra.faculty.id}"/>
                        <spring:param name="cathedraId" value="${cathedra.id}"/>
                    </spring:url>
                    <a href="${catUrl}"> <c:out value="${cathedra.title}"/></a>
                </td>
                <td>
                    <spring:url value="/employees/{employeId}" var="empUrl">
                        <spring:param name="employeId" value="${cathedra.boss.id}"/>
                    </spring:url>
                    <a href="${empUrl}"> <c:out value="${cathedra.boss.fio}"/> </a>
                </td>
                <td class="center">
                    <spring:url value="/faculties/{facultyId}/cathedras/{cathedraId}/lecturers"
                                var="lecUrl">
                        <spring:param name="facultyId" value="${cathedra.faculty.id}"/>
                        <spring:param name="cathedraId" value="${cathedra.id}"/>
                    </spring:url>
                    <a href="${lecUrl}"> <c:out value="..."/></a>
                </td>
                <td class="center">
                    <spring:url value="/faculties/{facultyId}/cathedras/{cathedraId}/groupClasses"
                                var="grclUrl">
                        <spring:param name="facultyId" value="${cathedra.faculty.id}"/>
                        <spring:param name="cathedraId" value="${cathedra.id}"/>
                    </spring:url>
                    <a href="${grclUrl}"> <c:out value="..."/></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
