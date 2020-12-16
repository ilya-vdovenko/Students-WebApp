<%@ page session="false" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="studapp" tagdir="/WEB-INF/tags" %>

<studapp:layout pageName="cathedras" back="true">
    <jsp:attribute name="customScript">
        <script src="${pageContext.request.contextPath}/resources/static/SortByName.js"></script>
    </jsp:attribute>
    <jsp:attribute name="customCss">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/list.css"/>
    </jsp:attribute>
    <jsp:body>
        <div class="row">
            <div class="col-md-1"></div>
            <div class="col-md-10">
                <h2><spring:url value="/faculties/{facultyId}" var="facUrl">
                    <spring:param name="facultyId" value="${faculty.id}"/>
                </spring:url>
                    <spring:message code="list.cathedra"/>
                    "<a class="link" href="${facUrl}"><c:out value="${faculty.title}"/></a>"
                </h2>
                <table class="table-sm"
                       data-toggle="table"
                       data-custom-sort="customSort"
                       data-locale="<spring:message code="table.locale"/>"
                       data-remember-order="true"
                       aria-describedby="cathedras">
                    <thead class="bg-primary text-white">
                    <tr>
                        <th scope="col" data-halign="center" data-sortable="true">
                            <spring:message code="table.title"/></th>
                        <th scope="col" data-halign="center">
                            <spring:message code="table.boss"/></th>
                        <th scope="col" data-halign="center" data-align="center">
                            <spring:message code="table.lect"/></th>
                        <th scope="col" data-halign="center" data-align="center">
                            <spring:message code="table.groups"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${cathedra_list}" var="cathedra">
                        <tr id="tr">
                            <td>
                                <spring:url value="/faculties/{facultyId}/cathedras/{cathedraId}" var="catUrl">
                                    <spring:param name="facultyId" value="${cathedra.faculty.id}"/>
                                    <spring:param name="cathedraId" value="${cathedra.id}"/>
                                </spring:url>
                                <a class="link" href="${catUrl}"><c:out value="${cathedra.title}"/></a>
                            </td>
                            <td>
                                <spring:url value="/employees/{employeId}" var="empUrl">
                                    <spring:param name="employeId" value="${cathedra.boss.id}"/>
                                </spring:url>
                                <a class="link" href="${empUrl}"><c:out value="${cathedra.boss.fio}"/> </a>
                            </td>
                            <td>
                                <spring:url value="/faculties/{facultyId}/cathedras/{cathedraId}/lecturers" var="lecUrl">
                                    <spring:param name="facultyId" value="${cathedra.faculty.id}"/>
                                    <spring:param name="cathedraId" value="${cathedra.id}"/>
                                </spring:url>
                                <a class="link" href="${lecUrl}"><c:out value="..."/></a>
                            </td>
                            <td>
                                <spring:url value="/faculties/{facultyId}/cathedras/{cathedraId}/groupClasses" var="grclUrl">
                                    <spring:param name="facultyId" value="${cathedra.faculty.id}"/>
                                    <spring:param name="cathedraId" value="${cathedra.id}"/>
                                </spring:url>
                                <a class="link" href="${grclUrl}"><c:out value="..."/></a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col-md-1"></div>
        </div>
    </jsp:body>
</studapp:layout>
