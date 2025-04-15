package com.cooperavote.cooperavoteback.entity;

import com.cooperavote.cooperavoteback.enums.OpcaoVoto;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Associado associado;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Pauta pauta;

    @Enumerated(EnumType.STRING)
    private OpcaoVoto opcao;

    private LocalDateTime dataHoraVoto;
}

