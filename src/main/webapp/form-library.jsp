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
        <label>Nombre: </label>
        <label>
            <input type="text" class="textItem" name="name" required>
        </label>
        <input type="submit" class="formButton" value="Crear biblioteca!">
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
        <p style="display: inline"><%=request.getAttribute("modifyLibraryMessage")%>

    </form>

    <h2>Eliminar Biblioteca</h2>
    <form action="./delete-library">
        <label> Id: </label>
        <label>
            <input type="text" class="textItem" name="libraryId" required>
        </label>
        <input type="submit" class="formButton" value="Borrar biblioteca!">
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
        <p style="display: inline"><%=request.getAttribute("disassociateLibraryMessage")%>
    </form>
</div>

<div>
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

    function printTable(elementId, servlet, columns) {

        const xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                const data = JSON.parse(xhr.responseText);
                const tbodyRef = document.getElementById(elementId).getElementsByTagName('tbody')[0];

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

    printTable(elementId = 'libraryTbl', servlet = 'list-libraries', columns = ['libraryId', 'name']);

    const ps = document.getElementsByTagName("p");
    for (let i = 0; i < ps.length; i++)
        if (ps[i].textContent.trim() === "null")
            ps[i].textContent = "";
</script>
</body>
</html>
