package org.rsa.test.springboot.app.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransaccionDto {

    private Long bancoId;

    private Long cuentaOrigenId;

    private Long cuentaDestinoId;

    private BigDecimal monto;

}
