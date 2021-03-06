<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Customer Forms</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="CSS/Styles.css">
</head>
<body>
<!--Button that allows you to return to index.html-->
<a href="index.html" class="btn btn-info btn-lg" style="background-color: #273c75">
    <span class="glyphicon glyphicon-home"></span> Home
</a>
<!--div containing the three forms to create, modify and delete an customer-->
<div id="create-menu">
    <h2>Crear Cliente</h2>
    <form action="./create-customer">
        <label> Email del cliente: </label>
        <label>
            <input type="text" class="textItem" name="email" required>
        </label>
        <br>
        <label>Nombre del cliente: </label>
        <label>
            <input type="text" class="textItem" name="first_name" required>
        </label>
        <br>
        <label>Apellido del cliente: </label>
        <label>
            <input type="text" class="textItem" name="last_name" required>
        </label>
        <br>
        <label>Edad del cliente: </label>
        <label>
            <input type="text" class="textItem" name="age" required>
        </label>
        <br>
        <label>Genero del cliente:</label>
        <label>
            <input type="text" class="textItem" name="gender" required>
        </label>
        <br>
        <input type="submit" class="formButton" value="Crear Cliente!">
        <!--Obtain the message attribute from the servlet-->
        <p style="display: inline"><%=request.getAttribute("createCustomerMessage")%>
        </p>
    </form>
    <br>
    <h2>Modificar Cliente</h2>
    <form action="./modify-customer">
        <label>Email del cliente: </label>
        <label>
            <input type="text" class="textItem" name="email" required>
        </label>
        <br>
        <label>Nombre del cliente: </label>
        <label>
            <input type="text" class="textItem" name="first_name" required>
        </label>
        <br>
        <label>Apellido del cliente: </label>
        <label>
            <input type="text" class="textItem" name="last_name" required>
        </label>
        <br>
        <label>Edad del cliente: </label>
        <label>
            <input type="text" class="textItem" name="age" required>
        </label>
        <br>
        <label>Genero del cliente: </label>
        <label>
            <input type="text" class="textItem" name="gender" required>
        </label>
        <br>
        <input type="submit" class="formButton" value="Modificar cliente!">
        <!--Obtain the message attribute from the servlet-->
        <p style="display: inline"><%=request.getAttribute("modifyCustomerMessage")%>
        </p>
    </form>

    <h2>Eliminar Cliente</h2>
    <form action="./delete-customer">
        <label>Email del cliente: </label>
        <label>
            <input type="text" class="textItem" name="email" required>
        </label>
        <br>
        <input type="submit" class="formButton" value="Borrar Cliente!">
        <!--Obtain the message attribute from the servlet-->
        <p style="display: inline"><%=request.getAttribute("deleteCustomerMessage")%>
        </p>
    </form>
</div>
<div>
    <!-- Create a table that shows the objects that were saved from the form-->

    <table class="table" id="customerTbl">
        <thead>
        <tr>
            <th>Email</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Edad</th>
            <th>Genero</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script>
    /**
     * Modify the table adding the information that receives from the servlet on JSON format
     * @param elementId the id of the table
     * @param servlet the servlet
     * @param columns the columns of the table
     */
    function printTable(elementId, servlet, columns) {

        const xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            // if the operation is complete
            if (xhr.readyState == 4) {
                // Parse the response text
                const data = JSON.parse(xhr.responseText);
                const tbodyRef = document.getElementById(elementId).getElementsByTagName('tbody')[0];
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

    // Invoke the method
    printTable(elementId = 'customerTbl', servlet = 'list-customer', columns = ['email', 'first_name', 'last_name', 'age','gender']);

    // Remove all null messages from the servlet response on p tags
    const ps = document.getElementsByTagName("p");
    for (let i = 0; i < ps.length; i++)
        if (ps[i].textContent.trim() === "null")
            ps[i].textContent = "";
</script>
</body>
</html>

