<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tag:template title="Vehicle Administration">
<jsp:attribute name="body">


    <table class="table table-striped">
        <thead>
        <tr>
            <th>attribute</th>
            <th>value</th>

        </tr>
        </thead>
        <tbody>
        <tr>
            <td>id</td>
            <td>${vehicle.id}</td>
        </tr><tr>
            <td>plate no.</td>
            <td><c:out value="${vehicle.vrp}"/></td>
        </tr><tr>
            <td>type</td>
            <td><c:out value="${vehicle.type}"/></td>
        </tr><tr>
            <td>year of production</td>
            <td><c:out value="${vehicle.productionYear}"/></td>
        </tr><tr>
            <td>type of engine</td>
            <td><c:out value="${vehicle.engineType}"/></td>
        </tr><tr>
            <td>vin</td>
            <td><c:out value="${vehicle.vin}"/></td>
        </tr><tr>
            <td>initial kilometrage</td>
            <td><c:out value="${vehicle.initialKilometrage}"/></td>
        </tr><tr>
            <td>total kilometrage</td>
            <td><c:out value="${kilometrage}"/></td>
        </tr>
        </tbody>
    </table>

    <h2>Inspection intervals</h2>

    <a href="${pageContext.request.contextPath}/technician/vehicleAddInspectionView/${vehicle.id}" class="btn btn-primary">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        Add inspection interval
    </a>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>name</th>
            <th>interval (days)</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${inspections}" var="inspection">
        <tr>
            <td>${inpection.name}</td>
            <td>${inpection.days}</td>

        </tr>
    </c:forEach>
        </tbody>
    </table>

</jsp:attribute>
</tag:template>