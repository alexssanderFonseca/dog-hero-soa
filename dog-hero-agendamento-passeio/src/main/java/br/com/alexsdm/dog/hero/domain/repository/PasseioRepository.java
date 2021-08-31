package br.com.alexsdm.dog.hero.domain.repository;

import br.com.alexsdm.dog.hero.domain.entity.Passeio;
import java.util.List;
import java.util.Optional;

public interface PasseioRepository {

    Passeio salvar(Passeio passeio);

    Optional<Passeio> buscarPeloId(String idPasseio, String idUsuario);

    List<Passeio> buscarTodosPasseiosDoUsuario(String idUsuario);

    void atualizarPasseio(Passeio passeio);
}
