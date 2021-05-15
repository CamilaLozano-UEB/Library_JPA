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
        <p style="display: inline"><%=request.getAttribute("createRentMessage")%>
        </p>
    </form>

    <h2>Mostrar rentas por rango de fechas</h2>
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
            <input type="date" class="textItem" name="date2" required>
        </label>
        <br>
        <input type="submit" class="formButton" value="Find rents!" id="buttonF">
    </form>
</div>
<div>
    <table class="table" id="rentsTbl">
        <thead>
        <tr>
            <th>Id de la renta</th>
            <th>Dia de renta</th>
            <th>Correo</th>
            <th>Id de edicion</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script>

    function printTable() {
        columns = ['rentId', 'renting_date', 'customer', 'edition'];


        const data = <%=request.getAttribute("rentsJsonString")%>;
        const tbodyRef = document.getElementById('rentsTbl').getElementsByTagName('tbody')[0];

        data.map(d => {
            const newRow = tbodyRef.insertRow();

            columns.map(c => {
                const cell = newRow.insertCell();
                const text = document.createTextNode(d[c]);
                cell.appendChild(text);
            });
        });

    }

    function doTable() {
        const data = <%=request.getAttribute("rentsJsonString")%>;
        if (data != null) {
            printTable();
        }

    }

    doTable();

    const ps = document.getElementsByTagName("p");
    for (let i = 0; i < ps.length; i++)
        if (ps[i].textContent.trim() === "null")
            ps[i].textContent = "";
</script>
</body>
</html>
