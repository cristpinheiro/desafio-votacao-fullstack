package com.cooperavote.cooperavoteback.dto;

import lombok.Data;

@Data
public class AbrirSessaoDTO {
    private Long pautaId;
    private Integer duracaoEmMinutos; // Opcional, se null usar 1 minuto como default
}