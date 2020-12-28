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
                            <spring:message code="student.add"/>
                        </c:when>
                        <c:otherwise>
                            <spring:message code="student.edit"/>
                        </c:otherwise>
                    </c:choose>
                </h2>
                <c:set var="name"><spring:message code="table.name"/></c:set>
                <c:set var="birthday"><spring:message code="table.birthday"/></c:set>
                <c:set var="sex"><spring:message code="table.sex"/></c:set>
                <c:set var="telephone"><spring:message code="table.telephone"/></c:set>
                <c:set var="actAddr"><spring:message code="table.actAddr"/></c:set>
                <c:set var="address"><spring:message code="table.address"/></c:set>
                <c:set var="faculty"><spring:message code="table.faculty"/></c:set>
                <c:set var="cathedra"><spring:message code="table.cathedra"/></c:set>
                <c:set var="group"><spring:message code="table.group"/></c:set>
                <sf:form method="post" modelAttribute="studentDto">
                <studapp:inputField label="${name}" type="text" maxlength="40" name="fullName"/>
                <div class="row">
                    <div class="col-4">
                        <studapp:inputField label="${birthday}" type="date" name="birthday"/>
                    </div>
                    <div class="col-3">
                        <studapp:inputField label="${sex}" type="selectSex" name="sex"/>
                    </div>
                    <div class="col-5">
                        <studapp:inputField label="${telephone}" type="tel" name="telephone"/>
                    </div>
                </div>
                <studapp:inputField label="${actAddr}" type="text" maxlength="100" name="actualAddress"/>
                <studapp:inputField label="${address}" type="text" maxlength="100" name="address"/>
                <studapp:inputField label="${faculty}" type="selectUnit" name="faculty"
                                    selId="selFac"
                                    itemValue="${studentDto.faculty.id}"
                                    itemLabel="${studentDto.faculty.title}"/>
                <studapp:inputField label="${cathedra}" type="selectUnit" name="cathedra"
                                    selId="selCat"
                                    itemValue="${studentDto.cathedra.id}"
                                    itemLabel="${studentDto.cathedra.title}"/>
                <studapp:inputField label="${group}" type="selectUnit" name="groupClass"
                                    selId="selGrp"
                                    itemValue="${studentDto.groupClass.id}"
                                    itemLabel="${studentDto.groupClass.title}"/>
                <div class="mt-5">
                    <button class="btn btn-primary" type="submit">
                    <c:choose>
                        <c:when test="${studentDto['new']}">
                            <spring:message code="btn.add"/></button>
                            <spring:url value="/students" var="backUrl"/>
                        </c:when>
                        <c:otherwise>
                            <spring:message code="btn.save"/></button>
                            <spring:url value="/students/{studentId}" var="backUrl">
                                <spring:param name="studentId" value="${studentDto.id}"/>
                            </spring:url>
                        </c:otherwise>
                    </c:choose><a href="${backUrl}" class="btn btn-danger"><spring:message code="btn.cancel"/></a>
                    </sf:form>
                </div>
            </div>
            <div class="col-md-5"></div>
        </div>
    </jsp:body>
</studapp:layout>
