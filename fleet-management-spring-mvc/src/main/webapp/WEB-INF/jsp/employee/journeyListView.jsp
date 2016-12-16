<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tag:template title="Journeys">
<jsp:attribute name="body">

<table class="table table-striped">
    <thead>
    <tr>
        <th>id</th>
        <th>distance</th>
        <th>borrowed at</th>
        <th>returned at</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${journeys}" var="journey">
        <tr>
            <td>${journey.id}</td>
            <td>${journey.distance}</td>
            <td><c:out value="${journey.borrowedAt}"/></td>
            <td><c:out value="${journey.returnedAt}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</jsp:attribute>
</tag:template>
