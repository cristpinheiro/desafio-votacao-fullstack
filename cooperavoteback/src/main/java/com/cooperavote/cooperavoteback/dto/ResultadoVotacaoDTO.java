package com.cooperavote.cooperavoteback.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultadoVotacaoDTO {
    private Long pautaId;
    private String titulo;
    private Integer totalVotos;
    private Integer totalSim;
    private Integer totalNao;
    private String resultado; // "APROVADA" ou "REJEITADA"
}