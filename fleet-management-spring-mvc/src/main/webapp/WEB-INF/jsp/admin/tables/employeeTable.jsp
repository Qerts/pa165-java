<table class="table table-striped">
    <thead>
    <tr>
        <th>id</th>
        <th>email</th>
        <th>name</th>
        <th>surname</th>
        <th>role</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${items}" var="item">
        <tr>
            <td>${item.id}</td>
            <td>${item.email}</td>
            <td>${item.name}</td>
            <td>${item.surname}</td>
            <td>${item.role}</td>
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