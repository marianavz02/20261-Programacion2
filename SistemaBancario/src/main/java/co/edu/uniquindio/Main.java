package co.edu.uniquindio;
import javax.swing.*;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        Banco banco = new Banco("Banco");
        int opcion;

        do {
            String menuPrincipal = """
                    ---- BANCO ----
                    1. Crear Cliente
                    2. Crear Cuenta Ahorro
                    3. Crear Cuenta Corriente
                    4. Crear Cuenta Nomina
                    5. Iniciar Sesion Cliente
                    0. Salir
                    """;

            opcion = Integer.parseInt(JOptionPane.showInputDialog(menuPrincipal));

            switch (opcion) {

                case 1 -> crearCliente(banco);
                case 2 -> crearCuentaAhorro(banco);
                case 3 -> crearCuentaCorriente(banco);
                case 4 -> crearCuentaNomina(banco);
                case 5 -> loginCliente(banco);
                case 0 -> JOptionPane.showMessageDialog(null, "Saliendo...");
                default -> JOptionPane.showMessageDialog(null, "Opcion invalida");
            }

        } while (opcion != 0);
    }

    // ===============================
    // LOGIN CLIENTE
    // ===============================

    public static void loginCliente(Banco banco) {

        String numeroCuenta = JOptionPane.showInputDialog("Numero de cuenta:");
        String clave = JOptionPane.showInputDialog("Clave:");

        Cuenta cuenta = banco.buscarCuenta(numeroCuenta);

        if (cuenta != null &&
                cuenta.getClave().equals(clave) &&
                cuenta.getEstado() == Estado.ACTIVA) {

            JOptionPane.showMessageDialog(null, "Inicio de sesion exitoso");

            menuCliente(banco, numeroCuenta);

        } else {
            JOptionPane.showMessageDialog(null, "Datos incorrectos o cuenta bloqueada");
        }
    }

    // ===============================
    // MENU CLIENTE
    // ===============================

    public static void menuCliente(Banco banco, String numeroCuenta) {

        int opcion;

        do {
            String menu = """
                    --- MENU CLIENTE ---
                    1. Mostrar informacion cuenta
                    2. Depositar
                    3. Retirar
                    4. Bloquear cuenta
                    0. Cerrar sesion
                    """;

            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));

            switch (opcion) {

                case 1 -> {
                    String info = banco.mostrarInfoCuenta(numeroCuenta);
                    JOptionPane.showMessageDialog(null, info);
                }

                case 2 -> {
                    double monto = Double.parseDouble(
                            JOptionPane.showInputDialog("Monto a depositar:"));
                    boolean exito = banco.deposito(numeroCuenta, monto);

                    JOptionPane.showMessageDialog(null,
                            exito ? "Deposito exitoso" : "Error en deposito");
                }

                case 3 -> {
                    double monto = Double.parseDouble(
                            JOptionPane.showInputDialog("Monto a retirar:"));
                    boolean exito = banco.retiro(numeroCuenta, monto, TipoMovimiento.RETIRAR);

                    JOptionPane.showMessageDialog(null,
                            exito ? "Retiro exitoso" : "No se pudo retirar");
                }

                case 4 -> {
                    banco.bloquearCuenta(numeroCuenta);
                    JOptionPane.showMessageDialog(null, "Cuenta bloqueada");
                    opcion = 0; // fuerza cierre de sesion
                }

                case 0 -> JOptionPane.showMessageDialog(null, "Sesion cerrada");

                default -> JOptionPane.showMessageDialog(null, "Opcion invalida");
            }

        } while (opcion != 0);
    }

    // ===============================
    // METODOS CREAR
    // ===============================

    public static void crearCliente(Banco banco) {
        String nombre = JOptionPane.showInputDialog("Nombre:");
        String id = JOptionPane.showInputDialog("ID:");
        String email = JOptionPane.showInputDialog("Email:");
        String telefono = JOptionPane.showInputDialog("Telefono:");
        String direccion = JOptionPane.showInputDialog("Direccion:");
        LocalDate fecha = LocalDate.parse(
                JOptionPane.showInputDialog("Fecha nacimiento (YYYY-MM-DD):"));

        boolean creado = banco.crearCliente(nombre, id, email, telefono, direccion, fecha);

        JOptionPane.showMessageDialog(null,
                creado ? "Cliente creado" : "Ya existe ese ID");
    }

    public static void crearCuentaAhorro(Banco banco) {
        String id = JOptionPane.showInputDialog("ID cliente:");
        String clave = JOptionPane.showInputDialog("Clave:");
        boolean creado = banco.crearAhorro(id, clave);

        JOptionPane.showMessageDialog(null,
                creado ? "Cuenta ahorro creada" : "Cliente no existe");
    }

    public static void crearCuentaCorriente(Banco banco) {
        String id = JOptionPane.showInputDialog("ID cliente:");
        String clave = JOptionPane.showInputDialog("Clave:");
        boolean creado = banco.crearCorriente(id, clave);

        JOptionPane.showMessageDialog(null,
                creado ? "Cuenta corriente creada" : "Cliente no existe");
    }

    public static void crearCuentaNomina(Banco banco) {
        String id = JOptionPane.showInputDialog("ID cliente:");
        String clave = JOptionPane.showInputDialog("Clave:");
        boolean creado = banco.crearNomina(id, clave);

        JOptionPane.showMessageDialog(null,
                creado ? "Cuenta nomina creada" : "Cliente no existe");
    }
}
