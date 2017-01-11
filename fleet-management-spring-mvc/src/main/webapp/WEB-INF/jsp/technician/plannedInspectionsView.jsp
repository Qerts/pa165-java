<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tag:template title="Planned inspections">
<jsp:attribute name="body">

<form class="form-inline">
    Following inspections must be performed within
    <input style="width: 80px" class="form-control" min="0" value="${withinDays}" type="number" id="withinDays" name="withinDays" size="2"/>
    <button class="btn btn-default" type="submit" value="Refresh">
        <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
    </button>
    <label for="withinDays">days</label>.
</form>

<table class="table table-striped">
    <thead>
    <tr>
        <th>Vehicle VRP</th>
        <th>Vehicle type</th>
        <th>Inspection name</th>
        <th>Interval (days)</th>
        <th>Last inspection performed on</th>
        <th>Next inspection should be performed until</th>
        <th></th>
    </tr>
    </thead>
    <tbody>


        <%--@elvariable id="inspectionInterval" type="cz.fi.muni.pa165.dto.InspectionIntervalDTO"--%>
    <c:forEach items="${plannedInspectionIntervals}" var="inspectionInterval">
        <tr>
            <td>${inspectionInterval.vehicle.vrp}</td>
            <td>${inspectionInterval.vehicle.type}</td>
            <td>${inspectionInterval.name}</td>
            <td>${inspectionInterval.days}</td>
            <td>
                <c:if test="${empty inspectionInterval.lastInspectionWasPerformedOn}">
                    <i>never</i>
                </c:if>
                <c:if test="${not empty inspectionInterval.lastInspectionWasPerformedOn}">
                    ${inspectionInterval.lastInspectionWasPerformedOn}
                </c:if>

            </td>
            <td>
                ${inspectionInterval.nextInspectionShouldBePerformedUntil}
                (in ${inspectionInterval.nextInspectionShouldBePerformedInDays} day(s))
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/technician/perform-inspection-now/${inspectionInterval.id}/?withinDays=${withinDays}"
                   class="btn btn-primary">Perform inspection now</a>
            </td>
        </tr>
    </c:forEach>

    </tbody>
</table>

</jsp:attribute>
</tag:template>
