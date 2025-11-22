<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Add Worker</title>
</head>
<body>

<h1>Add Worker</h1>

<form action="add-worker" method="post">
    <label>Name:</label><br>
    <input type="text" name="name" required><br><br>

    <label>Email:</label><br>
    <input type="email" name="email" required><br><br>

    <label>Phone:</label><br>
    <input type="text" name="phone" required><br><br>

    <button type="submit">Save</button>
</form>

<br>
<a href="workers">Back to workers list</a>

</body>
</html>
