<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="studapp" tagdir="/WEB-INF/tags" %>
<%@ attribute name="name" required="true" rtexprvalue="true"
              description="Name of the active menu: home, students, employees, faculties or error" %>
<%@ attribute name="back" required="true" rtexprvalue="true"
              description="Allow back button on page" %>

<c:choose>
    <c:when test="${name eq 'error'}">
        <nav class="navbar navbar-expand-lg navbar-light bg-danger sticky-top">
    </c:when>
    <c:otherwise>
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary sticky-top">
    </c:otherwise>
</c:choose>
    <c:if test="${back}">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link back">
                    <i class="fas fa-arrow-left"></i> Back</a>
            </li>
        </ul>
    </c:if>
    <c:if test="${name eq 'error'}">
        <ul class="navbar-nav">
            <li class="nav-item">
                <i class="fas fa-exclamation-triangle"></i><b> ERROR</b>
            </li>
        </ul>
    </c:if>
    <button class="navbar-toggler" type="button" data-toggle="collapse"
            data-target="#mainNavbar" aria-controls="mainNavbar"
            aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-end" id="mainNavbar">
        <ul class="navbar-nav">
            <studapp:menuItem active="${name eq 'home'}" url="/">Home</studapp:menuItem>
            <studapp:menuItem active="${name eq 'students'}" url="/students">Students</studapp:menuItem>
            <studapp:menuItem active="${name eq 'employees'}" url="/employees">Employees</studapp:menuItem>
            <studapp:menuItem active="${name eq 'faculties'}" url="/faculties">Faculties</studapp:menuItem>
        </ul>
    </div>
</nav>
