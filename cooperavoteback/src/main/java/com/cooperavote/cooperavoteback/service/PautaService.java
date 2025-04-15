package com.cooperavote.cooperavoteback.service;

import com.cooperavote.cooperavoteback.dto.PautaDTO;
import com.cooperavote.cooperavoteback.entity.Pauta;
import com.cooperavote.cooperavoteback.mapper.PautaMapper;
import com.cooperavote.cooperavoteback.repository.PautaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PautaService {

    private final PautaRepository repository;

    public PautaService(PautaRepository repository) {
        this.repository = repository;
    }

    public PautaDTO criarPauta(PautaDTO dto) {
        Pauta pauta = PautaMapper.toEntity(dto);
        pauta.setInicio(null); // Ainda não iniciada
        pauta.setFim(null);
        Pauta save = repository.save(pauta);
        return PautaMapper.toDto(save);
    }

    public Pauta abrirSessao(Long idPauta, Long duracaoEmMinutos) {
        Pauta pauta = repository.findById(idPauta).orElseThrow(() ->
                new RuntimeException("Pauta não encontrada"));

        if (pauta.getInicio() != null) {
            throw new RuntimeException("Sessão de votação já está aberta para esta pauta");
        }

        LocalDateTime agora = LocalDateTime.now();
        pauta.setInicio(agora);
        pauta.setFim(agora.plusMinutes(duracaoEmMinutos != null ? duracaoEmMinutos : 1));

        return repository.save(pauta);
    }

    public Pauta buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new RuntimeException("Pauta não encontrada"));
    }
}