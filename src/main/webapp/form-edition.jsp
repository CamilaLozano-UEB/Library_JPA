<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edition Forms</title>
    <link rel="stylesheet" href="CSS/Styles.css">
</head>
<body>
<!--div containing the three forms to create, modify and delete an edition-->
<div id="create-menu">
    <h2>Crear Edición</h2>
    <form action="./create-edition">
        <label>Id del libro: </label>
        <label>
            <input type="text" class="textItem" name="bookId" required>
        </label>
        <br>
        <label>Descripción: </label>
        <label>
            <input type="text" class="textItem" name="description" required>
        </label>
        <br>
        <label>Año de lanzamiento: </label>
        <label>
            <input type="date" class="textItem" name="releaseYear" required>
        </label>
        <br>
        <input type="submit" class="formButton" value="Crear edición!">
        <!--Obtain the message attribute from the servlet-->
        <p style="display: inline"><%=request.getAttribute("createEditionMessage")%>
        </p>
    </form>
    <br>
    <h2>Modificar Edición</h2>
    <form action="./modify-edition">
        <label>Id de la edición: </label>
        <label>
            <input type="text" class="textItem" name="editionId" required>
        </label>
        <br>
        <label>Id del libro: </label>
        <label>
            <input type="text" class="textItem" name="bookId" required>
        </label>
        <br>
        <label>Descripción: </label>
        <label>
            <input type="text" class="textItem" name="description" required>
        </label>
        <br>
        <label>Año de lanzamiento: </label>
        <label>
            <input type="date" class="textItem" name="releaseYear" required>
        </label>
        <br>
        <input type="submit" class="formButton" value="Modificar edición!">
        <!--Obtain the message attribute from the servlet-->
        <p style="display: inline"><%=request.getAttribute("modifyEditionMessage")%>
        </p>
    </form>

    <h2>Eliminar Edición</h2>
    <form action="./delete-edition">
        <label>Id de la edición:</label>
        <label>
            <input type="text" class="textItem" name="editionId" required>
        </label>
        <br>
        <input type="submit" class="formButton" value="Eliminar edición!">
        <!--Obtain the message attribute from the servlet-->
        <p style="display: inline"><%=request.getAttribute("DeleteEditionMessage")%>
        </p>
    </form>
</div>

<div>
    <!-- Create a table that shows the objects that were saved from the form-->
    <table class="table" id="editionTbl">
        <thead>
        <tr>
            <th>Id de la edición</th>
            <th>Id del libro</th>
            <th>Nombre del libro</th>
            <th>Descripción</th>
            <th>Año de lanzamiento</th>
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
    printTable(elementId = 'editionTbl', servlet = 'list-edition', columns = ['editionId', 'bookId', 'bookTitle', 'description', 'releaseYear']);
    // Remove all null messages from the servlet response on p tags
    const ps = document.getElementsByTagName("p");
    for (let i = 0; i < ps.length; i++)
        if (ps[i].textContent.trim() === "null")
            ps[i].textContent = "";
</script>
</body>
</html>
