package br.com.alexsdm.dog.hero.dto.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class CadastroPasseioInputDTO {

    @NotNull
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    @Future
    private LocalDateTime dataAgendamento;

    private String criadorId;

    @NotBlank
    private String latidude;

    @NotBlank
    private String longitude;

    @NotBlank
    private String duracao;

    @NotNull
    private List<String> pets;


}

