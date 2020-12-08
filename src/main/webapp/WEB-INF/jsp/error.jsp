<%@ page session="false" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="studapp" tagdir="/WEB-INF/tags" %>

<studapp:layout pageName="error" back="false">
    <div class="d-flex justify-content-center">
        <h2>Oups. Something happened...</h2>
    </div>
    <div class="d-flex justify-content-center">
        <p>${exception.message}</p>
    </div>
    <div class="row">
        <div class="container-md">
            <div class="col-md-10">
                <spring:url value="/resources/images/stress work.png" htmlEscape="true" var="studsImage"/>
                <img class="img-responsive" alt="Stress work" src="${studsImage}"/>
            </div>
        </div>
    </div>
</studapp:layout>
