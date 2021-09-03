package br.com.alexsdm.dog.hero.adapter.in.http;

import br.com.alexsdm.dog.hero.domain.usecase.VisualizarTodosPasseioDoUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final VisualizarTodosPasseioDoUsuario visualizarTodosPasseioDoUsuario;

    @GetMapping("/{idUsuario}/passeios")
    public ResponseEntity<?> buscarTodosPasseiosDoUsuario(@PathVariable String idUsuario) {
        var passeioDTO = visualizarTodosPasseioDoUsuario.executar(idUsuario);
        return ResponseEntity.ok(passeioDTO);
    }
}
