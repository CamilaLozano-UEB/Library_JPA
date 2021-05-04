<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>JSP Tutorial</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="CSS/Styles.css" type="text/css">
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
</head>
<body>

<div id="header">
    <ul class="nav">
        <li><a href="">Inicio</a></li>
        <li><a href="">Servicios</a>
            <ul>
                <li><a href="">Submenu1</a></li>
                <li><a href="">Submenu2</a></li>
                <li><a href="">Submenu3</a></li>
                <li><a href="">Submenu4</a>
                    <ul>
                        <li><a href="">Submenu1</a></li>
                        <li><a href="">Submenu2</a></li>
                        <li><a href="">Submenu3</a></li>
                        <li><a href="">Submenu4</a></li>
                    </ul>
                </li>
            </ul>
        </li>
        <li><a href="">Acerca de</a>
            <ul>
                <li><a href="">Submenu1</a></li>
                <li><a href="">Submenu2</a></li>
                <li><a href="">Submenu3</a></li>
                <li><a href="">Submenu4</a></li>
            </ul>
        </li>
        <li><a href="">Contacto</a></li>
    </ul>
</div>

<h1>Library Manager</h1>

<button onclick="location.href='./form-library.jsp';">Create library</button>
<button onclick="location.href='./form-author.jsp';">Create author</button>

<h3>Libraries</h3>

<table id="librariesTbl">
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
    </tr>
    </thead>
    <tbody>
    </tbody>
</table>

<h3>Authors</h3>

<table id="authorsTbl">
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th># Books</th>
        <th>Country</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    </tbody>
</table>

<script>

    function printTable(elementId, servlet, columns, actions = []) {

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

                    if (actions.includes('create-book')) {
                        const cell = newRow.insertCell();
                        const action = document.createElement('button');
                        action.setAttribute('onclick', 'location.href="./form-book.jsp?authorId=' + d['authorId'] + '";');
                        const text = document.createTextNode('Create book');
                        action.appendChild(text);
                        cell.appendChild(action);
                    }

                });

            }
        }
        xhr.open('GET', '${pageContext.request.contextPath}/' + servlet, true);
        xhr.send(null);

    }

    // Printing libraries
    printTable(elementId = 'librariesTbl', servlet = 'list-libraries', columns = ['libraryId', 'name']);

    // Printing authors
    printTable(elementId = 'authorsTbl', servlet = 'list-authors', columns = ['authorId', 'name', 'numBooks', 'country'], actions = ['create-book']);

</script>

</body>
</html>