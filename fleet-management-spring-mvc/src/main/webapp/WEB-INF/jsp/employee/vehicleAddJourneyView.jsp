<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tag:template title="New journey">
<jsp:attribute name="body">

<form:form method="post" action="${pageContext.request.contextPath}/employee/create"
           modelAttribute="journeyCreate" cssClass="form-horizontal">

                      <div class="form-group ${borrowedAt_error?'has-error':''}">
                          <form:label path="borrowedAt" cssClass="col-sm-2 control-label">Borrowed at:</form:label>
                          <div class="col-sm-10">
                              <form:errors path="borrowedAt" cssClass="help-block"/>
                              <fmt:formatDate value="${now}"
                                              type="date"
                                              pattern="dd-MM-yyyy"
                                              var="theFormattedDate"/>
                              <form:input type="text" path="borrowedAt" value="${theFormattedDate}"/>
                          </div>
                      </div>


                      <div class="form-group ${returnedAt_error?'has-error':''}">
                          <form:label path="returnedAt" cssClass="col-sm-2 control-label">Returned at:</form:label>
                          <div class="col-sm-10">
                                      <fmt:formatDate value="${now}"
                                                      type="date"
                                                      pattern="dd-MM-yyyy"
                                                      var="theFormattedDate"/>
                              <form:input type="text" path="borrowedAt" value="${theFormattedDate}"/>
                              <form:errors path="returnedAt" cssClass="help-block"/>
                          </div>
                      </div>

                      <div class="form-group ${distance_error?'has-error':''}">
                          <form:label path="distance" cssClass="col-sm-2 control-label">Distance:</form:label>
                          <div class="col-sm-10">
                              <form:input path="distance" cssClass="form-control"/>
                              <form:errors path="distance" cssClass="help-block"/>
                          </div>
                      </div>

        <button class="btn btn-primary" type="submit">Create journey</button>
    </form:form>


</jsp:attribute>
</tag:template>
