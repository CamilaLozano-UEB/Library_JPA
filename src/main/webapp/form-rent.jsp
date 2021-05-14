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
        <label>Correo: </label>
        <label>
            <input type="text" class="textItem" name="email" required>
        </label>
        <br>
        <label> Id de la edición:</label>
        <label>
            <input type="text" class="textItem" name="editionId" required>
        </label>
        <br>
        <label>Fecha de Renta</label>
        <label>
            <input type="date" class="textItem" name="releaseYear" required>
        </label>
        <br>
        <input type="submit" class="formButton" value="Crear Renta!">
    </form>

    <h2>Filtrar por rango de fechas</h2>
    <form action="./find-rentbydays">

        <label>Email del cliente: </label>
        <label>
            <input type="text" class="textItem" name="email" required>
        </label>
        <br>
        <label> Rango de fecha 1:</label>
        <label>
            <input type="date" class="textItem" name="date1" required>
        </label>
        <br>
        <label>Rango de fecha 2</label>
        <label>
            <input type="date" class="textItem" name="date1" required>
        </label>
        <br>
        <input type="submit" class="formButton" value="Find rents!">
    </form>
</div>
<div>
    <table class="table" id="rentsTbl">
        <thead>
        <tr>
            <th>Id de la renta</th>
            <th>Correo </th>
            <th>Libros</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
</body>
</html>
