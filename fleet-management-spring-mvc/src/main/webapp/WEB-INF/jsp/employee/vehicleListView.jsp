<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tag:template title="Vehicles">
<jsp:attribute name="body">

<form method="get" action="${pageContext.request.contextPath}/employee/journeyListView/${employee.id}">
    <button type="submit" class="btn btn-primary">List of all journeys</button>
</form>

<table class="table table-striped">
    <thead>
    <tr>
        <th>id</th>
        <th>vrp</th>
        <th>type</th>
        <th>year of production</th>
        <th>category</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${vehicles}" var="vehicle">
        <tr>
            <td>${vehicle.id}</td>
            <td>${vehicle.vrp}</td>
            <td>${vehicle.type}</td>
            <td><c:out value="${vehicle.productionYear}"/></td>
            <td>${vehicle.vehicleCategory.name}</td>
            <td>
                <a href="${pageContext.request.contextPath}/employee/vehicleAddJourneyView/${vehicle.id}/${employee.id}"
                   modelAttribute="journeyCreate" class="btn btn-primary">Add journey</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</jsp:attribute>
</tag:template>
