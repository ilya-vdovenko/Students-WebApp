<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!Doctype html>
<html>
<head>
    <title>StudentForm</title>
    <link rel="stylesheet" type="text/css" href="/resources/style/form.css">
</head>
<body>
<h2>
    <c:choose>
        <c:when test="${student['new']}">
            <spring:url value="/students" var="studUrl" />
            Добавление студента
        </c:when>
        <c:otherwise>
            <spring:url value="/students/{studentId}" var="studUrl">
                <spring:param name="studentId" value="${student.id}"/>
            </spring:url>
            Редактирование студента
        </c:otherwise>
    </c:choose>
</h2>
<sf:form method="POST" modelAttribute="student">
<div>
    ФИО: <sf:input path="fio" cssStyle="width: 22%;"/>
    <!--Дата рождения: <sf:input type="date" path="birthday" /><br/>-->
    Пол: <sf:select path="sex">
            <sf:option value="муж">муж</sf:option>
            <sf:option value="жен">жен</sf:option>
         </sf:select><br/>
    Факт. адрес: <sf:input path="fact_address" cssStyle="width: 28%;"/><br/>
    Адрес: <sf:input path="address" cssStyle="width: 28%;:"/><br/>
    Контактный тел.: <sf:input path="telephone" cssStyle="width: 10%;"/><br/>

    <!--there must be selection from db
    что если послылать сюда списки существующих факов, кафедр и т.д. без обновления из бд
    HTML5 <datalist> Element use
    Факультет:  <sf:select path="faculty" >
                    <c:forEach items="${sqlresult_fac.rows}" var="faculty" >
                        <sf:option value="${faculty}">${faculty.title}</sf:option>
                    </c:forEach>
                </sf:select>
    Кафедра: <sf:select path="cathedra" >
                <c:forEach items="${sqlresult_caf.rows}" var="cathedra" >
                    <sf:option value="${cathedra}">${cathedra.title}</sf:option>
                </c:forEach>
            </sf:select>

    Группа: <sf:select path="group_class" >
                <c:forEach items="${sqlresult_gr.rows}" var="group" >
                    <sf:option value="${group}">${group.name}</sf:option>
                </c:forEach>
            </sf:select>
            -->
</div>
<div>
    <button type="submit">
        <c:choose>
            <c:when test="${student['new']}">Добавить</c:when>
            <c:otherwise>Сохранить изменения</c:otherwise>
        </c:choose>
    </button>
</div>
<div>
    <a href="${studUrl}">Отмена</a>
</div>
</sf:form>
</body>
</html>