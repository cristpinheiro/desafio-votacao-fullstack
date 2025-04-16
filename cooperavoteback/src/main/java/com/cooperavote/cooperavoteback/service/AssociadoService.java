package com.cooperavote.cooperavoteback.service;

import com.cooperavote.cooperavoteback.dto.AssociadoDTO;
import com.cooperavote.cooperavoteback.entity.Associado;
import com.cooperavote.cooperavoteback.mapper.AssociadoMapper;
import com.cooperavote.cooperavoteback.repository.AssociadoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssociadoService {

    private final AssociadoRepository repository;

    public AssociadoService(AssociadoRepository repository) {
        this.repository = repository;
    }

    public AssociadoDTO salvar(AssociadoDTO dto) {
        Associado associado = AssociadoMapper.toEntity(dto);
        var associadoSalvo = findByCpf(dto.getCpf());
        if(associadoSalvo.isEmpty()) {
            Associado save = repository.save(associado);
            return AssociadoMapper.toDTO(save);
        }
        return AssociadoMapper.toDTO(associadoSalvo.get());
    }

    public boolean existePorCpf(String cpf) {
        return findByCpf(cpf).isPresent();
    }

    private Optional<Associado> findByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    public Associado buscarPorCpf(String cpf) {
        return repository.findByCpf(cpf).orElseThrow(() ->
                new RuntimeException("Associado não encontrado"));
    }
}