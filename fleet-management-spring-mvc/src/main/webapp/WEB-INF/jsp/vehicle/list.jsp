<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tag:template title="Vehicles">
<jsp:attribute name="body">

<a href="${pageContext.request.contextPath}/vehicle/new" class="btn btn-primary">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        New vehicle
    </a>

<table class="table table-striped">
    <thead>
    <tr>
        <th>id</th>
        <th>vrp</th>
        <th>type</th>
        <th>year of production</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${vehicles}" var="vehicle">
        <tr>
            <td>${vehicle.id}</td>
            <td>${vehicle.vrp}</td>
            <td>${vehicle.type}</td>
            <td><c:out value="${vehicle.productionYear}"/></td>
            <td>
                <a href="${pageContext.request.contextPath}/vehicle/view/${vehicle.id}" class="btn btn-primary">View</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</jsp:attribute>
</tag:template>
