<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tag:template title="Vehicles currently borrowed by me">
<jsp:attribute name="body">
<c:choose>
<c:when test="${empty journeys}">
    <div style="text-align: center; font-style: italic">You have currently no borrowed vehicles.</div>
</c:when>
<c:otherwise>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Borrowed on</th>
            <th>Vehicle VRP / type</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <%--@elvariable id="journey" type="cz.fi.muni.pa165.dto.JourneyDTO"--%>
        <c:forEach items="${journeys}" var="journey">
            <tr>
                <td>${journey.borrowedAt}</td>
                <td>${journey.vehicle.vrp} / ${journey.vehicle.type}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/employee/finish-journey/${journey.id}">
                        <input required placeholder="distance" style="width: 80px" type="number" step="0.01"
                               min="0" name="drivenDistance"/>km
                        <button class="btn btn-primary">Return vehicle now</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:otherwise>
</c:choose>

</jsp:attribute>
</tag:template>
