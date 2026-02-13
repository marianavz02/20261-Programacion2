package co.edu.uniquindio;
import java.time.LocalDate;
import java.util.ArrayList;


public abstract class Cuenta {
    private String numeroCuenta;
    public Cliente titular;
    public LocalDate fechaApertura;
    public Estado estado;
    public String clave;
    protected double saldoActual;
    public LocalDate fechaUltimoDeposito;

    public Cuenta(Cliente titular, LocalDate fechaApertura, Estado estado, String clave) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.fechaApertura = fechaApertura;
        this.estado = estado;
        this.clave = clave;
        this.saldoActual = saldoActual;
        this.fechaUltimoDeposito = fechaUltimoDeposito;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public Cliente getTitular() {
        return titular;
    }

    public LocalDate getFechaApertura() {
        return fechaApertura;
    }

    public Estado getEstado() {
        return estado;
    }

    public String getClave() {
        return clave;
    }

    public double getSaldoActual() {
        return saldoActual;
    }

    public LocalDate getFechaUltimoDeposito() {
        return fechaUltimoDeposito;
    }

    public void setTitular(Cliente titular) {
        this.titular = titular;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setFechaApertura(LocalDate fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public void setFechaUltimoDeposito(LocalDate fechaUltimoDeposito) {
        this.fechaUltimoDeposito = fechaUltimoDeposito;
    }

    public void setSaldoActual(double saldoActual) {
        this.saldoActual = saldoActual;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "numeroCuenta='" + numeroCuenta + '\'' +
                ", titular=" + titular +
                ", fechaApertura=" + fechaApertura +
                ", estado=" + estado +
                ", clave='" + clave + '\'' +
                ", saldoActual=" + saldoActual +
                ", fechaUltimoMovimiento=" + fechaUltimoDeposito +
                '}';
    }


    //METODOS

    public abstract Movimiento retiro(Cuenta cuenta,double monto, TipoMovimiento tipoMovimiento);

    public Movimiento deposito(Cuenta cuenta,double monto) {
        Movimiento nuevo= null;
        if (cuenta != null  &&  cuenta.getEstado() == Estado.ACTIVA) {
            TipoMovimiento tipoMovimiento = TipoMovimiento.DEPOSITAR;
            LocalDate fecha = LocalDate.now();
            nuevo = new Movimiento(numeroCuenta, tipoMovimiento, fecha, monto);
            this.saldoActual += monto;
            cuenta.setFechaUltimoDeposito(fecha);
        }return nuevo;
    }

    public void crearNumeroCuenta(ArrayList cuentasExistentes) {
        int  contador = cuentasExistentes.size() + 1;
        String numeroGenerado = Integer.toString(contador);
        this.numeroCuenta = numeroGenerado;
    }

    public void bloquear(Cuenta cuenta) {
        cuenta.setEstado(Estado.BLOQUEADA);
    }

    public void activar(Cuenta cuenta) {
        cuenta.setEstado(Estado.ACTIVA);
    }



}
