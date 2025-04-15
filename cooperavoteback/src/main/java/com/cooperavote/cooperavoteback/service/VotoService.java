package com.cooperavote.cooperavoteback.service;

import com.cooperavote.cooperavoteback.dto.ResultadoVotacaoDTO;
import com.cooperavote.cooperavoteback.dto.VotoDTO;
import com.cooperavote.cooperavoteback.entity.Associado;
import com.cooperavote.cooperavoteback.entity.Pauta;
import com.cooperavote.cooperavoteback.entity.Voto;
import com.cooperavote.cooperavoteback.enums.OpcaoVoto;
import com.cooperavote.cooperavoteback.mapper.ResultadoVotacaoMapper;
import com.cooperavote.cooperavoteback.repository.VotoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VotoService {

    private final VotoRepository repository;

    private final AssociadoService associadoService;

    private final PautaService pautaService;

    public VotoService(VotoRepository repository, AssociadoService associadoService, PautaService pautaService) {
        this.repository = repository;
        this.associadoService = associadoService;
        this.pautaService = pautaService;
    }

    public Voto votar(VotoDTO dto) {
        Pauta pauta = pautaService.buscarPorId(dto.getPautaId());
        Associado associado = associadoService.buscarPorCpf(dto.getCpfAssociado());

        // Verifica se a sessão está ativa
        if (pauta.getInicio() == null || pauta.getFim().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Sessão de votação encerrada ou não iniciada.");
        }

        // Verifica se o associado já votou
        Optional<Voto> jaVotou = repository.findByAssociadoAndPauta(associado, pauta);
        if (jaVotou.isPresent()) {
            throw new RuntimeException("Associado já votou nesta pauta.");
        }

        Voto voto = new Voto();
        voto.setAssociado(associado);
        voto.setPauta(pauta);
        voto.setOpcao(OpcaoVoto.valueOf(dto.getVoto().toUpperCase()));

        return repository.save(voto);
    }

    public ResultadoVotacaoDTO resultado(Long pautaId) {
        Pauta pauta = pautaService.buscarPorId(pautaId);
        List<Voto> votos = repository.findAllByPauta(pauta);
        long totalSim = votos.stream()
                .filter(v -> v.getOpcao() == OpcaoVoto.SIM)
                .count();
        long totalNao = votos.stream()
                .filter(v -> v.getOpcao() == OpcaoVoto.SIM)
                .count();
        return ResultadoVotacaoMapper.toDTO(pauta, Integer.parseInt(String.valueOf(totalSim)), Integer.parseInt(String.valueOf(totalSim)));
    }

}