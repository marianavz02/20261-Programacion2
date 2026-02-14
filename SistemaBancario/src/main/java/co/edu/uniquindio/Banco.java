package co.edu.uniquindio;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


public class Banco {
    public String nombre;
    public ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    public ArrayList<Cuenta> cuentas= new ArrayList<Cuenta>();
    public ArrayList<Movimiento> movimientos= new ArrayList<Movimiento>();

    public Banco(String nombre) {
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

    //Mostar cuenta
    public String mostrarInfoCuenta(String numeroCuenta) {
        Cuenta cuenta = buscarCuenta(numeroCuenta);
        String infoCuenta = "";
        if (cuenta != null) {
            infoCuenta = cuenta.toString();
        }return  infoCuenta;
    }


    //CREAR
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
            nuevaAhorro.crearNumeroCuenta(cuentas);
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
            nuevaNomina.crearNumeroCuenta(cuentas);
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
            nuevaCorriente.crearNumeroCuenta(cuentas);
            cuentas.add(nuevaCorriente);
            resultado = true;
        }
        return resultado;
    }


    //MODIFICACIONES

    public void ajusteNomina() {
        List<Cuenta> nuevasCuentas = new ArrayList<>();
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getFechaUltimoDeposito() == null) {
                continue;
            }
            long meses = ChronoUnit.MONTHS.between(cuenta.getFechaUltimoDeposito(), LocalDate.now());
            if (meses > 3 && cuenta instanceof Nomina) {
                Cliente cliente = cuenta.getTitular();
                String clave = cuenta.getClave();
                Cuenta cuentaNueva = new Corriente(cliente, LocalDate.now(), Estado.ACTIVA, clave);
                nuevasCuentas.add(cuentaNueva);

                deposito(cuentaNueva.getNumeroCuenta(), cuenta.getSaldoActual());
                bloquearCuenta(cuenta.getNumeroCuenta());
            }
        }
        cuentas.addAll(nuevasCuentas);
    }



    //BUSCAR
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


    //TRANSACCIONES


    public boolean retiro(String numeroCuenta, double monto, TipoMovimiento tipoMovimiento){
        boolean resultado = false;
        Movimiento movimientoRealizado = null;
        Cuenta cuenta = buscarCuenta(numeroCuenta);

        if (cuenta != null  &&  cuenta.getEstado() == Estado.ACTIVA) {
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
        Movimiento nuevo = null;
        if (cuenta != null) {
            nuevo = cuenta.deposito(cuenta, monto);
            movimientos.add(nuevo);
            resultado = true;
        } return  resultado;
    }


    public boolean bloquearCuenta(String numeroCuenta){
        boolean resultado = false;
        Cuenta cuenta = buscarCuenta(numeroCuenta);
        if (cuenta != null && cuenta.getEstado() == Estado.ACTIVA) {
            cuenta.bloquear(cuenta);
            resultado = true;

        } return  resultado;
    }

    public boolean activarCuenta(String numeroCuenta){
        boolean resultado = false;
        Cuenta cuenta = buscarCuenta(numeroCuenta);
        if (cuenta != null && cuenta.getEstado() == Estado.BLOQUEADA) {
            cuenta.activar(cuenta);
            resultado = true;
        }return  resultado;
    }

}
