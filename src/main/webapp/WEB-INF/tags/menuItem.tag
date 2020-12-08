<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ attribute name="active" required="true" rtexprvalue="true" %>
<%@ attribute name="url" required="true" rtexprvalue="true" %>

<li class="navbar-nav ${active ? 'active' : ''}">
    <a class="nav-link" href="<spring:url value="${url}" htmlEscape="true" />">
        <jsp:doBody/>
    </a>
</li>
