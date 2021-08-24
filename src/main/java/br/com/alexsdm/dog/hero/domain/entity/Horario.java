package br.com.alexsdm.dog.hero.domain.entity;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class Horario {
    private LocalDateTime inicioEsperado;
    private LocalDateTime terminoEsperado;
    private LocalDateTime inicioOcorrido;
    private LocalDateTime terminoOcorrido;

    public Horario(LocalDateTime inicioEsperado, Duracao duracao) {
        this.inicioEsperado = inicioEsperado;
        this.terminoEsperado = this.inicioEsperado.plusMinutes(duracao.getTempo());
    }


}