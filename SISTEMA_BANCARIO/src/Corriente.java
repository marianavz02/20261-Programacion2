import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Corriente extends Cuenta{
    private double topeSobregiro= 500000;
    private double comisionMensual= 150000;
    private double interesMensualSobregiro= 0.02;
    private double sobregiro=0;

    public Corriente(Cliente titular, LocalDate fechaApertura, Estado estado, String clave) {
        super(titular, fechaApertura, estado, clave);
    }

    public double getTopeSobregiro() {
        return topeSobregiro;
    }

    public double getComisionMensual() {
        return comisionMensual;
    }

    public double getInteresMensualSobregiro() {
        return interesMensualSobregiro;
    }

    public double getSobregiro() {
        return sobregiro;
    }

    @Override
    public Movimiento retiro(Cuenta cuenta, double monto, TipoMovimiento tipoMovimiento) {
        Movimiento retiro = null;
        double restante = saldoActual - monto;
        if (restante <= 0 ) {
            String numeroCuenta = cuenta.getNumeroCuenta();
            tipoMovimiento = TipoMovimiento.RETIRAR;
            LocalDate fecha = LocalDate.now();

            saldoActual -= monto;
            sobregiro = restante;

            Movimiento movimiento= new Movimiento(numeroCuenta, tipoMovimiento, fecha, monto);
        } else if(restante > 0){
            String numeroCuenta = cuenta.getNumeroCuenta();
            tipoMovimiento = TipoMovimiento.RETIRAR;
            LocalDate fecha = LocalDate.now();

            saldoActual -= monto;
            Movimiento movimiento= new Movimiento(numeroCuenta, tipoMovimiento, fecha, monto);
        }
        return retiro;
    }
}
