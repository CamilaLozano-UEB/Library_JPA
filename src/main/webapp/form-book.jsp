<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Book forms</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="CSS/Styles.css">
</head>
<body>
<!--Button that allows you to return to index.html-->
<a href="index.html" class="btn btn-info btn-lg"style="background-color: #273c75">
    <span class="glyphicon glyphicon-home"></span> Home
</a>
<!--div containing the three forms to create, modify and delete an book-->
<div>
    <h2>Crear Libro</h2>
    <form action="./create-book">
        <label>Id del autor: </label>
        <label>
            <input type="text" class="textItem" name="authorId" required>
        </label>
        <br>
        <label>Título del libro:</label>
        <label>
            <input type="text" class="textItem" name="title" required>
        </label>
        <br>
        <label>ISBN:</label>
        <label>
            <input type="text" class="textItem" name="isbn" required>
        </label>
        <br>
        <label>Género: </label>
        <label>
            <input type="text" class="textItem" name="genre" required>
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
        <input type="submit" class="formButton" value="Crear libro!">
        <!--Obtain the message attribute from the servlet-->
        <p style="display: inline"><%=request.getAttribute("createBookMessage")%>
        </p>
    </form>

    <h2>Modificar Libro</h2>
    <form action="./modify-book">
        <label>Id de libro: </label>
        <label>
            <input type="text" class="textItem" name="bookId" required>
        </label>
        <br>
        <label>Id del autor: </label>
        <label>
            <input type="text" class="textItem" name="authorId" required>
        </label>
        <br>
        <label>Título del libro: </label>
        <label>
            <input type="text" class="textItem" name="title" required>
        </label>
        <br>
        <label>ISBN: </label>
        <label>
            <input type="text" class="textItem" name="isbn" required>
        </label>
        <br>
        <label>Género: </label>
        <label>
            <input type="text" class="textItem" name="genre" required>
        </label>
        <br>
        <input type="submit" class="formButton" value="Modificar libro!">
        <!--Obtain the message attribute from the servlet-->
        <p style="display: inline"><%=request.getAttribute("modifyBookMessage")%>
        </p>
    </form>

    <h2>Eliminar Libro</h2>
    <form action="./delete-book">
        <label>Id del libro: </label>
        <label>
            <input type="text" class="textItem" name="bookId" required>
        </label>
        <br>
        <input type="submit" class="formButton" value="Eliminar libro!">
        <!--Obtain the message attribute from the servlet-->
        <p style="display: inline"><%=request.getAttribute("deleteBookMessage")%>
        </p>
    </form>
</div>
<!-- Create a table that shows the objects that were saved from the form-->
<div>
    <table class="table" id="booksTbl">
        <thead>
        <tr>
            <th>Id del libro</th>
            <th>Id del autor</th>
            <th>Nombre del autor</th>
            <th>Título</th>
            <th>ISBN</th>
            <th>Género</th>
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
    printTable(elementId = 'booksTbl', servlet = 'list-books', columns = ['bookId', 'authorId', 'authorName', 'title', 'isbn', 'genre']);
    // Remove all null messages from the servlet response on p tags
    const ps = document.getElementsByTagName("p");
    for (let i = 0; i < ps.length; i++)
        if (ps[i].textContent.trim() === "null")
            ps[i].textContent = "";
</script>
</body>
</html>
