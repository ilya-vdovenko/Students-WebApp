<%@ page session="false" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="studapp" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<studapp:layout pageName="students" back="false">
    <jsp:attribute name="customScript">
        <script src="${pageContext.request.contextPath}/resources/static/StudentForm.js"></script>
    </jsp:attribute>
    <jsp:body>
        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <h2>
                    <c:choose>
                        <c:when test="${studentDto['new']}">
                            Add
                        </c:when>
                        <c:otherwise>
                            Edit
                        </c:otherwise>
                    </c:choose>
                    student
                </h2>
                <sf:form method="post" modelAttribute="studentDto">
                <studapp:inputField label="Name" type="text" maxlength="40" name="fio"/>
                <div class="row">
                    <div class="col-auto">
                        <studapp:inputField label="BirthDay" type="date" name="birthday"/>
                    </div>
                    <div class="col-auto">
                        <studapp:inputField label="Sex" type="selectSex" name="sex"/>
                    </div>
                    <div class="col-auto">
                        <studapp:inputField label="Telephone" type="tel" name="telephone"/>
                    </div>
                </div>
                <studapp:inputField label="Actual address" type="text" maxlength="100" name="factAddress"/>
                <studapp:inputField label="Address" type="text" maxlength="100" name="address"/>
                <studapp:inputField label="Faculty" type="selectUnit" name="faculty"
                                    selId="selFac"
                                    itemValue="${studentDto.faculty.id}"
                                    itemLabel="${studentDto.faculty.title}"/>
                <studapp:inputField label="Cathedra" type="selectUnit" name="cathedra"
                                    selId="selCat"
                                    itemValue="${studentDto.cathedra.id}"
                                    itemLabel="${studentDto.cathedra.title}"/>
                <studapp:inputField label="Group" type="selectUnit" name="groupClass"
                                    selId="selGrp"
                                    itemValue="${studentDto.groupClass.id}"
                                    itemLabel="${studentDto.groupClass.title}"/>
                <div class="mt-5">
                    <button class="btn btn-primary" type="submit">
                    <c:choose>
                        <c:when test="${studentDto['new']}">
                            Add</button>
                            <spring:url value="/students" var="backUrl"/>
                        </c:when>
                        <c:otherwise>
                            Save</button>
                            <spring:url value="/students/{studentId}" var="backUrl">
                                <spring:param name="studentId" value="${studentDto.id}"/>
                            </spring:url>
                        </c:otherwise>
                    </c:choose><a href="${backUrl}" class="btn btn-danger">Cancel</a>
                    </sf:form>
                </div>
            </div>
            <div class="col-md-5"></div>
        </div>
    </jsp:body>
</studapp:layout>
