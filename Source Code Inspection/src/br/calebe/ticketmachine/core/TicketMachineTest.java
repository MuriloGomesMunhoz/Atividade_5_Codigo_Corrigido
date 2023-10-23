package br.calebe.ticketmachine.core;

import br.calebe.ticketmachine.core.TicketMachine;
import br.calebe.ticketmachine.exception.PapelMoedaInvalidaException;
import br.calebe.ticketmachine.exception.SaldoInsuficienteException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TicketMachineTest {

    private TicketMachine ticketMachine;

    @Before
    public void setUp() {
        // Configurar a TicketMachine com um preço de bilhete de 10 unidades
        ticketMachine = new TicketMachine(10);
    }

    @Test
    public void testInserirPapelMoedaValido() throws PapelMoedaInvalidaException {
        // Inserir uma quantia válida (10) na máquina
        ticketMachine.inserir(10);

        // Verificar se o saldo é atualizado corretamente
        assertEquals(10, ticketMachine.getSaldo());
    }

    @Test(expected = PapelMoedaInvalidaException.class)
    public void testInserirPapelMoedaInvalido() throws PapelMoedaInvalidaException {
        // Tentar inserir uma quantia inválida (25) na máquina
        ticketMachine.inserir(25);
    }

    @Test
    public void testImprimirBilhete() throws SaldoInsuficienteException, PapelMoedaInvalidaException {
        // Inserir saldo suficiente (10)
        ticketMachine.inserir(10);

        // Imprimir o bilhete
        String bilhete = ticketMachine.imprimir();

        // Verificar se o bilhete gerado está correto
        assertEquals("*****************\n*** R$ 10,00 ****\n*****************\n", bilhete);
    }

    @Test(expected = SaldoInsuficienteException.class)
    public void testImprimirBilheteComSaldoInsuficiente() throws SaldoInsuficienteException {
        // Tentar imprimir um bilhete sem saldo suficiente
        ticketMachine.imprimir();
    }
}
