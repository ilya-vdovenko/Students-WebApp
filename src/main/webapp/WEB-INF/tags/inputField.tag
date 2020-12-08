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
<%@ attribute name="helpText" required="false" rtexprvalue="true"
              description="help text for input" %>
<%@ attribute name="maxlength" required="false" rtexprvalue="true"
              description="max length of text field" %>


<spring:bind path="${name}">
    <div class="form-group">
        <label>${label}</label>
        <c:if test="${type eq 'text'}">
            <sf:input class="form-control mb-2" maxlength="${maxlength}" path="${name}"/>
        </c:if>
        <c:if test="${type eq 'date'}">
            <sf:input class="form-control mb-2" type="date" path="${name}"/>
        </c:if>
        <c:if test="${type eq 'tel'}">
            <sf:input class="form-control mb-2" type="tel" maxlength="11" path="${name}"/>
        </c:if>
        <c:if test="${type eq 'selectSex'}">
            <sf:select class="custom-select mb-2" path="${name}">
                <sf:option value="муж">муж</sf:option>
                <sf:option value="жен">жен</sf:option>
            </sf:select>
        </c:if>
        <c:if test="${type eq 'selectUnit'}">
            <sf:select class="custom-select mb-2" path="${name}" id="${selId}">
                <sf:option value="${itemValue}">${itemLabel}</sf:option>
            </sf:select>
        </c:if>
        <small class="form-text text-muted">${helpText}</small>
    </div>
</spring:bind>
