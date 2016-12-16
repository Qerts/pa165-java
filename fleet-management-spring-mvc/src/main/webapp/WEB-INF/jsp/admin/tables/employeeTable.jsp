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
    <c:forEach items="${items}" var="items">
        <tr>
            <td>${item.id}</td>
            <td>${item.email}</td>
            <td>${item.name}</td>
            <td>${item.surname}</td>
            <td>${item.role}</td>
            <td>
                <a href="${pageContext.request.contextPath}/admin/createItem/${item.id}/${item.type}"
                   modelAttribute="itemCreate" class="btn btn-primary">Add</a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/admin/updateItem/${item.id}/${item.type}"
                   modelAttribute="itemUpdate" class="btn btn-primary">Update</a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/admin/disableItem/${item.id}/${item.type}"
                   modelAttribute="itemDisable" class="btn btn-primary">Disable</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>