<%@ page session="false" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="studapp" tagdir="/WEB-INF/tags" %>

<studapp:layout pageName="faculties" back="true">
    <jsp:attribute name="customCss">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/list.css"/>
    </jsp:attribute>
    <jsp:body>
        <h2><spring:url value="/faculties/{facultyId}/cathedras/{cathedraId}" var="catUrl">
            <spring:param name="facultyId" value="${cathedra.faculty.id}"/>
            <spring:param name="cathedraId" value="${cathedra.id}"/>
        </spring:url>
            <spring:message code="list.group"/> "<a class="link" href="${catUrl}"><c:out value="${cathedra.title}"/></a>"
        </h2>
        <c:forEach items="${group_class_list}" var="groupClass">
            <p>
                <spring:url value="/faculties/{facultyId}/cathedras/{cathedraId}/groupClasses/{groupClassId}" var="grclUrl">
                    <spring:param name="facultyId" value="${cathedra.faculty.id}"/>
                    <spring:param name="cathedraId" value="${cathedra.id}"/>
                    <spring:param name="groupClassId" value="${groupClass.id}"/>
                </spring:url>
                <spring:message code="list.group.ab"/>
                <a class="link" href="${grclUrl}"> <c:out value="${groupClass.title}"/></a>
                (<c:out value="${groupClass.eduForm} "/><spring:message code="list.group.eduform"/>)
            </p>
            <p><spring:message code="list.group.student"/></p>
            <div class="col-md-5">
            <table class="table-sm"
                   data-toggle="table"
                   data-locale="<spring:message code="table.locale"/>"
                   aria-describedby="students">
                <thead class="bg-success text-white">
                <tr>
                    <th scope="col" data-width="50" data-width-unit="%"
                        data-halign="center"><spring:message code="table.name"/></th>
                    <th scope="col" data-width="10" data-width-unit="%"
                        data-halign="center" data-align="center">
                        <spring:message code="table.sex"/></th>
                    <th scope="col" data-width="20" data-width-unit="%"
                        data-halign="center" data-align="center">
                        <spring:message code="table.birthday"/></th>
                    <th scope="col" data-width="20" data-width-unit="%"
                        data-halign="center" data-align="center">
                        <spring:message code="table.telephone"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${groupClass.groupStudents}" var="student">
                    <tr id="tr">
                        <td>
                            <spring:url value="/students/{studentId}" var="studUrl">
                                <spring:param name="studentId" value="${student.id}"/>
                            </spring:url>
                            <a class="link" href="${studUrl}"><c:out value="${student.fullName}"/></a>
                        </td>
                        <td><c:out value="${student.sex}"/></td>
                        <td><c:out value="${student.birthday}"/></td>
                        <td><c:out value="${student.telephone}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            </div><br>
        </c:forEach>
    </jsp:body>
</studapp:layout>
