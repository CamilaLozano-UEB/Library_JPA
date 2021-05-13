<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edition Forms</title>
    <link rel="stylesheet" href="CSS/Styles.css">
</head>

<body>
<div id="create-menu">
    <h2>Crear Edición</h2>
    <form action="./create-edition">
        <label>Book id: </label>
        <label>
            <input type="text" class="textItem" name="bookId" required>
        </label>
        <br>
        <label>Description: </label>
        <label>
            <input type="text" class="textItem" name="description" required>
        </label>
        <br>
        <label>Release year: </label>
        <label>
            <input type="date" class="textItem" name="releaseYear" required>
        </label>
        <br>
        <input type="submit" class="formButton" value="Create edition!">
    </form>
    <br>
    <h2>Modificar Edición</h2>
    <form action="./modify-edition">
        <label>Edition id: </label>
        <label>
            <input type="text" class="textItem" name="editionId" required>
        </label>
        <br>
        <label>Book id: </label>
        <label>
            <input type="text" class="textItem" name="bookId" required>
        </label>
        <br>
        <label>Description: </label>
        <label>
            <input type="text" class="textItem" name="description" required>
        </label>
        <br>
        <label>Release year: </label>
        <label>
            <input type="date" class="textItem" name="releaseYear" required>
        </label>
        <br>
        <input type="submit" class="formButton" value="Modify edition!">
    </form>

    <h2>Eliminar Edición</h2>
    <form action="./delete-edition">
        <label> Edition id:</label>
        <label>
            <input type="text" class="textItem" name="editionId" required>
        </label>
        <br>
        <input type="submit" class="formButton" value="Delete edition!">
    </form>
</div>

<div>
    <table class="table" id="editionTbl">
        <thead>
        <tr>
            <th>Edition id</th>
            <th>Book id</th>
            <th>Book Name</th>
            <th>Description</th>
            <th>Release Year</th>
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
            if (xhr.readyState == 4) {
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
    printTable(elementId = 'editionTbl', servlet = 'list-edition', columns = ['editionId', 'bookId', 'bookTitle', 'description', 'releaseYear']);
</script>
</body>
</html>
