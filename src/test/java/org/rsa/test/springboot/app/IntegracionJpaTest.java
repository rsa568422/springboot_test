package org.rsa.test.springboot.app;

import org.junit.jupiter.api.Test;
import org.rsa.test.springboot.app.models.Cuenta;
import org.rsa.test.springboot.app.repositories.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class IntegracionJpaTest {

    @Autowired
    CuentaRepository cuentaRepository;

    @Test
    void testFindById() {
        Optional<Cuenta> cuenta = this.cuentaRepository.findById(1L);

        assertTrue(cuenta.isPresent());
        assertEquals("Andrés", cuenta.orElseThrow().getPersona());
    }

    @Test
    void testFindByPersona() {
        Optional<Cuenta> cuenta = this.cuentaRepository.findByPersona("Andrés");

        assertTrue(cuenta.isPresent());
        assertEquals("1000.00", cuenta.orElseThrow().getSaldo().toPlainString());
    }

    @Test
    void testFindByPersonaThrowException() {
        Optional<Cuenta> cuenta = this.cuentaRepository.findByPersona("John");

        assertThrows(NoSuchElementException.class, cuenta::orElseThrow);
        assertFalse(cuenta.isPresent());
    }

    @Test
    void testFindAll() {
        List<Cuenta> cuentas = this.cuentaRepository.findAll();

        assertFalse(cuentas.isEmpty());
        assertEquals(2, cuentas.size());
    }

    @Test
    void testSave() {
        Cuenta cuentaPepe = new Cuenta(null, "Pepe", new BigDecimal("3000"));

        Cuenta cuenta = this.cuentaRepository.save(cuentaPepe);

        assertEquals("Pepe", cuenta.getPersona());
        assertEquals("3000", cuenta.getSaldo().toPlainString());
        //assertEquals(3, cuenta.getId());
    }

}
