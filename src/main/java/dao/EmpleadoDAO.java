package dao;

import conexion.Conexion;
import dto.SalarioDTO;
import model.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

    public List<Empleado> obtenerTodos() throws SQLException {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT dni, nombre, sexo, categoria, anyos FROM empleados ORDER BY dni ASC";

        try (Connection conn = Conexion.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Empleado(
                    rs.getString("dni"),
                    rs.getString("nombre"),
                    rs.getString("sexo").charAt(0),
                    rs.getInt("categoria"),
                    rs.getInt("anyos")
                ));
            }
        }
        return lista;
    }

    public SalarioDTO obtenerSalarioPorDni(String dni) throws SQLException {
        String sql = "SELECT dni, sueldo FROM nominas WHERE dni = ?";
        try (Connection conn = Conexion.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new SalarioDTO(rs.getString("dni"), rs.getDouble("sueldo"));
                }
            }
        }
        return null;
    }

    public List<Empleado> buscarEmpleados(String dni, String nombre, String sexo, Integer categoria, Integer anyos) throws SQLException {
        List<Empleado> lista = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT dni, nombre, sexo, categoria, anyos FROM empleados WHERE 1=1");

        if (dni != null && !dni.trim().isEmpty()) sql.append(" AND dni LIKE ?");
        if (nombre != null && !nombre.trim().isEmpty()) sql.append(" AND nombre LIKE ?");
        if (sexo != null && !sexo.trim().isEmpty()) sql.append(" AND sexo = ?");
        if (categoria != null) sql.append(" AND categoria = ?");
        if (anyos != null) sql.append(" AND anyos = ?");

        sql.append(" ORDER BY dni ASC");

        try (Connection conn = Conexion.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            int paramIndex = 1;
            if (dni != null && !dni.trim().isEmpty()) ps.setString(paramIndex++, "%" + dni.trim() + "%");
            if (nombre != null && !nombre.trim().isEmpty()) ps.setString(paramIndex++, "%" + nombre.trim() + "%");
            if (sexo != null && !sexo.trim().isEmpty()) ps.setString(paramIndex++, sexo.trim());
            if (categoria != null) ps.setInt(paramIndex++, categoria);
            if (anyos != null) ps.setInt(paramIndex++, anyos);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Empleado(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("sexo").charAt(0),
                        rs.getInt("categoria"),
                        rs.getInt("anyos")
                    ));
                }
            }
        }
        return lista;
    }
}