package factory;

import command.BuscarEmpleadosCommand;
import command.Command;
import command.ConsultarSalarioCommand;
import command.MostrarEmpleadosCommand;
import jakarta.servlet.http.HttpServletRequest;

public class CommandFactory {

    public static Command getCommand(HttpServletRequest request) {
        String accion = request.getParameter("accion");

        if (accion == null || accion.trim().isEmpty()) {
            return null;
        }

        switch (accion) {
            case "mostrarEmpleados":
                return new MostrarEmpleadosCommand();
            case "consultarSalario":
                return new ConsultarSalarioCommand();
            case "buscarEmpleados":
                return new BuscarEmpleadosCommand();
            default:
                return null;
        }
    }
}
