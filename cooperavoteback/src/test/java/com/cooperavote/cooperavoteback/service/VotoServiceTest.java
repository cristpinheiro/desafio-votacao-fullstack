package com.cooperavote.cooperavoteback.service;

import com.cooperavote.cooperavoteback.dto.ResultadoVotacaoDTO;
import com.cooperavote.cooperavoteback.dto.VotoDTO;
import com.cooperavote.cooperavoteback.entity.Associado;
import com.cooperavote.cooperavoteback.entity.Pauta;
import com.cooperavote.cooperavoteback.entity.Voto;
import com.cooperavote.cooperavoteback.enums.OpcaoVoto;
import com.cooperavote.cooperavoteback.repository.VotoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class VotoServiceTest {

    @InjectMocks
    private VotoService votoService;

    @Mock
    private VotoRepository votoRepository;

    @Mock
    private AssociadoService associadoService;

    @Mock
    private PautaService pautaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testVotarComSessaoAtivaEAssociadoNaoVotou() {
        // Arrange
        VotoDTO dto = new VotoDTO();
        dto.setPautaId(1L);
        dto.setCpfAssociado("12345678901");
        dto.setVoto("SIM");

        Pauta pauta = new Pauta();
        pauta.setId(1L);
        pauta.setInicio(LocalDateTime.now().minusMinutes(5));
        pauta.setFim(LocalDateTime.now().plusMinutes(5));

        Associado associado = new Associado();
        associado.setCpf("12345678901");

        when(pautaService.buscarPorId(dto.getPautaId())).thenReturn(pauta);
        when(associadoService.buscarPorCpf(dto.getCpfAssociado())).thenReturn(associado);
        when(votoRepository.findByAssociadoAndPauta(associado, pauta)).thenReturn(Optional.empty());
        when(votoRepository.save(any(Voto.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Voto result = votoService.votar(dto);

        // Assert
        assertNotNull(result);
        assertEquals(associado, result.getAssociado());
        assertEquals(pauta, result.getPauta());
        assertEquals(OpcaoVoto.SIM, result.getOpcao());
        verify(votoRepository, times(1)).save(any(Voto.class));
    }

    @Test
    public void testVotarSessaoEncerrada() {
        // Arrange
        VotoDTO dto = new VotoDTO();
        dto.setPautaId(1L);
        dto.setCpfAssociado("12345678901");

        Pauta pauta = new Pauta();
        pauta.setId(1L);
        pauta.setInicio(LocalDateTime.now().minusMinutes(10));
        pauta.setFim(LocalDateTime.now().minusMinutes(5));

        when(pautaService.buscarPorId(dto.getPautaId())).thenReturn(pauta);

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            votoService.votar(dto);
        });
        assertEquals("Sessão de votação encerrada ou não iniciada.", exception.getMessage());
        verify(votoRepository, never()).save(any(Voto.class));
    }

    @Test
    public void testVotarAssociadoJaVotou() {
        // Arrange
        VotoDTO dto = new VotoDTO();
        dto.setPautaId(1L);
        dto.setCpfAssociado("12345678901");

        Pauta pauta = new Pauta();
        pauta.setId(1L);
        pauta.setInicio(LocalDateTime.now().minusMinutes(5));
        pauta.setFim(LocalDateTime.now().plusMinutes(5));

        Associado associado = new Associado();
        associado.setCpf("12345678901");

        when(pautaService.buscarPorId(dto.getPautaId())).thenReturn(pauta);
        when(associadoService.buscarPorCpf(dto.getCpfAssociado())).thenReturn(associado);
        when(votoRepository.findByAssociadoAndPauta(associado, pauta)).thenReturn(Optional.of(new Voto()));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            votoService.votar(dto);
        });
        assertEquals("Associado já votou nesta pauta.", exception.getMessage());
        verify(votoRepository, never()).save(any(Voto.class));
    }

    @Test
    public void testResultadoVotacao() {
        // Arrange
        Long pautaId = 1L;
        Pauta pauta = new Pauta();
        pauta.setId(pautaId);

        // Simulando votos
        Voto votoSim = new Voto();
        votoSim.setOpcao(OpcaoVoto.SIM);
        Voto votoNao = new Voto();
        votoNao.setOpcao(OpcaoVoto.NAO);

        when(pautaService.buscarPorId(pautaId)).thenReturn(pauta);
        when(votoRepository.findAllByPauta(pauta)).thenReturn(List.of(votoSim, votoNao));

        // Act
        ResultadoVotacaoDTO resultado = votoService.resultado(pautaId);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.getTotalSim());
        assertEquals(1, resultado.getTotalNao());
        verify(pautaService, times(1)).buscarPorId(pautaId);
        verify(votoRepository, times(1)).findAllByPauta(pauta);
    }
}