<%@ page session="false" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="studapp" tagdir="/WEB-INF/tags" %>

<studapp:layout pageName="home" back="false">
    <div class="row">
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            Example of
            <spring:url value="/oups" var="errorUrl"/>
            <a href="${errorUrl}" class="alert-link">error page</a>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </div>
    <div class="d-flex justify-content-center">
        <h2>Welcome to Student Webapp</h2>
    </div>
    <div class="row">
        <div class="container-md">
            <div class="col-md-10">
                <spring:url value="/resources/images/students.png" htmlEscape="true" var="studsImage"/>
                <img class="img-responsive" alt="A students" src="${studsImage}"/>
            </div>
        </div>
    </div>
</studapp:layout>
