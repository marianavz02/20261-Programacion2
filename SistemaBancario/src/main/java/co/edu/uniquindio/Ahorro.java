package co.edu.uniquindio;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Ahorro extends Cuenta {
    private double interesAnual = 0.036;
    private double saldoMinimo = 100000;

    public Ahorro(Cliente titular, LocalDate fechaApertura, Estado estado, String clave) {
        super(titular, fechaApertura, estado, clave);
    }

    @Override
    public Movimiento retiro(Cuenta cuenta, double monto, TipoMovimiento tipoMovimiento) {
        Movimiento movimiento = null;
        double restante = saldoActual - monto;
        if (restante > saldoMinimo) {
            String numeroCuenta = cuenta.getNumeroCuenta();
            tipoMovimiento = TipoMovimiento.RETIRAR;
            LocalDate fecha = LocalDate.now();

            saldoActual -= monto;

            movimiento= new Movimiento(numeroCuenta, tipoMovimiento, fecha, monto);
        }
        return movimiento;
    }

    @Override
    public Movimiento deposito(Cuenta cuenta, double monto) {
        return super.deposito(cuenta, monto);
    }

    @Override
    public void crearNumeroCuenta(ArrayList cuentasExistentes) {
        super.crearNumeroCuenta(cuentasExistentes);
    }
}
