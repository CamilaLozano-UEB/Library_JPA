<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Book forms</title>
    <link rel="stylesheet" href="CSS/Styles.css">
</head>
<body>
<div>
    <h2>Crear Libro</h2>
    <form action="./create-book">
        <label>Author id: </label>
        <label>
            <input type="text" class="textItem" name="authorId" required>
        </label>
        <br>
        <label> Book title:</label>
        <label>
            <input type="text" class="textItem" name="title" required>
        </label>
        <br>
        <label>Book ISBN:</label>
        <label>
            <input type="text" class="textItem" name="isbn" required>
        </label>
        <br>
        <label>Book Genre </label>
        <label>
            <input type="text" class="textItem" name="genre" required>
        </label>
        <br>
        <input type="submit" class="formButton" value="Create book!">
    </form>

    <h2>Modificar Libro</h2>
    <form action="./modify-book">
        <label>Book id: </label>
        <label>
            <input type="text" class="textItem" name="bookId" required>
        </label>
        <br>
        <label>Author id: </label>
        <label>
            <input type="text" class="textItem" name="authorId" required>
        </label>
        <br>
        <label>Book title: </label>
        <label>
            <input type="text" class="textItem" name="title" required>
        </label>
        <br>
        <label>Book ISBN: </label>
        <label>
            <input type="text" class="textItem" name="isbn" required>
        </label>
        <br>
        <label>Book Genre </label>
        <label>
            <input type="text" class="textItem" name="genre" required>
        </label>
        <br>
        <input type="submit" class="formButton" value="Modify book!">
    </form>


    <h2>Eliminar Libro</h2>
    <form action="./delete-book">
        <label>Book id: </label>
        <label>
            <input type="text" class="textItem" name="bookId" required>
        </label>
        <br>
        <input type="submit" class="formButton" value="Delete book!">
    </form>
</div>
<div>
    <table class="table" id="booksTbl">
        <thead>
        <tr>
            <th>Book id</th>
            <th>Author Id</th>
            <th>Title</th>
            <th>ISBN</th>
            <th>Genre</th>
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

    // Printing books
    printTable(elementId = 'booksTbl', servlet = 'list-books', columns = ['bookId', 'authorId', 'title', 'isbn', 'genre']);

</script>
</body>
</html>
