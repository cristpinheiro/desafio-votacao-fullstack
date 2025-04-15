package com.cooperavote.cooperavoteback.entity;

import com.cooperavote.cooperavoteback.enums.OpcaoVoto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

