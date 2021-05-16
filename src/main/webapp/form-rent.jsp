<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Rent forms</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="CSS/Styles.css">
</head>
<body>
<!--div containing the three forms to create and filter table-->

<div  style="float:left; width:45%; margin-right: 10%">
    <!--Button that allows you to return to index.html-->
    <a href="index.html" class="btn btn-info btn-lg" style="background-color: #273c75">
        <span class="glyphicon glyphicon-home"></span> Home
    </a>
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
        <!--Obtain the message attribute from the servlet-->
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
        <input type="submit" class="formButton" value="Mostrar rentas!" id="buttonF">
    </form>
</div>
<div style="float:left; width: 45%;">
    <!-- Create a table that shows the objects that filter in the second form-->
    <h2>Rentas buscadas por fechas</h2>
    <table class="table" id="rentsFilter">
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
<br>
<div>
    <!-- Create a table that shows the objects of all rents-->
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
    /**
     * Modify the table from the ListRentsServlets servlet attribute
     */
    function printTable() {
        columns = ['rentId', 'renting_date', 'customer', 'edition'];


        const data = <%=request.getAttribute("rentsJsonString")%>;
        const tbodyRef = document.getElementById('rentsFilter').getElementsByTagName('tbody')[0];

        data.map(d => {
            const newRow = tbodyRef.insertRow();

            columns.map(c => {
                const cell = newRow.insertCell();
                const text = document.createTextNode(d[c]);
                cell.appendChild(text);
            });
        });

    }

    /**
     * Modify the table from the ListAllRentsServlets servlet attribute
     */
    function printTable2(servlet) {
        columns = ['rentId', 'renting_date', 'customer', 'edition'];

        const xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            // if the operation is complete
            if (xhr.readyState === 4) {
                // Parse the response text
                const data = JSON.parse(xhr.responseText);
                const tbodyRef = document.getElementById("rentsTbl").getElementsByTagName('tbody')[0];
                // Create rows and columns with the information of the servlet

                data.map(d => {
                    const newRow = tbodyRef.insertRow();

                    columns.map(c => {
                        const cell = newRow.insertCell();
                        const text = document.createTextNode(d[c]);
                        cell.appendChild(text);
                    });
                });
            }
        }
        xhr.open('GET', '${pageContext.request.contextPath}/' + servlet, true);
        xhr.send(null);
    }

    /**
     * Method that controls when the tables is triggered to filter and display
     */
    function doTable() {
        const data = <%=request.getAttribute("rentsJsonString")%>;
        if (data != null) {
            printTable();
        }
        printTable2("find-rentList")
    }
    // Invoke the method
    doTable();

    // Remove all null messages from the servlet response on p tags
    const ps = document.getElementsByTagName("p");
    for (let i = 0; i < ps.length; i++)
        if (ps[i].textContent.trim() === "null")
            ps[i].textContent = "";
</script>
</body>
</html>
