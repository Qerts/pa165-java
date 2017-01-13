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

        </tr>
    </c:forEach>
    </tbody>
</table>