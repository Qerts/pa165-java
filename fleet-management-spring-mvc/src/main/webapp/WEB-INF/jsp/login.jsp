<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tag:template>
<jsp:attribute name="body">

        <form method="POST" action="${pageContext.request.contextPath}/login" class="form-signin">
            <c:if test="${param.error != null}">
                <div class="alert alert-danger">
                    <p>Invalid username and password.</p>
                </div>
            </c:if>
            <c:if test="${param.logout != null}">
                <div class="alert alert-success">
                    <p>You have been logged out successfully.</p>
                </div>
            </c:if>

            <h2 class="form-signin-heading">Please sign in</h2>

            <label for="inputEmail" class="sr-only">Email address</label>
            <input type="email" name="inputEmail" id="inputEmail" class="form-control" placeholder="Email address"
                   required autofocus>
            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" name="inputPassword" id="inputPassword" class="form-control" placeholder="Password"
                   required>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        </form>

        <h3>Testing accounts credentials</h3>
        <table class="table table-striped">
            <tr>
                <th>Role</th>
                <th>Email</th>
                <th>Password</th>
            </tr>
            <tr>
                <th>Admin</th>
                <td>admin@muni.cz</td>
                <td>admin</td>
            </tr>
            <tr>
                <th>Serviceman</th>
                <td>serviceman@muni.cz</td>
                <td>password</td>
            </tr>
            <tr>
                <th>Admin</th>
                <td>employee1@muni.cz</td>
                <td>password</td>
            </tr>
        </table>

</jsp:attribute>
</tag:template>
