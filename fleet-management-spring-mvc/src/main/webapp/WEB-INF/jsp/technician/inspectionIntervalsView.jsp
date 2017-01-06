<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tag:template title="Inspection intervals">
<jsp:attribute name="body">


<table class="table table-striped">
    <thead>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>interval (days)</th>
        <th>vehicle</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${inspections}" var="inspection">
        <tr>
            <td>${inspection.id}</td>
            <td>${inspection.name}</td>
            <td>${inspection.days}</td>
            <td>${inspection.vehicle.type}</td>

        </tr>
    </c:forEach>
    </tbody>
</table>
</jsp:attribute>
</tag:template>
