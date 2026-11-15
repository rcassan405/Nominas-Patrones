<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dto.SalarioDTO" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Resultado del Salario</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container">
        <h2>Información Salarial</h2>
        <%
            SalarioDTO salarioDTO = (SalarioDTO) request.getAttribute("salarioDTO");
        %>
        <p><strong>DNI del Empleado:</strong> <%= salarioDTO.getDni() %></p>
        <p><strong>Salario Calculado:</strong> <%= String.format("%.2f", salarioDTO.getSueldo()) %> €</p>
        <br>
        <a href="javascript:history.back()" class="btn btn-back">Volver</a>
    </div>
</body>
</html>