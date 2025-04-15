package com.cooperavote.cooperavoteback.service;

import com.cooperavote.cooperavoteback.dto.AssociadoDTO;
import com.cooperavote.cooperavoteback.entity.Associado;
import com.cooperavote.cooperavoteback.repository.AssociadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class AssociadoServiceTest {

    @InjectMocks
    private AssociadoService associadoService;

    @Mock
    private AssociadoRepository associadoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSalvar() {
        // Arrange
        AssociadoDTO dto = new AssociadoDTO();
        dto.setNome("João");
        dto.setCpf("12345678901");

        Associado associado = new Associado();
        associado.setNome(dto.getNome());
        associado.setCpf(dto.getCpf());

        when(associadoRepository.save(any(Associado.class))).thenReturn(associado);

        // Act
        AssociadoDTO result = associadoService.salvar(dto);

        // Assert
        assertNotNull(result);
        assertEquals(dto.getNome(), result.getNome());
        assertEquals(dto.getCpf(), result.getCpf());
        verify(associadoRepository, times(1)).save(any(Associado.class));
    }

    @Test
    public void testExistePorCpfTrue() {
        // Arrange
        String cpf = "12345678901";
        when(associadoRepository.findByCpf(cpf)).thenReturn(Optional.of(new Associado()));

        // Act
        boolean result = associadoService.existePorCpf(cpf);

        // Assert
        assertTrue(result);
        verify(associadoRepository, times(1)).findByCpf(cpf);
    }

    @Test
    public void testExistePorCpfFalse() {
        // Arrange
        String cpf = "12345678901";
        when(associadoRepository.findByCpf(cpf)).thenReturn(Optional.empty());

        // Act
        boolean result = associadoService.existePorCpf(cpf);

        // Assert
        assertFalse(result);
        verify(associadoRepository, times(1)).findByCpf(cpf);
    }

    @Test
    public void testBuscarPorCpfEncontrado() {
        // Arrange
        String cpf = "12345678901";
        Associado associado = new Associado();
        associado.setCpf(cpf);
        when(associadoRepository.findByCpf(cpf)).thenReturn(Optional.of(associado));

        // Act
        Associado result = associadoService.buscarPorCpf(cpf);

        // Assert
        assertNotNull(result);
        assertEquals(cpf, result.getCpf());
        verify(associadoRepository, times(1)).findByCpf(cpf);
    }

    @Test
    public void testBuscarPorCpfNaoEncontrado() {
        // Arrange
        String cpf = "12345678901";
        when(associadoRepository.findByCpf(cpf)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            associadoService.buscarPorCpf(cpf);
        });

        assertEquals("Associado não encontrado", exception.getMessage());
        verify(associadoRepository, times(1)).findByCpf(cpf);
    }

}