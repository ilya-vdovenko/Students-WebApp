<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <title>Group_classProfile</title>
</head>
<body>
<h1><b><c:out value="${group_class.number}"/></b></h1>
<table>
    <tr>
        <th>Форма обучения</th>
        <td><c:out value="${group_class.fos}"/><td>
    </tr>
    <tr>
        <th>Кафедра</th>
        <td>
            <spring:url value="/faculties/{facultyId}/cathedras/{cathedraId}" var="catUrl">
                <spring:param name="facultyId" value="${group_class.faculty.id}"/>
                <spring:param name="cathedraId" value="${group_class.cathedra.id}"/>
            </spring:url>
            <a href="${catUrl}"> <c:out value="${group_class.cathedra.title}"/></a>
        </td>
    </tr>
    <tr>
        <th>Факультет</th>
        <td>
            <spring:url value="/faculties/{facultyId}" var="facUrl">
                <spring:param name="facultyId" value="${cathedra.faculty.id}"/>
            </spring:url>
            <a href="${facUrl}"> <c:out value="${group_class.faculty.title}"/> </a>
    </tr>
</table>
<%--suppress ELValidationInJSP --%>
<p>Студенты группы:</p>
<table>
    <thead>
    <tr>
        <th>ФИО</th>
        <th>Пол</th>
        <th>Дата рождения</th>
        <th>Контактный тел.</th>
    </tr>
    </thead>
    <tbody>
    <%--suppress ELValidationInJSP --%>
    <c:forEach items="${group_students_list}" var="student">
        <tr>
            <td>
                <spring:url value="/students/{studentId}" var="studUrl">
                    <spring:param name="studentId" value="${student.id}"/>
                </spring:url>
                <a href="${studUrl}"> <c:out value="${student.fio}"/> </a>
            </td>
            <td> <c:out value="${student.sex}"/> </td>
            <td> <c:out value="${student.birthday}"/> </td>
            <td> <c:out value="${student.telephone}"/> </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>