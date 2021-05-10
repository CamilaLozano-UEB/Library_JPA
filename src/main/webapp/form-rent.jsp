<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Rent forms</title>
    <link rel="stylesheet" href="CSS/Styles.css">
</head>
<body>
<div>
    <h2>Crear Renta</h2>
    <form action="./create-rent">
        <label>email: </label>
        <label>
            <input type="text" class="textItem" name="email" required>
        </label>
        <br>
        <label> Edition id:</label>
        <label>
            <input type="text" class="textItem" name="editionId" required>
        </label>
        <br>
        <label>Renting Date</label>
        <label>
            <input type="text" class="textItem" name="renting_date" required>
        </label>
        <br>
        <input type="submit" class="formButton" value="Create Rent!">
    </form>

    <h2>Modificar Renta</h2>
    <form action="./modify-rent">
        <label>Rent id: </label>
        <label>
            <input type="text" class="textItem" name="rent_id" required>
        </label>
        <br>
        <label>email: </label>
        <label>
            <input type="text" class="textItem" name="email" required>
        </label>
        <br>
        <label> Edition id:</label>
        <label>
            <input type="text" class="textItem" name="editionId" required>
        </label>
        <br>
        <label>Renting Date</label>
        <label>
            <input type="text" class="textItem" name="renting_date" required>
        </label>
        <br>
        <input type="submit" class="formButton" value="Modify rent!">
    </form>


    <h2>Eliminar Renta</h2>
    <form action="./delete-rent">
        <label>Rent id: </label>
        <label>
            <input type="text" class="textItem" name="rent_id" required>
        </label>
        <br>
        <input type="submit" class="formButton" value="Delete rent!">
    </form>
</div>
</body>
</html>
