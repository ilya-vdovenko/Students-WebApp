<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!Doctype html>
<html>
<head>
    <title>StudentForm</title>
    <script type="text/javascript"
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/static/StudentForm.js"></script>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/style/form.css">
</head>
<body>
<h2>
    <c:choose>
        <c:when test="${student['new']}">
            <spring:url value="/students" var="studUrl"/>
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
<%--@elvariable id="student" type="StudentsWebAppInitializer"--%>
<sf:form method="POST" modelAttribute="student">
    <div>
        ФИО: <sf:input path="fio" cssStyle="width: 22%"/><br/>
        Дата рождения: <sf:input type="date" path="birthday"/><br/>
        Пол: <sf:select path="sex">
        <sf:option value="муж">муж</sf:option>
        <sf:option value="жен">жен</sf:option>
    </sf:select><br/>
        Факт. адрес: <sf:input path="factAddress" cssStyle="width: 28%"/><br/>
        Адрес: <sf:input path="address" cssStyle="width: 28%"/><br/>
        Контактный тел.: <sf:input path="telephone" cssStyle="width: 10%"/><br/>
        Факультет: <sf:select path="faculty" id="selFac">
        <sf:option value="${student.faculty.id}">${student.faculty.title}</sf:option>
    </sf:select><br/>
        Кафедра: <sf:select path="cathedra" id="selCat">
        <sf:option value="${student.cathedra.id}">${student.cathedra.title}</sf:option>
    </sf:select><br/>
        Группа: <sf:select path="groupClass" id="selGrp">
        <sf:option value="${student.groupClass.id}">${student.groupClass.title}</sf:option>
    </sf:select><br/>
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
