package br.com.alexsdm.dog.hero.adapter.http;

import br.com.alexsdm.dog.hero.domain.usecase.CadastroPasseio;
import br.com.alexsdm.dog.hero.dto.CadastroPasseioInputDTO;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passeios")
@RequiredArgsConstructor
public class PasseioController {

    private final CadastroPasseio cadastroPasseio;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CadastroPasseioInputDTO cadastroPasseioInputDTO) {
        cadastroPasseio.executar(cadastroPasseioInputDTO);
        return ResponseEntity.ok().build();
    }
}
