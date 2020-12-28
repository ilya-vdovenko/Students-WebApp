<%@ page session="false" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="studapp" tagdir="/WEB-INF/tags" %>

<studapp:layout pageName="employees" back="true">
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
                <spring:url value="/faculties/{facultyId}" var="facUrl">
                    <spring:param name="facultyId" value="${faculty.id}"/>
                </spring:url>
                <c:choose>
                    <c:when test="${faculty!=null && !board}">
                        <h2><spring:message code="list.unit.employee"/>
                            "<a class="link" href="${facUrl}"><c:out value="${faculty.title}"/></a>"</h2>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${board}">
                                <h2><spring:message code="list.unit.board"/>
                                    "<a class="link" href="${facUrl}"><c:out value="${faculty.title}"/></a>"</h2>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${cathedra!=null}">
                                <spring:url value="/faculties/{facultyId}/cathedras/{cathedraId}" var="catUrl">
                                    <spring:param name="facultyId" value="${cathedra.faculty.id}"/>
                                    <spring:param name="cathedraId" value="${cathedra.id}"/>
                                </spring:url>
                                <h2><spring:message code="list.unit.lect"/>
                                    "<a class="link" href="${catUrl}"><c:out value="${cathedra.title}"/></a>"</h2>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
                <table class="table-sm"
                       data-toggle="table"
                       data-locale="<spring:message code="table.locale"/>"
                       aria-describedby="employees">
                    <thead class="bg-warning text-dark">
                    <tr>
                        <th scope="col" data-width="20" data-width-unit="%"
                            data-sortable="true" data-halign="center">
                            <spring:message code="table.name"/>
                        </th>
                        <th scope="col" data-width="15"
                            data-width-unit="%" data-halign="center">
                            <spring:message code="table.position"/>
                        </th>
                        <th scope="col" data-width="40"
                            data-width-unit="%" data-halign="center">
                            <spring:message code="table.cathedra"/>
                        </th>
                        <th scope="col" data-width="30"
                            data-width-unit="%" data-halign="center">
                            <spring:message code="table.faculty"/>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${employee_list}" var="employee">
                        <tr id="tr">
                            <td>
                                <spring:url value="/employees/{employeId}" var="empUrl">
                                    <spring:param name="employeId" value="${employee.id}"/>
                                </spring:url>
                                <a class="link" href="${empUrl}"><c:out value="${employee.fullName}"/></a>
                            </td>
                            <td><c:out value="${employee.position}"/></td>
                            <td>
                                <spring:url value="/faculties/{facultyId}/cathedras/{cathedraId}" var="catUrl">
                                    <spring:param name="facultyId" value="${employee.faculty.id}"/>
                                    <spring:param name="cathedraId" value="${employee.cathedra.id}"/>
                                </spring:url>
                                <a class="link" href="${catUrl}"><c:out value="${employee.cathedra.title}"/></a>
                            </td>
                            <td>
                                <spring:url value="/faculties/{facultyId}" var="facUrl">
                                    <spring:param name="facultyId" value="${employee.faculty.id}"/>
                                </spring:url>
                                <a class="link" href="${facUrl}"><c:out value="${employee.faculty.title}"/></a>
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
