import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Banco {
    public String nombre;
    public ArrayList<Cliente> clientes;
    public ArrayList<Cuenta> cuentas;
    public ArrayList<Movimiento> movimientos;

    public Banco(String nombre, ArrayList<Cliente> clientes, ArrayList<Cuenta> cuentas, ArrayList<Movimiento> movimientos) {
        this.nombre = nombre;
        this.clientes = clientes;
        this.cuentas = cuentas;
        this.movimientos = movimientos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(ArrayList<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public ArrayList<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(ArrayList<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }



    //metodos



    public boolean crearCliente (String nombre, String id, String email, String telefono, String direccion, LocalDate fechaNacimiento){
        boolean resultado = false;
        Cliente clienteVerificar = buscarId(id);
        if (clienteVerificar == null) {
            Cliente nuevo = new Cliente(nombre, id, email, telefono, direccion, fechaNacimiento);
            clientes.add(nuevo);
            resultado = true;
        }return  resultado;
    }

    public boolean crearAhorro (String id, String clave){
        boolean resultado = false;
        LocalDate fechaApertura = LocalDate.now();
        Cliente clienteVerificar = buscarId(id);
        Estado estado = Estado.ACTIVA;
        if (clienteVerificar != null) {
            Ahorro nuevaAhorro= new Ahorro(clienteVerificar,fechaApertura,estado,clave);
            cuentas.add(nuevaAhorro);
            resultado = true;
        }
        return resultado;
    }

    public boolean crearNomina (String id,String clave){
        boolean resultado = false;
        LocalDate fechaApertura = LocalDate.now();
        Cliente clienteVerificar = buscarId(id);
        Estado estado = Estado.ACTIVA;
        if (clienteVerificar != null) {
            Nomina nuevaNomina= new Nomina(clienteVerificar,fechaApertura,estado,clave);
            cuentas.add(nuevaNomina);
            resultado = true;
        }
        return resultado;
    }

    public boolean crearCorriente (String id, String clave){
        boolean resultado = false;
        LocalDate fechaApertura = LocalDate.now();
        Cliente clienteVerificar = buscarId(id);
        Estado estado = Estado.ACTIVA;
        if (clienteVerificar != null) {
            Corriente nuevaCorriente= new Corriente(clienteVerificar,fechaApertura,estado,clave);
            cuentas.add(nuevaCorriente);
            resultado = true;
        }
        return resultado;
    }



    public void ajusteNomina(){
        for (Cuenta  cuenta : cuentas) {
            long meses = ChronoUnit.MONTHS.between(cuenta.fechaUltimoDeposito, LocalDate.now());
            if (meses >= 3){
                Cliente cliente = cuenta.getTitular();
                String id = cliente.getId();
                String clave = cuenta.getClave();
                crearCorriente(id,clave);

            }

        }
    }

    public Cliente buscarId(String id) {
        Cliente clienteEncontrado = null;
        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(id)) {
                clienteEncontrado = cliente;
            break;
            }
        }
        return clienteEncontrado;
    }


    public Cuenta buscarCuenta(String numeroCuenta) {
        Cuenta cuentaEncontrado = null;
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                cuentaEncontrado = cuenta;
                break;
            }
        }
        return cuentaEncontrado;
    }

    public boolean retiro(String numeroCuenta, double monto, TipoMovimiento tipoMovimiento){
        boolean resultado = false;
        Movimiento movimientoRealizado = null;
        Cuenta cuenta = buscarCuenta(numeroCuenta);

        if (cuenta != null) {
            movimientoRealizado= cuenta.retiro(cuenta, monto, tipoMovimiento);
            if(movimientoRealizado != null){
                resultado = true;
                movimientos.add(movimientoRealizado);
            }

        }return  resultado;
    }

    public boolean deposito(String numeroCuenta, double monto){
        boolean resultado = false;
        Cuenta cuenta = buscarCuenta(numeroCuenta);
        if (cuenta != null) {
            TipoMovimiento tipoMovimiento = TipoMovimiento.DEPOSITAR;
            LocalDate fecha = LocalDate.now();
            Movimiento nuevo = new Movimiento(numeroCuenta,tipoMovimiento,fecha, monto);
            movimientos.add(nuevo);
            cuenta.setFechaUltimoDeposito(fecha);
            resultado = true;
        } return  resultado;
    }


}
