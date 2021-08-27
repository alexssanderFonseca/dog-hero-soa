package br.com.alexsdm.dog.hero.adapter.in.http;

import br.com.alexsdm.dog.hero.domain.usecase.CadastroPasseio;
import br.com.alexsdm.dog.hero.domain.usecase.CancelamentoPasseio;
import br.com.alexsdm.dog.hero.domain.usecase.EncontrarPasseio;
import br.com.alexsdm.dog.hero.dto.in.CadastroPasseioInputDTO;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/passeios")
@RequiredArgsConstructor
public class PasseioController {

    private final CadastroPasseio cadastroPasseio;
    private final CancelamentoPasseio cancelamentoPasseio;
    private final EncontrarPasseio encontrarPasseio;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CadastroPasseioInputDTO cadastroPasseioInputDTO, UriComponentsBuilder uriBuilder) {
        var cadastroPasseioOutputDTO = this.cadastroPasseio.executar(cadastroPasseioInputDTO);
        var uri = uriBuilder.path("/{id}").buildAndExpand(cadastroPasseioOutputDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelar(@PathVariable String id) {
        this.cancelamentoPasseio.executar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable String id) {
        return ResponseEntity.noContent().build();
    }
}
