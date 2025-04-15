package com.cooperavote.cooperavoteback.service;

import com.cooperavote.cooperavoteback.dto.AssociadoDTO;
import com.cooperavote.cooperavoteback.entity.Associado;
import com.cooperavote.cooperavoteback.mapper.AssociadoMapper;
import com.cooperavote.cooperavoteback.repository.AssociadoRepository;
import org.springframework.stereotype.Service;

@Service
public class AssociadoService {

    private final AssociadoRepository repository;

    public AssociadoService(AssociadoRepository repository) {
        this.repository = repository;
    }

    public Associado salvar(AssociadoDTO dto) {
        Associado associado = AssociadoMapper.toEntity(dto);
        return repository.save(associado);
    }

    public boolean existePorCpf(String cpf) {
        return repository.findByCpf(cpf).isPresent();
    }

    public Associado buscarPorCpf(String cpf) {
        return repository.findByCpf(cpf).orElseThrow(() ->
                new RuntimeException("Associado não encontrado"));
    }
}