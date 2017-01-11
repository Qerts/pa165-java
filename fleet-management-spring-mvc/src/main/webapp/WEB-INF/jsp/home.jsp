<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tag:template>
<jsp:attribute name="body">

<div class="starter-template">
    <h1>Fleet management</h1>

    <h3 style="text-align: center">&raquo; Award-winning awesome ultimate enterprise solution for all your's company entire automobile management &laquo;</h3>

    <img style="width: 100%" src="<c:url value='/images/car-park.jpg'/>" alt="Car park" />

    <%--<p class="lead">Login accounts:<br>--%>
        <%--Admin email: <b>admin@muni.cz</b> password: <b>admin</b><br>--%>
        <%--Serviceman email: <b>serviceman@muni.cz</b> password: <b>password</b><br>--%>
        <%--Employee email: <b>employee1@muni.cz</b> password: <b>password</b><br></p>--%>
</div>

</jsp:attribute>
</tag:template>
