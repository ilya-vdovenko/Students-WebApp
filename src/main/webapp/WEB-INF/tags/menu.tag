<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
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
                <a class="nav-link" href="javascript:history.back()">
                    <i class="fas fa-arrow-left"></i> <spring:message code="menu.back"/></a>
            </li>
        </ul>
    </c:if>
    <c:if test="${name eq 'error'}">
        <ul class="navbar-nav">
            <li class="nav-item">
                <i class="fas fa-exclamation-triangle"></i><b> <spring:message code="menu.error"/></b>
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
            <studapp:menuItem active="${name eq 'home'}" url="/"><spring:message code="menu.home"/></studapp:menuItem>
            <studapp:menuItem active="${name eq 'students'}" url="/students"><spring:message code="menu.students"/></studapp:menuItem>
            <studapp:menuItem active="${name eq 'employees'}" url="/employees"><spring:message code="menu.employees"/></studapp:menuItem>
            <studapp:menuItem active="${name eq 'faculties'}" url="/faculties"><spring:message code="menu.faculties"/></studapp:menuItem>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle text-uppercase"
                   id="navbarDropdown" role="button"
                   data-toggle="dropdown" aria-haspopup="true"
                   aria-expanded="false">
                    ${pageContext.response.locale}
                </a>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="?locale=en">English</a>
                    <a class="dropdown-item" href="?locale=de">Deutsche</a>
                    <a class="dropdown-item" href="?locale=ru">Русский</a>
                </div>
            </li>
        </ul>
    </div>
</nav>
