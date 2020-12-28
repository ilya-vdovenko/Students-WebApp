<%@ page session="false" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="studapp" tagdir="/WEB-INF/tags" %>

<studapp:layout pageName="faculties" back="false">
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
                <h1><spring:message code="list.faculty"/></h1>
                <table class="table-sm"
                       data-toggle="table"
                       data-custom-sort="customSort"
                       data-locale="<spring:message code="table.locale"/>"
                       data-remember-order="true"
                       aria-describedby="faculties">
                    <thead class="bg-primary text-white">
                    <tr>
                        <th scope="col" data-width="50" data-width-unit="%"
                            data-sortable="true" data-halign="center">
                            <spring:message code="table.title"/>
                        </th>
                        <th scope="col" data-width="35" data-width-unit="%"
                            data-halign="center">
                            <spring:message code="table.boss"/>
                        </th>
                        <th scope="col" data-width="5" data-width-unit="%"
                            data-halign="center" data-align="center">
                            <spring:message code="table.board"/>
                        </th>
                        <th scope="col" data-width="5" data-width-unit="%"
                            data-halign="center" data-align="center">
                            <spring:message code="table.cathedras"/>
                        </th>
                        <th scope="col" data-width="5" data-width-unit="%"
                            data-halign="center" data-align="center">
                            <spring:message code="table.employees"/>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${faculty_list}" var="faculty">
                        <tr id="tr">
                            <td>
                                <spring:url value="/faculties/{facultyId}" var="facUrl">
                                    <spring:param name="facultyId" value="${faculty.id}"/>
                                </spring:url>
                                <a class="link" href="${facUrl}"><c:out value="${faculty.title}"/></a>
                            </td>

                            <td>
                                <spring:url value="/employees/{employeId}" var="empUrl">
                                    <spring:param name="employeId" value="${faculty.boss.id}"/>
                                </spring:url>
                                <a class="link" href="${empUrl}"><c:out value="${faculty.boss.fullName}"/></a>
                            </td>
                            <td>
                                <spring:url value="/faculties/{facultyId}/board" var="sovUrl">
                                    <spring:param name="facultyId" value="${faculty.id}"/>
                                </spring:url>
                                <a class="link" href="${sovUrl}"><c:out value="..."/></a>
                            <td>
                                <spring:url value="/faculties/{facultyId}/cathedras" var="catUrl">
                                    <spring:param name="facultyId" value="${faculty.id}"/>
                                </spring:url>
                                <a class="link" href="${catUrl}"><c:out value="..."/></a>
                            </td>
                            <td>
                                <spring:url value="/faculties/{facultyId}/employees" var="emplUrl">
                                    <spring:param name="facultyId" value="${faculty.id}"/>
                                </spring:url>
                                <a class="link" href="${emplUrl}"><c:out value="..."/></a>
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
