package br.com.alexsdm.dog.hero.domain.repository;

import br.com.alexsdm.dog.hero.domain.entity.Passeio;
import java.util.Optional;

public interface PasseioRepository {

    Passeio salvar(Passeio passeio);

    Optional<Passeio> buscarPeloId(String idCriador, String idPasseio);

    void atualizarPasseio(Passeio passeio);
}
