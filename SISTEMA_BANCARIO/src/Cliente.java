import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Cliente {
    private String nombre;
    private String id;
    private String email;
    private String telefono;
    private String direccion;
    private LocalDate fechaNacimiento;

    public Cliente(String nombre, String id, String email, String telefono, String direccion, LocalDate fecha_nacimiento) {
        this.nombre = nombre;
        this.id = id;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fechaNacimiento = fecha_nacimiento;
    }
//GETTERS
    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getId() {
        return id;
    }


//SETTERS
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                ", fecha nacimiento=" + fechaNacimiento +
                '}';
    }


    //METODOS



}
