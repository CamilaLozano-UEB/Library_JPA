<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Author Forms</title>
    <link rel="stylesheet" href="CSS/Styles.css">
</head>
<body>
<div id="create-menu">
    <h2>Crear Autor</h2>
    <form action="./create-author">
        <label>Nombre: </label>
        <label>
            <input type="text" class="textItem" name="name" required>
        </label>
        <br>
        <label>País: </label>
        <label>
            <input type="text" class="textItem" name="country" required>
        </label>
        <br>
        <input type="submit" class="formButton" value="Crear autor!">
        <p style="display: inline"><%=request.getAttribute("createAuthorMessage")%>
        </p>
    </form>
    <h2>Modificar Autor</h2>
    <form action="./modify-author">
        <label>Id: </label>
        <label>
            <input type="text" class="textItem" name="authorId" required>
        </label>
        <br>
        <label>Nombre: </label>
        <label>
            <input type="text" class="textItem" name="name" required>
        </label>
        <br>
        <label>País: </label>
        <label>
            <input type="text" class="textItem" name="country" required>
        </label>
        <br>
        <input type="submit" class="formButton" value="Modificar!">
        <p style="display: inline"><%=request.getAttribute("modifyAuthorMessage")%>
        </p>
    </form>

    <h2>Eliminar Autor</h2>
    <form action="./delete-author">
        <label>Id: </label>
        <label>
            <input type="text" class="textItem" name="authorId" required>
        </label>
        <br>
        <input type="submit" class="formButton" value="Eliminar autor!">
        <p style="display: inline"><%=request.getAttribute("deleteAuthorMessage")%>
        </p>
    </form>
</div>
<div>
    <table class="table" id="authorsTbl">
        <thead>
        <tr>
            <th>Id</th>
            <th>Nombre</th>
            <th># Library</th>
            <th>País</th>
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

    printTable(elementId = 'authorsTbl', servlet = 'list-authors', columns = ['authorId', 'name', 'numBooks', 'country']);

    const ps = document.getElementsByTagName("p");
    for (let i = 0; i < ps.length; i++)
        if (ps[i].textContent.trim() === "null")
            ps[i].textContent = "";
</script>

</body>
</html>
