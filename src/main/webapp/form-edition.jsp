<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edition Forms</title>
    <link rel="stylesheet" href="CSS/Styles.css">
</head>

<body>
<div id="create-menu">
    <h2>Crear Edición</h2>
    <form action="./create-edition">
        Book id: <input type="text" class="textItem" name="bookId" required>
        <br>
        Description: <input type="text" class="textItem" name="description" required>
        <br>
        Release year: <input type="date" class="textItem" name="releaseYear" required>
        <br>
        <input type="submit" class="formButton" value="Create edition!">
    </form>
    <br>
    <h2>Modificar Autor</h2>
    <form action="./modify-edition">
        Edition id: <input type="text" class="textItem" name="editionId" required>
        <br>
        Book id: <input type="text" class="textItem" name="bookId" required>
        <br>
        Description: <input type="text" class="textItem" name="description" required>
        <br>
        Release year: <input type="date" class="textItem" name="releaseYear" required>
        <br>
        <input type="submit" class="formButton" value="Modify author!">
    </form>

    <h2>Eliminar Autor</h2>
    <form action="./delete-edition">
        Edition id: <input type="text" class="textItem" name="editionId" required>
        <br>
        <input type="submit" class="formButton" value="Delete author!">
    </form>
</div>
</body>
</html>
