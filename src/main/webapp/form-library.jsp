<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Library Forms</title>
        <link rel="stylesheet" href="CSS/Styles.css">
    </head>
    <body>
    <div id="create-menu">
        <h2>Crear Biblioteca</h2>
        <form action="./create-library">
            Library name: <input type="text" id="name" name="name"required>
            <input type="submit" value="Create library!">
        </form>
    </div>
    <br>
    <div>
        <h2>Modificar Autor</h2>
        <form action="./modify-library">
            Author id: <input type="text" class="textItem" name="libraryId" required>
            <br>
            Library name: <input type="text" class="textItem" name="name" required>
            <input type="submit" class="formButton" value="Modify author!">
        </form>
    </div>
    <div>
        <h2>Eliminar Autor</h2>
        <form action="./delete-library">
            Author id: <input type="text" class="textItem" name="libraryId" required>
            <br>
            <input type="submit" class="formButton" value="Delete author!">
        </form>
    </div>
    </body>
</html>
