<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="name" required="true" rtexprvalue="true"
              description="Name of corresponding property in bean object" %>
<%@ attribute name="label" required="true" rtexprvalue="true"
              description="Label appears in red color if input is considered as invalid after submission" %>
<%@ attribute name="type" required="true" rtexprvalue="true"
              description="type of input" %>
<%@ attribute name="items" required="false" rtexprvalue="true" type="java.util.List"
              description="Names in the list" %>
<%@ attribute name="selId" required="false" rtexprvalue="true"
              description="id for type:select" %>
<%@ attribute name="itemLabel" required="false" rtexprvalue="true"
              description="label for type:select" %>
<%@ attribute name="itemValue" required="false" rtexprvalue="true"
              description="value for type:select" %>
<%@ attribute name="maxlength" required="false" rtexprvalue="true"
              description="max length of text field" %>


<spring:bind path="${name}">
    <c:set var="inputFeedback"
           value="form-control ${status.errors.hasFieldErrors(name) ? 'is-invalid' : ''} mb-2"/>
    <c:set var="selectFeedback"
           value="custom-select ${status.errors.hasFieldErrors(name) ? 'is-invalid' : ''} mb-2"/>
    <div class="form-group">
        <label>${label}</label>
        <c:if test="${type eq 'text'}">
            <sf:input class="${inputFeedback}" maxlength="${maxlength}" path="${name}"/>
        </c:if>
        <c:if test="${type eq 'date'}">
            <sf:input class="${inputFeedback}" type="date" path="${name}"/>
        </c:if>
        <c:if test="${type eq 'tel'}">
            <sf:input class="${inputFeedback}" type="tel" maxlength="11" path="${name}"/>
        </c:if>
        <c:if test="${type eq 'selectSex'}">
            <c:set var="man"><spring:message code="field.man"/></c:set>
            <c:set var="fem"><spring:message code="field.fem"/></c:set>
            <sf:select class="${selectFeedback}" path="${name}">
                <sf:option disabled="true" value="" selected="true"> </sf:option>
                <sf:option value="${man}">${man}</sf:option>
                <sf:option value="${fem}">${fem}</sf:option>
            </sf:select>
        </c:if>
        <c:if test="${type eq 'selectUnit'}">
            <sf:select class="${selectFeedback}" path="${name}" id="${selId}">
                <sf:option value="${itemValue}">${itemLabel}</sf:option>
            </sf:select>
        </c:if>
        <small class="invalid-feedback"><sf:errors path="${name}"/></small>
    </div>
</spring:bind>
