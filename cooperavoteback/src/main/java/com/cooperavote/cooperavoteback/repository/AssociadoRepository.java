package com.cooperavote.cooperavoteback.repository;

import com.cooperavote.cooperavoteback.entity.Associado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssociadoRepository extends JpaRepository<Associado, Long> {
    Optional<Associado> findByCpf(String cpf);
}