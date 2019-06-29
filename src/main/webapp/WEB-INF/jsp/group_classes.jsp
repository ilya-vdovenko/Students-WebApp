<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!Doctype html>
<html>
<head>
    <title>Group_classes</title>
    <link rel="stylesheet" type="text/css" href="/resources/style/list.css">
</head>
<body>
<h1><spring:url value="/faculties/{facultyId}/cathedras/{cathedraId}" var="catUrl">
        <spring:param name="facultyId" value="${cathedra.faculty.id}"/>
        <spring:param name="cathedraId" value="${cathedra.id}"/>
    </spring:url>
    <a href="${catUrl}"> <c:out value="${cathedra.title}"/></a>. Список групп:
</h1>
<%--suppress ELValidationInJSP --%>
<c:forEach items="${group_class_list}" var="group_class">
    <p>гр.
        <spring:url value="/faculties/{facultyId}/cathedras/{cathedraId}/group_classes/{group_classId}" var="grclUrl">
            <spring:param name="facultyId" value="${cathedra.faculty.id}"/>
            <spring:param name="cathedraId" value="${cathedra.id}"/>
            <spring:param name="group_classId" value="${group_class.id}"/>
        </spring:url>
        <a href="${grclUrl}"> <c:out value="${group_class.number}"/></a> (<c:out value="${group_class.fos}"/> форма обучения)</p>
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
        <c:forEach items="${group_class.group_students}" var="student">
            <tr>
                <td>
                    <spring:url value="/students/{studentId}" var="studUrl">
                        <spring:param name="studentId" value="${student.id}"/>
                    </spring:url>
                    <a href="${studUrl}"> <c:out value="${student.fio}"/> </a>
                </td>
                <td class="center"> <c:out value="${student.sex}"/> </td>
                <td class="center"> <c:out value="${student.birthday}"/> </td>
                <td class="center"> <c:out value="${student.telephone}"/> </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </c:forEach>
</body>
</html>