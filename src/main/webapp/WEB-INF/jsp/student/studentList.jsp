<%@ page session="false" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="studapp" tagdir="/WEB-INF/tags" %>

<studapp:layout pageName="students" back="false">
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
                <h1><spring:message code="list.student"/></h1>
                <table class="table-sm"
                       data-toggle="table"
                       data-pagination="true"
                       data-page-list="[10, 25, 50, 100, All]"
                       data-custom-sort="customSort"
                       data-locale="<spring:message code="table.locale"/>"
                       data-search="true"
                       data-show-search-clear-button="true"
                       data-show-search-button="true"
                       data-remember-order="true"
                       aria-describedby="students">
                    <thead class="bg-success text-white">
                    <tr>
                        <th scope="col" data-width="20" data-width-unit="%" data-sortable="true"
                            data-halign="center"><spring:message code="table.name"/></th>
                        <th scope="col" data-width="5" data-width-unit="%" data-halign="center"
                            data-align="center"><spring:message code="table.sex"/></th>
                        <th scope="col" data-width="10" data-width-unit="%" data-sortable="true"
                            data-halign="center" data-align="center"><spring:message code="table.birthday"/></th>
                        <th scope="col" data-width="10" data-width-unit="%" data-halign="center"
                            data-align="center"><spring:message code="table.telephone"/></th>
                        <th scope="col" data-width="5" data-width-unit="%" data-sortable="true"
                            data-halign="center" data-align="center"><spring:message code="table.group"/></th>
                        <th scope="col" data-width="30" data-width-unit="%" data-sortable="true"
                            data-halign="center"><spring:message code="table.cathedra"/></th>
                        <th scope="col" data-width="20" data-width-unit="%" data-sortable="true"
                            data-halign="center"><spring:message code="table.faculty"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${student_list}" var="student">
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
                            <td>
                                <spring:url value="/faculties/{facultyId}/cathedras/{cathedraId}/groupClasses/{groupClassId}" var="grclUrl">
                                    <spring:param name="facultyId" value="${student.faculty.id}"/>
                                    <spring:param name="cathedraId" value="${student.cathedra.id}"/>
                                    <spring:param name="groupClassId" value="${student.groupClass.id}"/>
                                </spring:url>
                                <a class="link" href="${grclUrl}"><c:out
                                        value="${student.groupClass.title}"/></a>
                            </td>
                            <td>
                                <spring:url value="/faculties/{facultyId}/cathedras/{cathedraId}" var="catUrl">
                                    <spring:param name="facultyId" value="${student.faculty.id}"/>
                                    <spring:param name="cathedraId" value="${student.cathedra.id}"/>
                                </spring:url>
                                <a class="link" href="${catUrl}"><c:out value="${student.cathedra.title}"/></a>
                            </td>
                            <td>
                                <spring:url value="/faculties/{facultyId}" var="facUrl">
                                    <spring:param name="facultyId" value="${student.faculty.id}"/>
                                </spring:url>
                                <a class="link" href="${facUrl}"><c:out value="${student.faculty.title}"/></a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div>
                   <spring:url value="/students/new" var="newUrl"/>
                   <a href="${newUrl}" class="btn btn-primary"><spring:message code="student.add"/></a>
                </div>
            </div>
            <div class="col-md-1"></div>
        </div>
    </jsp:body>
</studapp:layout>
