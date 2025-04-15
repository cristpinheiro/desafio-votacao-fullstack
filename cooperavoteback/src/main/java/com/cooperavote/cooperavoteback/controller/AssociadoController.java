package com.cooperavote.cooperavoteback.controller;

import com.cooperavote.cooperavoteback.dto.AssociadoDTO;
import com.cooperavote.cooperavoteback.service.AssociadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/associados")
@RequiredArgsConstructor
public class AssociadoController {

    private final AssociadoService associadoService;

    @PostMapping
    public ResponseEntity<String> salvar(@RequestBody AssociadoDTO dto) {
        associadoService.salvar(dto);
        return ResponseEntity.ok("Associado registrado com sucesso!");
    }
}