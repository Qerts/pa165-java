<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tag:template title="Entities">
<jsp:attribute name="body">

<form method="get" action="${pageContext.request.contextPath}/admin/entityListView/selectTable/">
    <tr>
        <td>
            <form:select path="entities" name="entity">
                <form:options items="${entities}"/>
            </form:select>

        </td>
        <td>
            <button type="submit" class="btn btn-primary">Find</button>
        </td>
    </tr>
</form>

<c:choose>
        <c:when test="${entityType == 'vehicle'}"><%@include file="tables/vehicleTable.jsp" %></c:when>
        <c:when test="${entityType == 'employee'}"><%@include file="tables/employeeTable.jsp" %></c:when>
        <c:when test="${entityType == 'inspectionInterval'}"><%@include file="tables/inspectionIntervalTable.jsp" %></c:when>
        <c:when test="${entityType == 'journey'}"><%@include file="tables/journeyTable.jsp" %></c:when>
    </c:choose>

<%--
    <jsp:include page="tables/${entityType}.jsp" flush="true">
        <jsp:param name="items" value="${items}"/>
    </jsp:include>
--%>

</jsp:attribute>
</tag:template>
