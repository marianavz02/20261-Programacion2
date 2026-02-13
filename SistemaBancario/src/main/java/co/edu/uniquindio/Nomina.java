package co.edu.uniquindio;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Nomina extends Cuenta{
    private double comision = 8000;


    public Nomina(Cliente titular, LocalDate fechaApertura, Estado estado, String clave) {
        super(titular, fechaApertura, estado, clave);
        this.comision = comision;
    }

    public double getComision() {
        return comision;
    }

    @Override
    public Movimiento retiro(Cuenta cuenta, double monto, TipoMovimiento tipoMovimiento) {
        Movimiento movimiento = null;
        boolean AplicaComision = false;

        long meses = ChronoUnit.MONTHS.between(fechaUltimoDeposito, LocalDate.now());
        if (meses <= 3){comision = 0;}
        if (saldoActual > monto + comision) {
            String numeroCuenta = cuenta.getNumeroCuenta();
            tipoMovimiento = TipoMovimiento.RETIRAR;
            LocalDate fecha = LocalDate.now();

            saldoActual -= (monto + comision);

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
