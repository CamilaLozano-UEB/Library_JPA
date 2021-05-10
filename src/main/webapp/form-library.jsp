<%@ page contentType="text/html;charset=UTF-8" %>
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
        <label>Library name: </label>
        <label>
            <input type="text" class="textItem" name="name" required>
        </label>
        <br>
        <input type="submit" class="formButton" value="Create library!">
    </form>

    <h2>Modificar Biblioteca</h2>
    <form action="./modify-library">
        <label>Library id: </label>
        <label>
            <input type="text" class="textItem" name="libraryId" required>
        </label>
        <br>
        <label> Library name: </label>
        <label>
            <input type="text" class="textItem" name="name" required>
        </label>
        <br>
        <input type="submit" class="formButton" value="Modify library!">
    </form>
</div>
<div>
    <h2>Eliminar Biblioteca</h2>
    <form action="./delete-library">
        <label> Library id: </label>
        <label>
            <input type="text" class="textItem" name="libraryId" required>
        </label>
        <br>
        <input type="submit" class="formButton" value="Delete library!">
    </form>
</div>
</body>
</html>
