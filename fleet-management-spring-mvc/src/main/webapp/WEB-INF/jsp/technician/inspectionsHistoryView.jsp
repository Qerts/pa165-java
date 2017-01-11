<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tag:template title="Inspections history">
<jsp:attribute name="body">

<table class="table table-striped">
    <thead>
    <tr>
        <th>Vehicle VRP</th>
        <th>Vehicle type</th>
        <th>Inspection interval name</th>
        <th>Interval (days)</th>
        <th>Performed on</th>
    </tr>
    </thead>
    <tbody>

    <%--@elvariable id="inspection" type="cz.fi.muni.pa165.dto.InspectionDTO"--%>
    <c:forEach items="${inspections}" var="inspection">
        <tr>
            <td>${inspection.inspectionInterval.vehicle.vrp}</td>
            <td>${inspection.inspectionInterval.vehicle.type}</td>
            <td>${inspection.inspectionInterval.name}</td>
            <td>${inspection.inspectionInterval.days}</td>
            <td>${inspection.performedOn}</td>
        </tr>
    </c:forEach>

    </tbody>
</table>

</jsp:attribute>
</tag:template>
