package br.com.alexsdm.dog.hero.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Pet {
    private final String id;
    private final String nome;
    private final Dono dono;
}
