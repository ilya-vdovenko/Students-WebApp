<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url value="/webjars/jquery/jquery.min.js" var="jQuery"/>
<script src="${jQuery}"></script>

<spring:url value="/webjars/popper.js/umd/popper.min.js" var="popperJs"/>
<script src="${popperJs}"></script>

<spring:url value="/webjars/bootstrap/js/bootstrap.min.js" var="bootstrapJs"/>
<script src="${bootstrapJs}"></script>

<spring:url value="/webjars/bootstrap-table/bootstrap-table.min.js" var="bootstrapTableJs"/>
<script src="${bootstrapTableJs}"></script>

<spring:url value="/webjars/bootstrap-table/bootstrap-table-locale-all.js" var="bootstrapLocaleJs"/>
<script src="${bootstrapLocaleJs}"></script>
