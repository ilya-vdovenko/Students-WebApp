<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <spring:url value="/resources/images/favicon.png" var="favicon"/>
    <link rel="shortcut icon" type="image/x-icon" href="${favicon}">

    <title>Student WebApp</title>

    <spring:url value="/webjars/bootstrap/css/bootstrap.min.css" var="bootstrapCss"/>
    <link rel="stylesheet" href="${bootstrapCss}" />

    <spring:url value="/webjars/bootstrap-table/bootstrap-table.min.css" var="bootstrapTableCss"/>
    <link rel="stylesheet" href="${bootstrapTableCss}"/>

    <spring:url value="/webjars/font-awesome/css/all.css" var="fontAwesome"/>
    <link rel="stylesheet" href="${fontAwesome}"/>
</head>
