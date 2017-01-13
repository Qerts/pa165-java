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
        </tr>
    </c:forEach>
    </tbody>
</table>