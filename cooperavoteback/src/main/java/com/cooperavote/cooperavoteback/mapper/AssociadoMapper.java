package com.cooperavote.cooperavoteback.mapper;

import com.cooperavote.cooperavoteback.dto.AssociadoDTO;
import com.cooperavote.cooperavoteback.entity.Associado;

public class AssociadoMapper {

    public static Associado toEntity(AssociadoDTO dto) {
        Associado associado = new Associado();
        associado.setNome(dto.getNome());
        associado.setCpf(dto.getCpf());
        return associado;
    }

    public static AssociadoDTO toDTO(Associado entity) {
        AssociadoDTO dto = new AssociadoDTO();
        dto.setNome(entity.getNome());
        dto.setCpf(entity.getCpf());
        return dto;
    }
}