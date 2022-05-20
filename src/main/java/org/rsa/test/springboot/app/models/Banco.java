package org.rsa.test.springboot.app.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Banco {

    private Long id;

    private String nombre;

    private int totalTransferencias;

}
