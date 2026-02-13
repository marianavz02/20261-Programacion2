package co.edu.uniquindio;
import co.edu.uniquindio.Ahorro;
import co.edu.uniquindio.Banco;
import co.edu.uniquindio.Cliente;
import co.edu.uniquindio.Corriente;
import co.edu.uniquindio.Cuenta;
import co.edu.uniquindio.Estado;
import co.edu.uniquindio.Movimiento;
import co.edu.uniquindio.Nomina;
import co.edu.uniquindio.TipoMovimiento;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.util.logging.Logger;




public class BancoTest {

    private static final Logger LOG = Logger.getLogger(BancoTest.class.getName());

    @Test
    @DisplayName("Prueba de funcionalidad metodo crear cliente")
    public void crearCliente() {
        LOG.info("Iniciando creacion de cliente");
        Banco banco = new Banco("Banco1");
        boolean resultado = banco.crearCliente("lina","123","lina@","111","armenia",LocalDate.now());
        assertTrue(resultado);
        LOG.info("fIN PRUEBA");

    }

    @Test
    @DisplayName("Prueba cuenta de ahorros(caso 1)")
    public void casoUno() {
        LOG.info("Iniciando");
        Banco banco = new Banco("Banco1");
        banco.crearCliente("Maria","123","lina@","111","armenia",LocalDate.now());
        banco.crearAhorro("123", "123");
        Cuenta cuenta = banco.buscarCuenta("1");
        banco.deposito("1",500000);
        boolean resultado =banco.retiro("1",450000,TipoMovimiento.RETIRAR);

        assertFalse(resultado);
        LOG.info("fIN PRUEBA");
    }

    @Test
    @DisplayName("Prueba cuenta corriente(caso 2)")
    public void casoDos() {
        LOG.info("Iniciando");
        Banco banco = new Banco("Banco1");
        banco.crearCliente("Maria","123","lina@","111","armenia",LocalDate.now());
        banco.crearCorriente("123", "123");
        Cuenta cuenta = banco.buscarCuenta("1");
        banco.deposito("1",50000);
        boolean resultado = banco.retiro("1",400000,TipoMovimiento.RETIRAR);
        assertTrue(resultado);
        LOG.info("fIN PRUEBA");
    }

    @Test
    @DisplayName("Prueba cuenta nomina(caso 3)")
    public void casoTres() {
        LOG.info("Iniciando");
        Banco banco = new Banco("Banco1");
        banco.crearCliente("Maria","123","lina@","111","armenia",LocalDate.now());
        banco.crearNomina("123", "123");
        Cuenta cuenta = banco.buscarCuenta("1");
        cuenta.setFechaUltimoDeposito(LocalDate.of(2025,01,01));
        banco.ajusteNomina();

        assertEquals(cuenta.getEstado(),Estado.BLOQUEADA);

        LOG.info("fIN PRUEBA");
    }



}
