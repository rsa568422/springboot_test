package org.rsa.test.springboot.app.repositories;

import org.rsa.test.springboot.app.models.Banco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BancoRepository extends JpaRepository<Banco, Long> {
//    List<Banco> findAll();
//    Banco findById(Long id);
//    void update(Banco banco);

}
