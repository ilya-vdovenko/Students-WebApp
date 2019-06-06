<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
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
    ФИО: <sf:input path="fio" /><br/>
    <!--Дата рождения: <sf:input type="date" path="birthday" /><br/>-->
    Пол: <sf:select path="sex">
            <sf:option value="муж">муж</sf:option>
            <sf:option value="жен">жен</sf:option>
        </sf:select>
    Факт. адрес: <sf:input path="fact_address" /><br/>
    Адрес: <sf:input path="address" /><br/>
    Контактный тел.: <sf:input path="telephone" /><br/>

    <jsp:useBean id="dataSource" scope="application" class="org.apache.tomcat.jdbc.pool.DataSource"/>
    <sql:setDataSource dataSource="jdbc:hsqldb:mem:students-app, org.hsqldb.jdbcDriver, sa, "/>
    <sql:query var="sqlresult_fac" sql="select id, title from faculties"/>
    Факультет:  <sf:select path="faculty" >
                    <c:forEach items="${sqlresult_fac.rows}" var="faculty" >
                        <sf:option value="${faculty}">${faculty.title}</sf:option>
                    </c:forEach>
                </sf:select>

    <sql:query var="sqlresult_caf" sql="select id, title from cathedras where faculty_id = ?" >
        <sql:param value="1" />
    </sql:query>
    Кафедра: <sf:select path="cathedra" >
                <c:forEach items="${sqlresult_caf.rows}" var="cathedra" >
                    <sf:option value="${cathedra}">${cathedra.title}</sf:option>
                </c:forEach>
            </sf:select>

    <sql:query var="sqlresult_gr" sql="select id, name from group_classes where cathedra_id = ?" >
        <sql:param value="1" />
    </sql:query>
    Группа: <sf:select path="group_class" >
                <c:forEach items="${sqlresult_gr.rows}" var="group" >
                    <sf:option value="${group}">${group.name}</sf:option>
                </c:forEach>
            </sf:select>
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