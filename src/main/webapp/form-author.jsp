<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Author Forms</title>
    <link rel="stylesheet" href="CSS/Styles.css">
</head>
<body>
<div id="create-menu">
    <h2>Crear Autor</h2>
    <form action="./create-author">
        Author name: <input type="text" class="textItem" name="name" required>
        <br>
        Author country: <input type="text" class="textItem" name="country" required>
        <br>
        <input type="submit" class="formButton" value="Create author!">
    </form>
</div>
<br>
<div>
    <h2>Modificar Autor</h2>
    <form action="./modify-author">
        Author id: <input type="text" class="textItem" name="authorId" required>
        <br>
        Author name: <input type="text" class="textItem" name="name" required>
        <br>
        Author country: <input type="text" class="textItem" name="country" required>
        <br>
        <input type="submit" class="formButton" value="Modify author!">
    </form>
</div>
<div>
    <h2>Eliminar Autor</h2>
    <form action="./delete-author">
        Author id: <input type="text" class="textItem" name="authorId" required>
        <br>
        <input type="submit" class="formButton" value="Delete author!">
    </form>
</div>
</body>
</html>
