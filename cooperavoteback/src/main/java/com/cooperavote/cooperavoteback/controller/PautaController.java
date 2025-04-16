package com.cooperavote.cooperavoteback.controller;

import com.cooperavote.cooperavoteback.dto.PautaDTO;
import com.cooperavote.cooperavoteback.service.PautaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pautas")
@RequiredArgsConstructor
public class PautaController {

    private final PautaService pautaService;

    @PostMapping
    public ResponseEntity<PautaDTO> criarPauta(@RequestBody PautaDTO dto) {
        PautaDTO novaPauta = pautaService.criarPauta(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPauta);
    }

    @PostMapping("/{id}/abrir-sessao")
    public ResponseEntity<String> abrirSessao(
            @PathVariable Long id,
            @RequestParam(required = false) Long duracaoEmMinutos) {
        pautaService.abrirSessao(id, duracaoEmMinutos);
        return ResponseEntity.ok("Sessão aberta com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<PautaDTO>> listar() {
        final List<PautaDTO> pautasDto = pautaService.buscarTodas();
        return ResponseEntity.ok(pautasDto);
    }
}
