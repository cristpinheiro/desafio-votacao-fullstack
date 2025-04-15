package com.cooperavote.cooperavoteback.controller;

import com.cooperavote.cooperavoteback.dto.ResultadoVotacaoDTO;
import com.cooperavote.cooperavoteback.service.VotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resultados")
@RequiredArgsConstructor
public class ResultadoVotacaoController {

    private final VotoService votoService;

    @GetMapping("/{idPauta}")
    public ResponseEntity<ResultadoVotacaoDTO> resultado(@PathVariable Long idPauta) {
        ResultadoVotacaoDTO resultado = votoService.resultado(idPauta);
        return ResponseEntity.ok(resultado);
    }
}