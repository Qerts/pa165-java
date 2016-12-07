<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tag:template title="Vehicle Administration">
<jsp:attribute name="body">


    <form method="post" action="${pageContext.request.contextPath}/vehicle/disable/${vehicle.id}">
        <button type="submit" class="btn btn-primary">Disable</button>
    </form>


    <table class="table">
        <thead>
        <tr>
            <th>id</th>
            <th>plate no.</th>
            <th>type</th>
            <th>year of production</th>
            <th>type of engine</th>
            <th>vin</th>
            <th>initial kilometrage</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${vehicle.id}</td>
            <td><c:out value="${vehicle.vrp}"/></td>
            <td><c:out value="${vehicle.type}"/></td>
            <td><c:out value="${vehicle.productionYear}"/></td>
            <td><c:out value="${vehicle.engineType}"/></td>
            <td><c:out value="${vehicle.vin}"/></td>
            <td><c:out value="${vehicle.initialKilometrage}"/></td>
        </tr>
        </tbody>
    </table>


</jsp:attribute>
</tag:template>
