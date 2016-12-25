<table class="table table-striped">
    <thead>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>days</th>
        <th>vehicle id</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${items}" var="item">
        <tr>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.days}</td>
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