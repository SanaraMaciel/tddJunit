package br.com.sanara.tdd.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import br.com.sanara.tdd.modelo.Funcionario;
import br.com.sanara.tdd.service.BonusService;

class BonusServiceTest {

	@Test
	void bonusDeveriaSerZeroParaFuncionarioComSalarioMuitoAlto() {

		BonusService service = new BonusService();

		// verificando se o método está lançando uma exceção
//		assertThrows(IllegalArgumentException.class,
//				() -> service.calcularBonus(new Funcionario("Sanara", LocalDate.now(), new BigDecimal("25000.00"))));
		
		try {
			service.calcularBonus(new Funcionario("Sanara", LocalDate.now(), new BigDecimal("25000.00")));
			//fail() método do jUnit para forçar um método a falhar
			fail("Exceção não ocorreu");
		} catch (Exception e) {			
			assertEquals("Funcionário com salário maior do que R$10000 nao pode receber bonus!", e.getMessage());
		}
	}

	@Test
	void bonusDeveriaSerDezPorCentoDoSalario() {

		BonusService service = new BonusService();
		BigDecimal bonus = service.calcularBonus(new Funcionario("Sanara", LocalDate.now(), new BigDecimal("2500.00")));

		assertEquals(new BigDecimal("250.00"), bonus);
	}

	@Test
	void bonusDeveriaSerDezPorCentoParaSalarioDeExatamenteDezMilReais() {

		BonusService service = new BonusService();
		BigDecimal bonus = service
				.calcularBonus(new Funcionario("Sanara", LocalDate.now(), new BigDecimal("10000.00")));

		assertEquals(new BigDecimal("1000.00"), bonus);
	}

}
