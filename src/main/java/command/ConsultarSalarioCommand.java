package command;

import dao.EmpleadoDAO;
import dto.SalarioDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ConsultarSalarioCommand implements Command {
    private final EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String dni = request.getParameter("dni");

        if (dni == null || dni.trim().isEmpty()) {
            request.setAttribute("mensajeError", "Debe proporcionar un DNI válido.");
            return "/error.jsp";
        }

        SalarioDTO salarioDTO = empleadoDAO.obtenerSalarioPorDni(dni.trim());

        if (salarioDTO != null) {
            request.setAttribute("salarioDTO", salarioDTO);
            return "/MostrarSalario.jsp";
        } else {
            request.setAttribute("mensajeError", "No se encontró ningún empleado registrado con el DNI: " + dni);
            return "/error.jsp";
        }
    }
}
