package br.com.processo.repository;

import br.com.processo.entity.Processo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessoRepository extends JpaRepository<Processo, Long> {

    Optional<Processo> findByCodigoProcesso(String codigoProcesso);
    
} 