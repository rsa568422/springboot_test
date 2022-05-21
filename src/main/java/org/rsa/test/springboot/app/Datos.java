package org.rsa.test.springboot.app;

import org.rsa.test.springboot.app.models.Banco;
import org.rsa.test.springboot.app.models.Cuenta;

import java.math.BigDecimal;

public class Datos {

    public static final Cuenta CUENTA_001 = new Cuenta(1L, "Andrés", new BigDecimal("1000"));

    public static final Cuenta CUENTA_002 = new Cuenta(2L, "Roberto", new BigDecimal("2000"));

    public static final Banco BANCO = new Banco(1L, "Banco financiero", 0);

}