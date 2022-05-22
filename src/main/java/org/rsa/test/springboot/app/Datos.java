package org.rsa.test.springboot.app;

import org.rsa.test.springboot.app.models.Banco;
import org.rsa.test.springboot.app.models.Cuenta;

import java.math.BigDecimal;

public class Datos {

    public static Cuenta crearCuenta001() {
        return new Cuenta(1L, "Andrés", new BigDecimal("1000"));
    }

    public static Cuenta crearCuenta002() {
        return new Cuenta(2L, "Roberto", new BigDecimal("2000"));
    }

    public static Banco crearBanco() {
        return new Banco(1L, "Banco financiero", 0);
    }

}
