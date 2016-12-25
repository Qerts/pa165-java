<table class="table table-striped">
    <thead>
    <tr>
        <th>id</th>
        <th>distance</th>
        <th>borrowed at</th>
        <th>returned at</th>
        <th>employee id</th>
        <th>vehicle id</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${items}" var="item">
        <tr>
            <td>${item.id}</td>
            <td>${item.distance}</td>
            <td>${item.borrowedAt}</td>
            <td>${item.returnedAt}</td>
            <td>${item.employee.id}</td>
            <td>${item.vehicle.id}</td>
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