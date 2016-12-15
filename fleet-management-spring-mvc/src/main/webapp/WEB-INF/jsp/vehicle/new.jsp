<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tag:template title="New vehicle">
<jsp:attribute name="body">

<form:form method="post" action="${pageContext.request.contextPath}/vehicle/create"
           modelAttribute="vehicleCreate" cssClass="form-horizontal">

        <div class="form-group ${vrp_error?'has-error':''}">
            <form:label path="vrp" cssClass="col-sm-2 control-label">Plate no.</form:label>
            <div class="col-sm-10">
                <form:input path="vrp" cssClass="form-control"/>
                <form:errors path="vrp" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${type_error?'has-error':''}">
            <form:label path="type" cssClass="col-sm-2 control-label">Type</form:label>
            <div class="col-sm-10">
                <form:input path="type" cssClass="form-control"/>
                <form:errors path="type" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="vehicleCategoryId" cssClass="col-sm-2 control-label">Vehicle category</form:label>
            <div class="col-sm-10">
                    <form:select path="vehicleCategoryId" cssClass="form-control">
                        <c:forEach items="${vehicleCategories}" var="c">
                            <form:option value="${c.id}">${c.name}</form:option>
                        </c:forEach>
                    </form:select>
                <p class="help-block"><form:errors path="vehicleCategoryId" cssClass="error"/></p>
            </div>
        </div>

        <div class="form-group ${productionYear_error?'has-error':''}">
            <form:label path="productionYear" cssClass="col-sm-2 control-label">Year of production</form:label>
            <div class="col-sm-10">
                <form:input path="productionYear" cssClass="form-control"/>
                <form:errors path="productionYear" cssClass="help-block"/>
            </div>
        </div>

        <div class="form-group ${engineType_error?'has-error':''}">
            <form:label path="engineType" cssClass="col-sm-2 control-label">Type of engine</form:label>
            <div class="col-sm-10">
                <form:input path="engineType" cssClass="form-control"/>
                <form:errors path="engineType" cssClass="help-block"/>
            </div>
        </div>


        <div class="form-group ${vin_error?'has-error':''}">
            <form:label path="vin" cssClass="col-sm-2 control-label">Vin</form:label>
            <div class="col-sm-10">
                <form:input path="vin" cssClass="form-control"/>
                <form:errors path="vin" cssClass="help-block"/>
            </div>
        </div>

        <div class="form-group ${initialKilometrage_error?'has-error':''}">
            <form:label path="initialKilometrage" cssClass="col-sm-2 control-label">Initial kilometrage</form:label>
            <div class="col-sm-10">
                <form:input path="initialKilometrage" cssClass="form-control"/>
                <form:errors path="initialKilometrage" cssClass="help-block"/>
            </div>
        </div>



        <button class="btn btn-primary" type="submit">Create vehicle</button>
    </form:form>


</jsp:attribute>
</tag:template>
