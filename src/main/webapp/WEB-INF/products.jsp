<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Products</title></head>
<body>
<h2>Products List</h2>
<table border="1">
    <tr><th>ID</th><th>Name</th><th>Price</th><th>Actions</th></tr>
    <c:forEach var="p" items="${products}">
        <tr>
            <td>${p.id}</td>
            <td>${p.name}</td>
            <td>${p.price}</td>
            <td>
                <form method="post">
                    <input type="hidden" name="id" value="${p.id}"/>
                    <input type="hidden" name="action" value="delete"/>
                    <input type="submit" value="Delete"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<h3>Add Product</h3>
<form method="post">
    <input type="hidden" name="action" value="add"/>
    Name: <input type="text" name="name"/>
    Price: <input type="text" name="price"/>
    <input type="submit" value="Add"/>
</form>
</body>
</html>
