package command;

import dao.EmpleadoDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Empleado;

import java.util.List;

public class MostrarEmpleadosCommand implements Command {
    private final EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Empleado> empleados = empleadoDAO.obtenerTodos();
        request.setAttribute("empleados", empleados);
        return "/mostrarEmpleados.jsp";
    }
}