package br.com.alexsdm.dog.hero.dto.out;

import br.com.alexsdm.dog.hero.domain.entity.Cuidador;
import br.com.alexsdm.dog.hero.domain.entity.Duracao;
import br.com.alexsdm.dog.hero.domain.entity.Horario;
import br.com.alexsdm.dog.hero.domain.entity.Local;
import br.com.alexsdm.dog.hero.domain.entity.Status;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class PasseioDTO {

    private String id;
    private Status status;
    private final LocalDateTime dataAgendamento;
    private final Duracao duracao;
    private final Local local;
    private final List<String> pets;
    private final Horario horario;
    private BigDecimal preco;
    private Cuidador cuidador;
}
