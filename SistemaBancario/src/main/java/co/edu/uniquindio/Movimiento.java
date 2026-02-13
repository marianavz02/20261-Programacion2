package co.edu.uniquindio;
import java.time.LocalDate;

public class Movimiento {
    private String numeroCuenta;
    private TipoMovimiento tipoMovimiento;
    private LocalDate fecha;
    private double monto;

    public Movimiento(String numeroCuenta, TipoMovimiento tipoMovimiento, LocalDate fecha) {
        this.numeroCuenta = numeroCuenta;
        this.tipoMovimiento = tipoMovimiento;
        this.fecha = fecha;
        this.monto = 0;
    }

    public Movimiento(String numeroCuenta, TipoMovimiento tipoMovimiento, LocalDate fecha, double monto) {
        this.numeroCuenta = numeroCuenta;
        this.tipoMovimiento = tipoMovimiento;
        this.fecha = fecha;
        this.monto = monto;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public double getMonto() {
        return monto;
    }


    @Override
    public String toString() {
        return "Movimiento{" +
                "numeroCuenta='" + numeroCuenta + '\'' +
                ", tipoMovimiento=" + tipoMovimiento +
                ", fecha=" + fecha +
                ", monto=" + monto +
                '}';
    }


}
