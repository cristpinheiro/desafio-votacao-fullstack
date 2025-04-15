package com.cooperavote.cooperavoteback.service;

import com.cooperavote.cooperavoteback.dto.PautaDTO;
import com.cooperavote.cooperavoteback.entity.Pauta;
import com.cooperavote.cooperavoteback.repository.PautaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PautaServiceTest {

    @InjectMocks
    private PautaService pautaService;

    @Mock
    private PautaRepository pautaRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarPauta() {
        // Arrange
        PautaDTO dto = new PautaDTO();
        dto.setTitulo("Pauta 1");
        dto.setDescricao("Descrição da Pauta 1");

        Pauta pauta = new Pauta();
        pauta.setTitulo(dto.getTitulo());
        pauta.setDescricao(dto.getDescricao());
        pauta.setInicio(null);
        pauta.setFim(null);

        when(pautaRepository.save(any(Pauta.class))).thenReturn(pauta);

        // Act
        PautaDTO result = pautaService.criarPauta(dto);

        // Assert
        assertNotNull(result);
        assertEquals(dto.getTitulo(), result.getTitulo());
        assertEquals(dto.getDescricao(), result.getDescricao());
        verify(pautaRepository, times(1)).save(any(Pauta.class));
    }

    @Test
    public void testAbrirSessaoPautaExistente() {
        // Arrange
        Long idPauta = 1L;
        Long duracaoEmMinutos = 10L;
        Pauta pauta = new Pauta();
        pauta.setId(idPauta);
        pauta.setInicio(null);

        when(pautaRepository.findById(idPauta)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            pautaService.abrirSessao(idPauta, duracaoEmMinutos);
        });

        assertEquals("Pauta não encontrada", exception.getMessage());
        verify(pautaRepository, times(1)).findById(idPauta);
    }

    @Test
    public void testAbrirSessaoPautaJaAberta() {
        // Arrange
        Long idPauta = 1L;
        Pauta pauta = new Pauta();
        pauta.setId(idPauta);
        pauta.setInicio(LocalDateTime.now());

        when(pautaRepository.findById(idPauta)).thenReturn(Optional.of(pauta));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            pautaService.abrirSessao(idPauta, 10L);
        });

        assertEquals("Sessão de votação já está aberta para esta pauta", exception.getMessage());
        verify(pautaRepository, times(1)).findById(idPauta);
    }

    @Test
    public void testBuscarPorIdEncontrado() {
        // Arrange
        Long id = 1L;
        Pauta pauta = new Pauta();
        pauta.setId(id);
        when(pautaRepository.findById(id)).thenReturn(Optional.of(pauta));

        // Act
        Pauta result = pautaService.buscarPorId(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(pautaRepository, times(1)).findById(id);
    }

    @Test
    public void testBuscarPorIdNaoEncontrado() {
        // Arrange
        Long id = 1L;
        when(pautaRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            pautaService.buscarPorId(id);
        });

        assertEquals("Pauta não encontrada", exception.getMessage());
        verify(pautaRepository, times(1)).findById(id);
    }
}