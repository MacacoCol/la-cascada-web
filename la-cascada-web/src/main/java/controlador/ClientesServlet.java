package controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Clientes;
import model.ClientesDAO;

@WebServlet("/clientes")
public class ClientesServlet extends HttpServlet {

    private final ClientesDAO dao = new ClientesDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");

        if (accion == null) {
            accion = "listar";
        }

        try {
            switch (accion) {
                case "nuevo":
                    request.getRequestDispatcher("/formulario.jsp").forward(request, response);
                    break;

                case "editar":
                    int idEditar = Integer.parseInt(request.getParameter("id"));
                    Clientes clienteEditar = dao.buscarPorId(idEditar);
                    request.setAttribute("cliente", clienteEditar);
                    request.getRequestDispatcher("/formulario.jsp").forward(request, response);
                    break;

                case "eliminar":
                    int idEliminar = Integer.parseInt(request.getParameter("id"));
                    dao.eliminar(idEliminar);
                    response.sendRedirect(request.getContextPath() + "/clientes");
                    break;

                case "listar":
                default:
                    listarClientes(request, response);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error de base de datos: " + e.getMessage());
            request.getRequestDispatcher("/resultado.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");

        if ("guardar".equals(accion)) {
            guardarCliente(request, response);
        } else if ("actualizar".equals(accion)) {
            actualizarCliente(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/clientes");
        }
    }

    private void listarClientes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        ArrayList<Clientes> listaClientes = dao.listar();
        request.setAttribute("listaClientes", listaClientes);
        request.getRequestDispatcher("/listar.jsp").forward(request, response);
    }

    private void guardarCliente(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String nombre = request.getParameter("nombre");
        String documento = request.getParameter("documento");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        String correo = request.getParameter("correo");

        Clientes cliente = new Clientes(nombre, documento, telefono, direccion, correo);

        try {
            boolean guardado = dao.guardar(cliente);
            request.setAttribute("mensaje",
                    guardado ? "Cliente guardado correctamente." : "No se pudo guardar el cliente.");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error al guardar: " + e.getMessage());
        }

        request.getRequestDispatcher("/resultado.jsp").forward(request, response);
    }

    private void actualizarCliente(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        int id = Integer.parseInt(request.getParameter("id_cliente"));
        String nombre = request.getParameter("nombre");
        String documento = request.getParameter("documento");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        String correo = request.getParameter("correo");

        Clientes cliente = new Clientes();
        cliente.setId_cliente(id);
        cliente.setNombre(nombre);
        cliente.setDocumento(documento);
        cliente.setTelefono(telefono);
        cliente.setDireccion(direccion);
        cliente.setCorreo(correo);

        try {
            boolean actualizado = dao.actualizar(cliente);
            request.setAttribute("mensaje",
                    actualizado ? "Cliente actualizado correctamente." : "No se pudo actualizar el cliente.");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error al actualizar: " + e.getMessage());
        }

        request.getRequestDispatcher("/resultado.jsp").forward(request, response);
    }
}
