package org.rsa.test.springboot.app.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "bancos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Banco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(name = "total_transferencias")
    private int totalTransferencias;

}
