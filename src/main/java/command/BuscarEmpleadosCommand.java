package command;

import dao.EmpleadoDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Empleado;

import java.util.List;

public class BuscarEmpleadosCommand implements Command {
    private final EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombre");
        String sexo = request.getParameter("sexo");
        String catStr = request.getParameter("categoria");
        String anyosStr = request.getParameter("anyos");

        Integer categoria = (catStr != null && !catStr.trim().isEmpty()) ? Integer.parseInt(catStr) : null;
        Integer anyos = (anyosStr != null && !anyosStr.trim().isEmpty()) ? Integer.parseInt(anyosStr) : null;

        List<Empleado> resultados = empleadoDAO.buscarEmpleados(dni, nombre, sexo, categoria, anyos);
        request.setAttribute("resultados", resultados);
        return "/resultadosBusqueda.jsp";
    }
}
