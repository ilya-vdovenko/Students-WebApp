<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!Doctype html>
<html>
<head>
    <title>StudentForm</title>
</head>
<body>
<h2>
    <c:choose>
        <c:when test="${student['new']}"> Добавление студента </c:when>
        <c:otherwise> Редактирование студента </c:otherwise>
    </c:choose>
</h2>
<sf:form method="POST" modelAttribute="student">
<div>
    ФИО:<sf:input path="fio" /><br/>
    <!--Дата рождения: <sf:input type="date" path="birthday" /><br/>-->
    Пол: <sf:select path="sex">
            <sf:option selected="selected" value="m">муж</sf:option>
            <sf:option value="w">жен</sf:option>
        </sf:select>
    Факт. адрес: <sf:input path="fact_address" /><br/>
    Адрес: <sf:input path="address" /><br/>
    Контактный тел.: <sf:input path="telephone" /><br/>
    <!--Факультет: <sf:input path="faculty" /><br/>
    Кафедра: <sf:input path="cathedra" /><br/>
    Группа: <sf:input path="group_class" /><br/>-->
</div>
    <div>
        <div>
            <c:choose>
                <c:when test="${student['new']}">
                    <button type="submit">Добавить</button>
                </c:when>
                <c:otherwise>
                    <button type="submit">Сохранить изменения</button>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</sf:form>
</body>
</html>