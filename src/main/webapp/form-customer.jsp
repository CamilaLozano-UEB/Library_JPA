<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Customer Forms</title>
    <link rel="stylesheet" href="CSS/Styles.css">
</head>
<body>
<div id="create-menu">
    <h2>Crear Cliente</h2>
    <form action="./create-customer">
        <label> Customer email </label>
        <label>
            <input type="text" class="textItem" name="email" required>
        </label>
        <br>
        <label>Customer first name: </label>
        <label>
            <input type="text" class="textItem" name="first_name" required>
        </label>
        <br>
        <label>Customer last name: </label>
        <label>
            <input type="text" class="textItem" name="last_name" required>
        </label>
        <br>
        <label>Customer age: </label>
        <label>
            <input type="text" class="textItem" name="age" required>
        </label>
        <br>
        <label>Customer gender: </label>
        <label>
            <input type="text" class="textItem" name="gender" required>
        </label>
        <br>
        <input type="submit" class="formButton" value="Create customer!">
    </form>
    <br>
    <h2>Modificar Cliente</h2>
    <form action="./modify-customer">
        <label>Customer email: </label>
        <label>
            <input type="text" class="textItem" name="email" required>
        </label>
        <br>
        <label>Customer first name: </label>
        <label>
            <input type="text" class="textItem" name="first_name" required>
        </label>
        <br>
        <label>Customer last name: </label>
        <label>
            <input type="text" class="textItem" name="last_name" required>
        </label>
        <br>
        <label>Customer age: </label>
        <label>
            <input type="text" class="textItem" name="age" required>
        </label>
        <br>
        <label>Customer gender: </label>
        <label>
            <input type="text" class="textItem" name="gender" required>
        </label>
        <br>
        <input type="submit" class="formButton" value="Modify customer!">
    </form>

    <h2>Eliminar Cliente</h2>
    <form action="./delete-author">
        <label>Customer email: </label>
        <label>
            <input type="text" class="textItem" name="email" required>
        </label>
        <br>
        <input type="submit" class="formButton" value="Delete customer!">
    </form>
</div>
<div>
    <table class="table" id="customerTbl">
        <thead>
        <tr>
            <th>email</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Age</th>
            <th>Gender</th>
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

    // Printing customers
    printTable(elementId = 'customerTbl', servlet = 'list-customer', columns = ['email', 'first_name', 'last_name', 'age','gender']);

</script>
</body>
</html>

