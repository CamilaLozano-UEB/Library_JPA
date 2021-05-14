<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Library Forms</title>
    <link rel="stylesheet" href="CSS/Styles.css">
</head>
<body>
<!--div containing the three forms to create, modify and delete an library-->
<div id="create-menu">
    <h2>Crear Biblioteca</h2>
    <form action="./create-library">
        <label>Nombre: </label>
        <label>
            <input type="text" class="textItem" name="name" required>
        </label>
        <input type="submit" class="formButton" value="Crear biblioteca!">
        <!--Obtain the message attribute from the servlet-->
        <p style="display: inline"><%=request.getAttribute("createLibraryMessage")%>

    </form>

    <h2>Modificar Biblioteca</h2>
    <form action="./modify-library">
        <label>Id: </label>
        <label>
            <input type="text" class="textItem" name="libraryId" required>
        </label>
        <label> Nombre: </label>
        <label>
            <input type="text" class="textItem" name="name" required>
        </label>
        <input type="submit" class="formButton" value="Modificar biblioteca!">
        <!--Obtain the message attribute from the servlet-->
        <p style="display: inline"><%=request.getAttribute("modifyLibraryMessage")%>

    </form>

    <h2>Eliminar Biblioteca</h2>
    <form action="./delete-library">
        <label> Id: </label>
        <label>
            <input type="text" class="textItem" name="libraryId" required>
        </label>
        <input type="submit" class="formButton" value="Borrar biblioteca!">
        <!--Obtain the message attribute from the servlet-->
        <p style="display: inline"><%=request.getAttribute("deleteLibraryMessage")%>
    </form>

    <h2>Asociar edición a librería</h2>
    <form action="./associate-library">
        <label>Id de la librería: </label>
        <label>
            <input type="text" class="textItem" name="libraryId" required>
        </label>
        <label>Id de la edición: </label>
        <label>
            <input type="text" class="textItem" name="editionId" required>
        </label>
        <input type="submit" class="formButton" value="Asociar!">
        <!--Obtain the message attribute from the servlet-->
        <p style="display: inline"><%=request.getAttribute("associateLibraryMessage")%>
    </form>

    <h2>Desasociar edición a librería</h2>
    <form action="./disassociate-library">
        <label>Id de la librería: </label>
        <label>
            <input type="text" class="textItem" name="libraryId" required>
        </label>
        <label>Id de la edición: </label>
        <label>
            <input type="text" class="textItem" name="editionId" required>
        </label>
        <input type="submit" class="formButton" value="Desasociar!">
        <!--Obtain the message attribute from the servlet-->
        <p style="display: inline"><%=request.getAttribute("disassociateLibraryMessage")%>
    </form>
</div>

<div>
    <!-- Create a table that shows the objects that were saved from the form-->
    <table class="table" id="libraryTbl">
        <thead>
        <tr>
            <th>Id</th>
            <th>Nombre de la biblioteca</th>
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
            if (xhr.readyState === 4) {
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

    printTable(elementId = 'libraryTbl', servlet = 'list-libraries', columns = ['libraryId', 'name']);
    // Remove all null messages from the servlet response on p tags
    const ps = document.getElementsByTagName("p");
    for (let i = 0; i < ps.length; i++)
        if (ps[i].textContent.trim() === "null")
            ps[i].textContent = "";
</script>
</body>
</html>
