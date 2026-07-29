<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String mensaje = (String) request.getAttribute("mensaje");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Resultado</title>
</head>
<body>

<h1>Resultado</h1>

<p><%= mensaje %></p>

<a href="<%= request.getContextPath() %>/clientes">
    Ver listado de clientes
</a>
<br><br>
<a href="<%= request.getContextPath() %>/clientes?accion=nuevo">
    Registrar otro cliente
</a>

</body>
</html>
