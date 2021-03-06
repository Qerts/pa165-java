<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tag:template title="New inspection interval">
<jsp:attribute name="body">

<form:form method="post" action="${pageContext.request.contextPath}/technician/create"
           modelAttribute="inspectionCreate" cssClass="form-horizontal">

    <div class="form-group ${name_error?'has-error':''}">
        <form:label path="name" cssClass="col-sm-2 control-label">Name:</form:label>
        <div class="col-sm-10">
            <form:input path="name" cssClass="form-control"/>
            <form:errors path="name" cssClass="help-block"/>
        </div>
    </div>

        <div class="form-group ${days_error?'has-error':''}">
            <form:label path="days" cssClass="col-sm-2 control-label">Interval (days):</form:label>
            <div class="col-sm-10">
                <form:input path="days" cssClass="form-control"/>
                <form:errors path="days" cssClass="help-block"/>
            </div>
        </div>

            <form:input path="vehicle.id" type="hidden"/>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <button class="btn btn-primary" type="submit">Create inspection</button>

    </form:form>


</jsp:attribute>
</tag:template>
