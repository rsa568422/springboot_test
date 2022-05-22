package org.rsa.test.springboot.app.services;

import org.rsa.test.springboot.app.models.Banco;
import org.rsa.test.springboot.app.models.Cuenta;
import org.rsa.test.springboot.app.repositories.BancoRepository;
import org.rsa.test.springboot.app.repositories.CuentaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepository;

    private final BancoRepository bancoRepository;

    public CuentaServiceImpl(CuentaRepository cuentaRepository, BancoRepository bancoRepository) {
        this.cuentaRepository = cuentaRepository;
        this.bancoRepository = bancoRepository;
    }

    @Override
    public Cuenta findById(Long id) {
        return this.cuentaRepository.findById(id);
    }

    @Override
    public int revisarTotalTransferencias(Long bancoId) {
        Banco banco = this.bancoRepository.findById(bancoId);
        return banco.getTotalTransferencias();
    }

    @Override
    public BigDecimal revisarSaldo(Long cuentaId) {
        Cuenta cuenta = this.cuentaRepository.findById(cuentaId);
        return cuenta.getSaldo();
    }

    @Override
    public void transferir(Long bancoId, Long numCuentaOrigen, Long numCuentaDestino, BigDecimal monto) {
        Cuenta cuentaOrigen = this.cuentaRepository.findById(numCuentaOrigen);
        cuentaOrigen.debito(monto);
        cuentaRepository.update(cuentaOrigen);

        Cuenta cuentaDestino = this.cuentaRepository.findById(numCuentaDestino);
        cuentaDestino.credito(monto);
        cuentaRepository.update(cuentaDestino);

        Banco banco = this.bancoRepository.findById(bancoId);
        int totalTransferencias = banco.getTotalTransferencias();
        banco.setTotalTransferencias(++totalTransferencias);
        bancoRepository.update(banco);
    }

}
