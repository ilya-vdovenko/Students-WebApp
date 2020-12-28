<%@ page session="false" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="studapp" tagdir="/WEB-INF/tags" %>

<studapp:layout pageName="faculties" back="true">
    <jsp:attribute name="customCss">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/profile.css"/>
    </jsp:attribute>
    <jsp:body>
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <h2><spring:message code="profile.faculty"/> "<strong><c:out value="${faculty.title}"/></strong>"</h2>
                <table class="table table-borderless table-sm unit"
                       aria-describedby="facultyInformation">
                    <tr>
                        <th scope="row"><spring:message code="table.info"/></th>
                        <td><c:out value="${faculty.information}"/></td>
                    </tr>
                    <tr>
                        <th scope="row"><spring:message code="table.boss"/></th>
                        <td>
                            <spring:url value="/employees/{employeId}" var="empUrl">
                                <spring:param name="employeId" value="${faculty.boss.id}"/>
                            </spring:url>
                            <a class="link" href="${empUrl}"><c:out value="${faculty.boss.fullName}"/></a>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row"><spring:message code="table.telephone"/></th>
                        <td><c:out value="${faculty.contactInf}"/></td>
                    </tr>
                    <tr>
                        <th scope="row"><spring:message code="table.board"/></th>
                        <td>
                            <spring:url value="/faculties/{facultyId}/board" var="sovUrl">
                                <spring:param name="facultyId" value="${faculty.id}"/>
                            </spring:url>
                            <a class="link" href="${sovUrl}"><c:out value="..."/></a>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row"><spring:message code="table.cathedras"/></th>
                        <td>
                            <spring:url value="/faculties/{facultyId}/cathedras" var="catUrl">
                                <spring:param name="facultyId" value="${faculty.id}"/>
                            </spring:url>
                            <a class="link" href="${catUrl}"><c:out value="..."/></a>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row"><spring:message code="table.employees"/></th>
                        <td>
                            <spring:url value="/faculties/{facultyId}/employees" var="emplUrl">
                                <spring:param name="facultyId" value="${faculty.id}"/>
                            </spring:url>
                            <a class="link" href="${emplUrl}"><c:out value="..."/></a>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="col-md-3"></div>
        </div>
    </jsp:body>
</studapp:layout>
