package org.rsa.test.springboot.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rsa.test.springboot.app.exceptions.DineroInsuficienteException;
import org.rsa.test.springboot.app.models.Banco;
import org.rsa.test.springboot.app.models.Cuenta;
import org.rsa.test.springboot.app.repositories.BancoRepository;
import org.rsa.test.springboot.app.repositories.CuentaRepository;
import org.rsa.test.springboot.app.services.CuentaService;
import org.rsa.test.springboot.app.services.CuentaServiceImpl;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class SpringbootTestApplicationTests {

	CuentaService service;

	CuentaRepository cuentaRepository;

	BancoRepository bancoRepository;

	@BeforeEach
	void setUp() {
		this.cuentaRepository = mock(CuentaRepository.class);
		this.bancoRepository = mock(BancoRepository.class);
		this.service = new CuentaServiceImpl(this.cuentaRepository, this.bancoRepository);
	}

	@Test
	void contextLoads() {
		Cuenta cuentaOrigen = Datos.crearCuenta001();
		Cuenta cuentaDestino = Datos.crearCuenta002();
		Banco banco = Datos.crearBanco();

		when(this.cuentaRepository.findById(1L)).thenReturn(cuentaOrigen);
		when(this.cuentaRepository.findById(2L)).thenReturn(cuentaDestino);
		when(this.bancoRepository.findById(1L)).thenReturn(banco);

		BigDecimal saldoOrigen = this.service.revisarSaldo(1L);
		BigDecimal saldoDestino = this.service.revisarSaldo(2L);

		assertEquals("1000", saldoOrigen.toPlainString());
		assertEquals("2000", saldoDestino.toPlainString());

		this.service.transferir(1L, 1L, 2L, new BigDecimal("100"));

		saldoOrigen = this.service.revisarSaldo(1L);
		saldoDestino = this.service.revisarSaldo(2L);

		assertEquals("900", saldoOrigen.toPlainString());
		assertEquals("2100", saldoDestino.toPlainString());

		int total = this.service.revisarTotalTransferencias(1L);

		assertEquals(1, total);

		verify(this.cuentaRepository, times(3)).findById(1L);
		verify(this.cuentaRepository, times(3)).findById(2L);
		verify(this.cuentaRepository, times(2)).update(any(Cuenta.class));

		verify(this.bancoRepository, times(2)).findById(1L);
		verify(this.bancoRepository).update(any(Banco.class));

		verify(this.cuentaRepository, times(6)).findById(anyLong());
		verify(this.cuentaRepository, never()).findAll();
	}

	@Test
	void contextLoads2() {
		Cuenta cuentaOrigen = Datos.crearCuenta001();
		Cuenta cuentaDestino = Datos.crearCuenta002();
		Banco banco = Datos.crearBanco();

		when(this.cuentaRepository.findById(1L)).thenReturn(cuentaOrigen);
		when(this.cuentaRepository.findById(2L)).thenReturn(cuentaDestino);
		when(this.bancoRepository.findById(1L)).thenReturn(banco);

		BigDecimal saldoOrigen = this.service.revisarSaldo(1L);
		BigDecimal saldoDestino = this.service.revisarSaldo(2L);

		assertEquals("1000", saldoOrigen.toPlainString());
		assertEquals("2000", saldoDestino.toPlainString());

		assertThrows(DineroInsuficienteException.class, () -> this.service.transferir(1L, 1L, 2L, new BigDecimal("1200")));

		saldoOrigen = this.service.revisarSaldo(1L);
		saldoDestino = this.service.revisarSaldo(2L);

		assertEquals("1000", saldoOrigen.toPlainString());
		assertEquals("2000", saldoDestino.toPlainString());

		int total = this.service.revisarTotalTransferencias(1L);

		assertEquals(0, total);

		verify(this.cuentaRepository, times(3)).findById(1L);
		verify(this.cuentaRepository, times(2)).findById(2L);
		verify(this.cuentaRepository, never()).update(any(Cuenta.class));

		verify(this.bancoRepository, times(1)).findById(1L);
		verify(this.bancoRepository, never()).update(any(Banco.class));

		verify(this.cuentaRepository, times(5)).findById(anyLong());
		verify(this.cuentaRepository, never()).findAll();
	}

	@Test
	void contextLoads3() {
		when(this.cuentaRepository.findById(1L)).thenReturn(Datos.crearCuenta001());

		Cuenta cuenta1 = this.service.findById(1L);
		Cuenta cuenta2 = this.service.findById(1L);

		assertSame(cuenta1, cuenta2);
		assertEquals(cuenta1, cuenta2);
		assertEquals("Andrés", cuenta1.getPersona());
		assertEquals("Andrés", cuenta2.getPersona());
		verify(this.cuentaRepository, times(2)).findById(1L);
	}

}
