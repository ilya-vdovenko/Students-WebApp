<%@ page session="false" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="studapp" tagdir="/WEB-INF/tags" %>

<studapp:layout pageName="students" back="true">
    <jsp:attribute name="customCss">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/profile.css"/>
    </jsp:attribute>
    <jsp:body>
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <h2><strong><c:out value="${student.fio}"/></strong></h2>
                <table class="table table-borderless table-sm student"
                       aria-describedby="studentInformation">
                    <tr>
                        <th scope="row">BirthDay</th>
                        <td><c:out value="${student.birthday}"/></td>
                    </tr>
                    <tr>
                        <th scope="row">Sex</th>
                        <td><c:out value="${student.sex}"/></td>
                    </tr>
                    <tr>
                        <th scope="row">Actual address</th>
                        <td><c:out value="${student.factAddress}"/></td>
                    </tr>
                    <tr>
                        <th scope="row">Address</th>
                        <td><c:out value="${student.address}"/></td>
                    </tr>
                    <tr>
                        <th scope="row">Telephone</th>
                        <td><c:out value="${student.telephone}"/></td>
                    </tr>
                    <tr>
                        <th scope="row">Group</th>
                        <td>
                            <spring:url
                                    value="/faculties/{facultyId}/cathedras/{cathedraId}/groupClasses/{groupClassId}"
                                    var="grclUrl">
                                <spring:param name="facultyId" value="${student.faculty.id}"/>
                                <spring:param name="cathedraId" value="${student.cathedra.id}"/>
                                <spring:param name="groupClassId" value="${student.groupClass.id}"/>
                            </spring:url>
                            <a class="link" href="${grclUrl}"><c:out value="${student.groupClass.title}"/></a>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">Cathedra</th>
                        <td>
                            <spring:url value="/faculties/{facultyId}/cathedras/{cathedraId}" var="catUrl">
                                <spring:param name="facultyId" value="${student.faculty.id}"/>
                                <spring:param name="cathedraId" value="${student.cathedra.id}"/>
                            </spring:url>
                            <a class="link" href="${catUrl}"><c:out value="${student.cathedra.title}"/></a>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">Faculty</th>
                        <td>
                            <spring:url value="/faculties/{facultyId}" var="facUrl">
                                <spring:param name="facultyId" value="${student.faculty.id}"/>
                            </spring:url>
                            <a class="link" href="${facUrl}"><c:out value="${student.faculty.title}"/></a>
                        </td>
                    </tr>
                </table>
                <div>
                    <spring:url value="/students/{studentId}/edit" var="editUrl">
                        <spring:param name="studentId" value="${student.id}"/>
                    </spring:url>
                    <a href="${editUrl}" class="btn btn-info">Edit student</a>
                </div>
            </div>
            <div class="col-md-3"></div>
        </div>
    </jsp:body>
</studapp:layout>
