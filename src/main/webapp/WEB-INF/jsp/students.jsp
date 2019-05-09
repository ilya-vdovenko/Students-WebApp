<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!Doctype html>
<html>
<head>
    <title>Students</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>ФИО</th>
        <th>Дата рождения</th>
        <th>Пол</th>
        <th>Факт. адрес</th>
        <th>Адрес</th>
        <th>Контактный тел.</th>
        <th>Факультет</th>
        <th>Группа</th>
        <th>Форма обучения</th>
    </tr>
    </thead>
    <tbody>
<%--suppress ELValidationInJSP --%>
<c:forEach items="${student_list}" var="student">
        <tr>
            <td>
                <c:out value="${student.fio}"/>
            </td>
            <td>
                <c:out value="${student.birthday}"/>
            </td>
            <td>
                <c:out value="${student.sex}"/>
            </td>
            <td>
                <c:out value="${student.fact_address}"/>
            </td>
            <td>
                <c:out value="${student.address}"/>
            </td>
            <td>
                <c:out value="${student.telephone}"/>
            </td>
            <td>
                <c:out value="${student.faculty}"/>
            </td>
            <td>
                <c:out value="${student.group_class}"/>
            </td>
            <td>
                <c:out value="${student.fos}"/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>