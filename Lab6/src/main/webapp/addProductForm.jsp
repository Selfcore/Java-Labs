<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Add Product</title>
</head>
<body>

<h1>Add New Product</h1>

<form action="add-product" method="post">

    <label>Title:</label><br>
    <input type="text" name="title" required><br><br>

    <label>Price:</label><br>
    <input type="number" name="price" step="0.01" min="0" required><br><br>

    <button type="submit">Add Product</button>
</form>

<br>
<a href="products">Back to Products</a>

</body>
</html>
