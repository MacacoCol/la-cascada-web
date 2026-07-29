<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Clientes" %>
<%
    ArrayList<Clientes> listaClientes =
            (ArrayList<Clientes>) request.getAttribute("listaClientes");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Clientes</title>
</head>
<body>

<h1>Lista de clientes</h1>

<a href="<%= request.getContextPath() %>/clientes?accion=nuevo">
    Registrar cliente
</a>

<br><br>

<table border="1">
    <thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Documento</th>
            <th>Telefono</th>
            <th>Direccion</th>
            <th>Correo</th>
            <th>Acciones</th>
        </tr>
    </thead>

    <tbody>
    <%
        if (listaClientes != null && !listaClientes.isEmpty()) {
            for (Clientes cliente : listaClientes) {
    %>
        <tr>
            <td><%= cliente.getId_cliente() %></td>
            <td><%= cliente.getNombre() %></td>
            <td><%= cliente.getDocumento() %></td>
            <td><%= cliente.getTelefono() %></td>
            <td><%= cliente.getDireccion() %></td>
            <td><%= cliente.getCorreo() %></td>
            <td>
                <a href="<%= request.getContextPath() %>/clientes?accion=editar&id=<%= cliente.getId_cliente() %>">
                    Editar
                </a>
                &nbsp;|&nbsp;
                <a href="<%= request.getContextPath() %>/clientes?accion=eliminar&id=<%= cliente.getId_cliente() %>"
                   onclick="return confirm('¿Desea eliminar este cliente?')">
                    Eliminar
                </a>
            </td>
        </tr>
    <%
            }
        } else {
    %>
        <tr>
            <td colspan="7">No hay clientes registrados.</td>
        </tr>
    <%
        }
    %>
    </tbody>
</table>

<br>

<a href="<%= request.getContextPath() %>/">
    Volver al inicio
</a>

</body>
</html>
