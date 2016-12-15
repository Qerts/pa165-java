<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tag:template>
<jsp:attribute name="body">

<form action="login.html" method="post">
    <label> UserName: </label>
    <input type="text" name="userName"/> <br/>
    <label> Password: </label>
    <input type="password" name="password"/><br/>
    <input type="submit" value="login"/>
</form>

</jsp:attribute>
</tag:template>
