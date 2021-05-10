<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        Author name: <input type="text" class="textItem" name="name" required>
        <br>
        Author country: <input type="text" class="textItem" name="country" required>
        <br>
        <input type="submit" class="formButton" value="Create author!">
    </form>
    <br>
    <h2>Modificar Autor</h2>
    <form action="./modify-author">
        Author id: <input type="text" class="textItem" name="authorId" required>
        <br>
        Author name: <input type="text" class="textItem" name="name" required>
        <br>
        Author country: <input type="text" class="textItem" name="country" required>
        <br>
        <input type="submit" class="formButton" value="Modify author!">
    </form>

    <h2>Eliminar Autor</h2>
    <form action="./delete-author">
        Author id: <input type="text" class="textItem" name="authorId" required>
        <br>
        <input type="submit" class="formButton" value="Delete author!">
    </form>
</div>
<div>
    <table class="table" id="authorsTbl">
        <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th># Books</th>
            <th>Country</th>
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

    // Printing authors
    printTable(elementId = 'authorsTbl', servlet = 'list-authors', columns = ['authorId', 'name', 'numBooks', 'country']);

</script>
</body>
</html>
