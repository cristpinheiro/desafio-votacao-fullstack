package com.cooperavote.cooperavoteback.mapper;

import com.cooperavote.cooperavoteback.dto.PautaDTO;
import com.cooperavote.cooperavoteback.entity.Pauta;

public class PautaMapper {

    public static Pauta toEntity(PautaDTO dto) {
        Pauta pauta = new Pauta();
        pauta.setTitulo(dto.getTitulo());
        pauta.setDescricao(dto.getDescricao());
        return pauta;
    }

    public static PautaDTO toDto(Pauta pauta) {
        PautaDTO dto = new PautaDTO();
        dto.setTitulo(pauta.getTitulo());
        dto.setDescricao(pauta.getDescricao());
        return dto;
    }
}