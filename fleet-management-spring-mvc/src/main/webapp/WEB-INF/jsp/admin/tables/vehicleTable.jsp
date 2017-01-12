<a href="${pageContext.request.contextPath}/vehicle/new" class="btn btn-primary">
    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
    New vehicle
</a>

<table class="table table-striped">
    <thead>
    <tr>
        <th>id</th>
        <th>vrp</th>
        <th>type</th>
        <th>year of production</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${items}" var="item">
        <tr>
            <td>${item.id}</td>
            <td>${item.vrp}</td>
            <td>${item.type}</td>
            <td><c:out value="${item.productionYear}"/></td>
            <td>
                <a href="${pageContext.request.contextPath}/vehicle/update/${item.id}"
                   modelAttribute="itemUpdate" class="btn btn-primary">Update</a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/vehicle/deactivate-vehicle/${item.id}"
                   modelAttribute="itemDisable" class="btn btn-primary">Deactivate</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
