package com.cooperavote.cooperavoteback.repository;

import com.cooperavote.cooperavoteback.entity.Associado;
import com.cooperavote.cooperavoteback.entity.Pauta;
import com.cooperavote.cooperavoteback.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VotoRepository extends JpaRepository<Voto, Long> {

    Optional<Voto> findByAssociadoAndPauta(Associado associado, Pauta pauta);

    List<Voto> findAllByPauta(Pauta pauta);
}