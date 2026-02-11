import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Ahorro extends Cuenta{
    private double interesAnual = 0.036;
    private double saldoMinimo = 100000;

    public Ahorro(Cliente titular, LocalDate fechaApertura, Estado estado, String clave) {
        super(titular, fechaApertura, estado, clave);
    }

    @Override
    public Movimiento retiro(Cuenta cuenta, double monto, TipoMovimiento tipoMovimiento) {
        Movimiento retiro = null;
        double restante = saldoActual - monto;
        if (restante > saldoMinimo) {
            String numeroCuenta = cuenta.getNumeroCuenta();
            tipoMovimiento = TipoMovimiento.RETIRAR;
            LocalDate fecha = LocalDate.now();

            saldoActual -= monto;

            Movimiento movimiento= new Movimiento(numeroCuenta, tipoMovimiento, fecha, monto);
        }
        return retiro;
    }
}
