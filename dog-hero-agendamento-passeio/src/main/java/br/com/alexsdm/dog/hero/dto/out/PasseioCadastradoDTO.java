package br.com.alexsdm.dog.hero.dto.out;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PasseioCadastradoDTO {

    private String id;

    private String usuarioId;

    private LocalDateTime dataAgendamento;

    private String latitude;

    private String longitude;

    private String duracao;

    private List<String> pets;
}
