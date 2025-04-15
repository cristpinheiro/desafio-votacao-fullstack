package com.cooperavote.cooperavoteback.dto;

import lombok.Data;

@Data
public class VotoDTO {
    private Long pautaId;
    private String cpfAssociado;
    private String voto; // "SIM" ou "NAO"
}