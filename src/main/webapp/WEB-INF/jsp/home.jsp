<%@ page session="false" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="studapp" tagdir="/WEB-INF/tags" %>

<studapp:layout pageName="home" back="false">
    <h2 style="text-align: center">Welcome to Student Webapp</h2>
    <div class="row">
        <div class="container-md">
            <div class="col-md-10">
                <spring:url value="/resources/images/students.png" htmlEscape="true" var="studsImage"/>
                <img class="img-responsive" alt="A students" src="${studsImage}"/>
            </div>
        </div>
    </div>
</studapp:layout>
