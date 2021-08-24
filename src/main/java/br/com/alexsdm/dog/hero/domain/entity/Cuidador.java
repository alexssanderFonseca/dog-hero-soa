package br.com.alexsdm.dog.hero.domain.entity;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Cuidador {
    private String id;
    private String nome;
    private String email;
    private LocalDateTime dataNascimento;
}
