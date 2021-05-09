<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Book forms</title>
    <link rel="stylesheet" href="CSS/Styles.css">
</head>
<body>
<div>
    <h2>Crear Libro</h2>
    <form action="./create-book">
        <input type="text" class="textItem" name="authorId" required>
        <br>
        Book title: <input type="text" class="textItem" name="title" required>
        <br>
        Book ISBN: <input type="text" class="textItem" name="isbn" required>
        <br>
        Book Genre <input type="text" class="textItem" name="genre" required>
        <br>
        <input type="submit" class="formButton" value="Create book!">
    </form>
</div>

<div>
    <h2>Modificar Libro</h2>
    <form action="./modify-book">
        Book id: <input type="text" class="textItem" name="bookId" required>
        <br>
        Author id: <input type="text" class="textItem" name="authorId" required>
        <br>
        Book title: <input type="text" class="textItem" name="title" required>
        <br>
        Book ISBN: <input type="text" class="textItem" name="isbn" required>
        <br>
        Book Genre <input type="text" class="textItem" name="genre" required>
        <br>
        <input type="submit" class="formButton" value="Modify book!">
    </form>
</div>

<div>
    <h2>Eliminar Libro</h2>
    <form action="./delete-book">
        Book id: <input type="text" class="textItem" name="bookId" required>
        <br>
        <input type="submit" class="formButton" value="Delete book!">
    </form>
</div>

</body>
</html>
