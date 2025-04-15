package com.cooperavote.cooperavoteback.mapper;

import com.cooperavote.cooperavoteback.dto.ResultadoVotacaoDTO;
import com.cooperavote.cooperavoteback.entity.Pauta;

public class ResultadoVotacaoMapper {

    public static ResultadoVotacaoDTO toDTO(Pauta pauta, int totalSim, int totalNao) {
        return ResultadoVotacaoDTO.builder()
                .pautaId(pauta.getId())
                .titulo(pauta.getTitulo())
                .totalVotos(totalSim + totalNao)
                .totalSim(totalSim)
                .totalNao(totalNao)
                .resultado(totalSim > totalNao ? "APROVADA" : "REJEITADA")
                .build();
    }
}