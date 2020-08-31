<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!Doctype html>
<html>
<head>
    <title>Students</title>
    <link rel="stylesheet" type="text/css" href="/resources/style/list.css">
</head>
<body>
<h1>Список всех студентов института</h1>
<div style="overflow-x:auto;">
    <table>
        <thead>
        <tr>
            <th>ФИО</th>
            <th>Пол</th>
            <th>Дата рождения</th>
            <th>Факт. адрес</th>
            <th>Контактный тел.</th>
            <th>Группа</th>
            <th>Кафедра</th>
            <th>Факультет</th>
        </tr>
        </thead>
        <tbody>
        <%--suppress ELValidationInJSP --%>
        <c:forEach items="${student_list}" var="student">
            <tr>
                <td>
                    <spring:url value="/students/{studentId}" var="studUrl">
                        <spring:param name="studentId" value="${student.id}"/>
                    </spring:url>
                    <a href="${studUrl}"> <c:out value="${student.fio}"/> </a>
                </td>
                <td class="center"><c:out value="${student.sex}"/></td>
                <td class="center"><c:out value="${student.birthday}"/></td>
                <td><c:out value="${student.fact_address}"/></td>
                <td class="center"><c:out value="${student.telephone}"/></td>
                <td class="center">
                    <spring:url value="/faculties/{facultyId}/cathedras/{cathedraId}/group_classes/{group_classId}"
                                var="grclUrl">
                        <spring:param name="facultyId" value="${student.faculty.id}"/>
                        <spring:param name="cathedraId" value="${student.cathedra.id}"/>
                        <spring:param name="group_classId" value="${student.group_class.id}"/>
                    </spring:url>
                    <a href="${grclUrl}"> <c:out value="${student.group_class.title}"/></a>
                </td>
                <td>
                    <spring:url value="/faculties/{facultyId}/cathedras/{cathedraId}" var="catUrl">
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
</div>
<div id="AddButton">
    <spring:url value="/students/new" var="newUrl"/>
    <a href="${newUrl}">Добавить студента</a>
</div>
</body>
</html>
