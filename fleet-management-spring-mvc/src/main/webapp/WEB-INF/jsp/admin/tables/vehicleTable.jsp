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
                <a href="${pageContext.request.contextPath}/admin/createItem/${item.id}/${entityType}"
                   modelAttribute="itemCreate" class="btn btn-primary">Add</a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/admin/updateItem/${item.id}/${entityType}"
                   modelAttribute="itemUpdate" class="btn btn-primary">Update</a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/admin/disableItem/${item.id}/${entityType}"
                   modelAttribute="itemDisable" class="btn btn-primary">Disable</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>