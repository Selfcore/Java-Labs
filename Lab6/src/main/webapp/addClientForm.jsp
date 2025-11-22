<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Add Client</title>
</head>
<body>

<h1>Add Client</h1>

<form action="add-client" method="post">
    <label>Name:</label><br>
    <input type="text" name="name" required><br><br>

    <label>Email:</label><br>
    <input type="email" name="email" required><br><br>

    <label>Phone:</label><br>
    <input type="text" name="phone" required><br><br>

    <button type="submit">Save</button>
</form>

<br>
<a href="clients">Back to client list</a>

</body>
</html>
