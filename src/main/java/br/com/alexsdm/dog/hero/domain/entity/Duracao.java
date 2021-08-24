package br.com.alexsdm.dog.hero.domain.entity;

import java.math.BigDecimal;
import java.util.Arrays;
import lombok.Getter;

@Getter
public enum Duracao {
    TRINTA(BigDecimal.valueOf(25), BigDecimal.valueOf(15), 30),
    SESSENTA(BigDecimal.valueOf(35), (BigDecimal.valueOf(20)), 60);

    private final long tempo;
    private final BigDecimal valorAdicionalPorDogExtra;
    private final BigDecimal valorBase;

    Duracao(BigDecimal valorBase, BigDecimal valorAdicionalPorDogExtra, long tempo) {
        this.valorBase = valorBase;
        this.valorAdicionalPorDogExtra = valorAdicionalPorDogExtra;
        this.tempo = tempo;
    }

    public static Duracao getByTempo(String tempo) {
        return Arrays.stream(Duracao.values())
                .filter(duracao -> String.valueOf(tempo).equals(tempo))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Duracao invalida"));
    }
}
