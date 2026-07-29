<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="model.Clientes" %>
<%
    Clientes cliente = (Clientes) request.getAttribute("cliente");
    boolean esEdicion = (cliente != null);
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title><%= esEdicion ? "Editar cliente" : "Registrar cliente" %></title>
</head>
<body>

<h1><%= esEdicion ? "Editar cliente" : "Registrar cliente" %></h1>

<form action="<%= request.getContextPath() %>/clientes" method="post">

    <% if (esEdicion) { %>
        <input type="hidden" name="accion" value="actualizar">
        <input type="hidden" name="id_cliente" value="<%= cliente.getId_cliente() %>">
    <% } else { %>
        <input type="hidden" name="accion" value="guardar">
    <% } %>

    <label for="nombre">Nombre:</label>
    <input type="text" id="nombre" name="nombre" required
           value="<%= esEdicion ? cliente.getNombre() : "" %>">

    <br><br>

    <label for="documento">Documento:</label>
    <input type="text" id="documento" name="documento" required
           value="<%= esEdicion ? cliente.getDocumento() : "" %>">

    <br><br>

    <label for="telefono">Telefono:</label>
    <input type="text" id="telefono" name="telefono" required
           value="<%= esEdicion ? cliente.getTelefono() : "" %>">

    <br><br>

    <label for="direccion">Direccion:</label>
    <input type="text" id="direccion" name="direccion" required
           value="<%= esEdicion ? cliente.getDireccion() : "" %>">

    <br><br>

    <label for="correo">Correo:</label>
    <input type="email" id="correo" name="correo" required
           value="<%= esEdicion ? cliente.getCorreo() : "" %>">

    <br><br>

    <button type="submit">
        <%= esEdicion ? "Actualizar" : "Guardar" %>
    </button>

    <a href="<%= request.getContextPath() %>/clientes">
        Cancelar
    </a>

</form>

</body>
</html>
