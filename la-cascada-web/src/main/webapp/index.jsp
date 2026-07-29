<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>La Cascada - Gestion de clientes</title>
</head>
<body>

<h1>La Cascada - Gestion de clientes</h1>

<a href="<%= request.getContextPath() %>/clientes">
    Ver listado de clientes
</a>
<br><br>
<a href="<%= request.getContextPath() %>/clientes?accion=nuevo">
    Registrar nuevo cliente
</a>

</body>
</html>
