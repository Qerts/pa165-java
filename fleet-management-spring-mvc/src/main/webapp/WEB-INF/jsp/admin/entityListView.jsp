<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tag:template title="Entities">
<jsp:attribute name="body">

<form method="get" action="${pageContext.request.contextPath}/admin/entityListView/selectTable">
        <tr>
            <td>
                <form:select path="entities" name="entity" items="${entities}" />

            </td>
            <td>
                <button type="submit" class="btn btn-primary">Find</button>
            </td>
        </tr>
    </form>

<jsp:include page="tables/vehicleTable.jsp">
        <jsp:param name="items" value="${items}"/>
    </jsp:include>


</jsp:attribute>
</tag:template>
