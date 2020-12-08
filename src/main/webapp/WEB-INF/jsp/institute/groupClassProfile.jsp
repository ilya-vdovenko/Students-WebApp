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
            <div class="col-md-5">
                <h2>grp. "<b><c:out value="${groupClass.title}"/></b>"</h2>
                <table class="table table-borderless table-sm unit"
                       aria-describedby="groupInformation">
                    <tr>
                        <th>Education form</th>
                        <td><c:out value="${groupClass.eduForm}"/><td>
                    </tr>
                    <tr>
                        <th>Cathedra</th>
                        <td>
                            <spring:url value="/faculties/{facultyId}/cathedras/{cathedraId}" var="catUrl">
                                <spring:param name="facultyId" value="${groupClass.cathedra.faculty.id}"/>
                                <spring:param name="cathedraId" value="${groupClass.cathedra.id}"/>
                            </spring:url>
                            <a class="link" href="${catUrl}"><c:out value="${groupClass.cathedra.title}"/></a>
                        </td>
                    </tr>
                    <tr>
                        <th>Faculty</th>
                        <td>
                            <spring:url value="/faculties/{facultyId}" var="facUrl">
                                <spring:param name="facultyId" value="${groupClass.cathedra.faculty.id}"/>
                            </spring:url>
                            <a class="link" href="${facUrl}"><c:out value="${groupClass.cathedra.faculty.title}"/></a>
                        </td>
                    </tr>
                </table>
                <p>Students of group:</p>
                <table class="table table-borderless table-sm student second"
                       aria-describedby="students">
                    <thead class="bg-success text-white">
                    <tr>
                        <th>Name</th>
                        <th>Sex</th>
                        <th>BirthDay</th>
                        <th>Telephone</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${group_students_list}" var="student">
                        <tr>
                            <td>
                                <spring:url value="/students/{studentId}" var="studUrl">
                                    <spring:param name="studentId" value="${student.id}"/>
                                </spring:url>
                                <a class="link" href="${studUrl}"><c:out value="${student.fio}"/></a>
                            </td>
                            <td><c:out value="${student.sex}"/></td>
                            <td><c:out value="${student.birthday}"/></td>
                            <td><c:out value="${student.telephone}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col-md-4"></div>
        </div>
    </jsp:body>
</studapp:layout>
