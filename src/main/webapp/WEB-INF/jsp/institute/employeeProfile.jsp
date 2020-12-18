<%@ page session="false" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="studapp" tagdir="/WEB-INF/tags" %>

<studapp:layout pageName="employees" back="true">
    <jsp:attribute name="customCss">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/profile.css"/>
    </jsp:attribute>
    <jsp:body>
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <h2><strong><c:out value="${employee.fio}"/></strong></h2>
                <table class="table table-borderless table-sm employee"
                       aria-describedby="employeeInformation">
                    <tr>
                        <th scope="row"><spring:message code="table.position"/></th>
                        <td><c:out value="${employee.position}"/></td>
                    </tr>
                    <tr>
                        <th scope="row"><spring:message code="table.degree"/></th>
                        <td><c:out value="${employee.degree}"/></td>
                    </tr>
                    <tr>
                        <th scope="row"><spring:message code="table.cathedra"/></th>
                        <td>
                            <spring:url value="/faculties/{facultyId}/cathedras/{cathedraId}" var="catUrl">
                                <spring:param name="facultyId" value="${employee.faculty.id}"/>
                                <spring:param name="cathedraId" value="${employee.cathedra.id}"/>
                            </spring:url>
                            <a class="link" href="${catUrl}"><c:out value="${employee.cathedra.title}"/></a>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row"><spring:message code="table.faculty"/></th>
                        <td>
                            <spring:url value="/faculties/{facultyId}" var="facUrl">
                                <spring:param name="facultyId" value="${employee.faculty.id}"/>
                            </spring:url>
                            <a class="link" href="${facUrl}"><c:out value="${employee.faculty.title}"/></a>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="col-md-3"></div>
        </div>
    </jsp:body>
</studapp:layout>
