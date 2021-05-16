# Library_JPA

<html>
<dl>
	<dt><h2><em> 1. Integrantes </em></h2></dt>
	<br>
	<dd><b>1.1.</b> Nicolás Peña Mogollón</dd>
	<dd><b>1.2.</b> María Camila Lozano Gutierrez</dd>
	<dd><b>1.3.</b> Juana Valentina Torres Parrado</dd>
	<br>
	<dt><h2><em>2. Stack tecnológico</em></h2></dt>
	<dd>JBoss As -Wildfly 23.0.1.Final</dd>
	<dd> PostgreSQL 10</dd>
	<dd>PostgreSQL JDBC Driver 42.2.10</dd>
	<dd>JDK 1.8</dd>
	<br>
	<dt><h2><em> 3. Link del proyecto </em></h2></dt>
	<br>
	<dd><b>3.1.</b> Link de Repositorio</dd>
	<dd>https://github.com/CamilaLozano-UEB/Library_JPA</dd>
	<dd><b>3.2.</b> Link del video en Youtube</dd>
	<dd> https://youtu.be/ZefEQP_F-bo </dd>
	<br>
	<dt><h2><em> 4. Lenguaje de Codificación </em></h2></dt>
	<br>
	<dd> UTF-8 </dd>
	<br>
	<dt><h2><em> 5. Entorno de Desarrollo </em></h2></dt>
	<br>
	<dd> IDE IntelliJ IDEA Ultimate 2021.1</dd>
	<br>
	<dt><h2><em> 7. Intrucciones de Uso </em></h2></dt>
	<dd><b>7.1.</b> Primero debe correr la base de datos en el entorno de desarrollo de su preferencia, usando un servidor de aplicaciones como Wildfly en este caso y PostgreSQL para la base de datos. </dd>
	<dd><b>7.2.</b>Cuando la base de datos este corriendo debera ir a el link <b>http://localhost:8080/Library_JPA-1.0-SNAPSHOT/</b> donde podra tener acceso a la interfaz gráfica de la base de datos donde tendra un menu con diferentes opciones </dd>
	<dd><b>7.3.</b>En primera instancia vera seis botones <b>Librerías</b>, <b>Libros</b>, <b>Usuarios</b>, <b>Autores</b>, <b>Ediciones</b> y <b>Rentas</b>. En cada uno de estos botones tendra acceso a una interfaz con tres formularios en los que podra crear, modificar y eliminar un objeto de las categorias anteriormente mencionadas.</dd>
	<dd><b>7.4.</b>Para guardar un registro primero debe llenar los datos del formulario de la creación y la interfaz le mostrara en una tabla bajo los formularios el registro guardado junto con un id asignado automaticamente que servira para modificar o eliminar un registro </dd>
	<dd><b>7.5.</b>Hay ciertas categorias que a la hora de eliminar tienen una asociación, si desea eliminar un autor debe ser consciente que los libros y sus ediciones también se borraran. Lo mismo pasa con la librería, si borra una librería, las ediciones asociadas a esta también se borraran. Si borra un cliente, sus rentas también se borraran  en ese orden de ideas. </dd>
	</dl>
</html>
