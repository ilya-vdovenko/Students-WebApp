<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!Doctype html>
<html>
<head>
    <title>Students</title>
</head>
<body>
<h1>Список всех студентов института</h1>
<table>
    <thead>
    <tr>
        <th>ФИО</th>
        <th>Пол</th>
        <th>Дата рождения</th>
        <th>Факт. адрес</th>
        <th>Контактный тел.</th>
        <th>Группа</th>
        <th>Кафеда</th>
        <th>Факультет</th>
    </tr>
    </thead>
    <tbody>
<%--suppress ELValidationInJSP --%>
    <c:forEach items="${student_list}" var="student">
        <tr>
            <td>
                <spring:url value="students/{studentId}" var="studUrl">
                    <spring:param name="studentId" value="${student.id}"/>
                </spring:url>
                <a href="${studUrl}"> <c:out value="${student.fio}"/> </a>
            </td>
            <td> <c:out value="${student.sex}"/> </td>
            <td> <c:out value="${student.birthday}"/> </td>
            <td> <c:out value="${student.fact_address}"/> </td>
            <td> <c:out value="${student.telephone}"/> </td>
            <td>
                <!-- TODO: ref to groupProfile -->
                <spring:url value="/" var="gcUrl">
                    <spring:param name="group_classId" value="${student.group_class.id}"/>
                </spring:url>
                <a href="${gcUrl}"> <c:out value="${student.group_class.name}"/></a>
            </td>
            <td>
                <spring:url value="/{facultyId}/cathedras/{cathedraId}" var="catUrl">
                    <spring:param name="facultyId" value="${student.faculty.id}"/>
                    <spring:param name="cathedraId" value="${student.cathedra.id}"/>
                </spring:url>
                <a href="${catUrl}"> <c:out value="${student.cathedra.title}"/></a>
            </td>
            <td>
                <spring:url value="/faculties/{facultyId}" var="facUrl">
                    <spring:param name="facultyId" value="${student.faculty.id}"/>
                </spring:url>
                <a href="${facUrl}"> <c:out value="${student.faculty.title}"/></a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div>
    <spring:url value="students/new" var="newUrl"/>
    <a href="${newUrl}">Добавить студента</a>
</div>
</body>
</html>