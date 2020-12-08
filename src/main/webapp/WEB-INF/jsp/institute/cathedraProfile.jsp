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
                <h2>Cathedra of "<strong><c:out value="${cathedra.title}"/></strong>"</h2>
                <table class="table table-borderless table-sm unit"
                       aria-describedby="cathedraInformation">
                    <tr>
                        <th scope="row">Information</th>
                        <td><c:out value="${cathedra.information}"/><td>
                    </tr>
                    <tr>
                        <th scope="row">Boss</th>
                        <td>
                            <spring:url value="/employees/{employeId}" var="empUrl">
                                <spring:param name="employeId" value="${cathedra.boss.id}"/>
                            </spring:url>
                            <a class="link" href="${empUrl}"><c:out value="${cathedra.boss.fio}"/></a>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">Faculty</th>
                        <td>
                            <spring:url value="/faculties/{facultyId}" var="facUrl">
                                <spring:param name="facultyId" value="${cathedra.faculty.id}"/>
                            </spring:url>
                            <a class="link" href="${facUrl}"><c:out value="${cathedra.faculty.title}"/></a>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">Telephone</th>
                        <td><c:out value="${cathedra.contactInf}"/></td>
                    </tr>
                    <tr>
                        <th scope="row">Education program</th>
                        <td><c:out value="${cathedra.eduPrograms}"/></td>
                    </tr>
                    <tr>
                        <th scope="row">Lecturers</th>
                        <td><spring:url value="/faculties/{facultyId}/cathedras/{cathedraId}/lecturers" var="lecUrl">
                                <spring:param name="facultyId" value="${cathedra.faculty.id}"/>
                                <spring:param name="cathedraId" value="${cathedra.id}"/>
                            </spring:url>
                            <a class="link" href="${lecUrl}"><c:out value="..."/></a>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">Groups</th>
                        <td>
                            <spring:url value="/faculties/{facultyId}/cathedras/{cathedraId}/groupClasses" var="grclUrl">
                                <spring:param name="facultyId" value="${cathedra.faculty.id}"/>
                                <spring:param name="cathedraId" value="${cathedra.id}"/>
                            </spring:url>
                            <a class="link" href="${grclUrl}"><c:out value="..."/></a>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="col-md-3"></div>
        </div>
    </jsp:body>
</studapp:layout>
