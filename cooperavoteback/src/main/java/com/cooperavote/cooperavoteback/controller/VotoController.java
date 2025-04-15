package com.cooperavote.cooperavoteback.controller;

import com.cooperavote.cooperavoteback.dto.VotoDTO;
import com.cooperavote.cooperavoteback.service.VotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/votos")
@RequiredArgsConstructor
public class VotoController {

    private final VotoService votoService;

    @PostMapping
    public ResponseEntity<String> votar(@RequestBody VotoDTO dto) {
        votoService.votar(dto);
        return ResponseEntity.ok("Voto registrado com sucesso.");
    }
}