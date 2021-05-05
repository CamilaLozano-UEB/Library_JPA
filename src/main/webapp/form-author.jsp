<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>JPA Tutorial</title>
</head>
<body>
<div id="create-menu">
    <h3>Crear Autor</h3>
    <form action="./create-author">
        Author name: <input type="text" id="name" name="name">
        Author country: <input type="text" id="country" name="country">
        <input type="submit" value="Create author!">
    </form>
</div>
<div>
    <h3>Modificar Autor</h3>
    <form action="./modify-author">
        Author name: <input type="text" id="modify_name" name="name">
        Author country: <input type="text" id="modify_country" name="country">
        <input type="submit" value="Modify author!">
    </form>
</div>
<div>
    <h3>Eliminar Autor</h3>
    <form action="./eraser-author">
        Author name: <input type="text" id="eraser_name" name="name">
        Author country: <input type="text" id="eraser_country" name="country">
        <input type="submit" value="Remove author!">
    </form>
</div>
</body>
</html>
