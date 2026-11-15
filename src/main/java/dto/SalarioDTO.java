package dto;

import java.io.Serializable;


public class SalarioDTO implements Serializable {
    private String dni;
    private double sueldo;

    public SalarioDTO() {}

    public SalarioDTO(String dni, double sueldo) {
        this.dni = dni;
        this.sueldo = sueldo;
    }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public double getSueldo() { return sueldo; }
    public void setSueldo(double sueldo) { this.sueldo = sueldo; }
}