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
                <h2>Faculty of "<b><c:out value="${faculty.title}"/></b>"</h2>
                <table class="table table-borderless table-sm unit"
                       aria-describedby="facultyInformation">
                    <tr>
                        <th>Information</th>
                        <td><c:out value="${faculty.information}"/></td>
                    </tr>
                    <tr>
                        <th>Boss</th>
                        <td>
                            <spring:url value="/employees/{employeId}" var="empUrl">
                                <spring:param name="employeId" value="${faculty.boss.id}"/>
                            </spring:url>
                            <a class="link" href="${empUrl}"><c:out value="${faculty.boss.fio}"/></a>
                        </td>
                    </tr>
                    <tr>
                        <th>Telephone</th>
                        <td><c:out value="${faculty.contactInf}"/></td>
                    </tr>
                    <tr>
                        <th>Board</th>
                        <td>
                            <spring:url value="/faculties/{facultyId}/soviet" var="sovUrl">
                                <spring:param name="facultyId" value="${faculty.id}"/>
                            </spring:url>
                            <a class="link" href="${sovUrl}"><c:out value="..."/></a>
                        </td>
                    </tr>
                    <tr>
                        <th>Cathedras</th>
                        <td>
                            <spring:url value="/faculties/{facultyId}/cathedras" var="catUrl">
                                <spring:param name="facultyId" value="${faculty.id}"/>
                            </spring:url>
                            <a class="link" href="${catUrl}"><c:out value="..."/></a>
                        </td>
                    </tr>
                    <tr>
                        <th>Employees</th>
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
