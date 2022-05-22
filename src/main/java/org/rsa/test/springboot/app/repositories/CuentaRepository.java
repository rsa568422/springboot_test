package org.rsa.test.springboot.app.repositories;

import org.rsa.test.springboot.app.models.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

}
