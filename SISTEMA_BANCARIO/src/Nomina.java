import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
        Movimiento retiro = null;
        boolean AplicaComision = false;

        long meses = ChronoUnit.MONTHS.between(fechaUltimoDeposito, LocalDate.now());
        if (meses <= 3){comision = 0;}
        if (saldoActual > monto + comision) {
            String numeroCuenta = cuenta.getNumeroCuenta();
            tipoMovimiento = TipoMovimiento.RETIRAR;
            LocalDate fecha = LocalDate.now();

            saldoActual -= (monto + comision);

            Movimiento movimiento= new Movimiento(numeroCuenta, tipoMovimiento, fecha, monto);
        }
        return retiro;
    }
}
