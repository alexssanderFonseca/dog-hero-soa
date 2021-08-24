package br.com.alexsdm.dog.hero.domain.usecase;

import br.com.alexsdm.dog.hero.domain.entity.Passeio;

public interface PasseioRepository {

    void salvar(Passeio passeio);
}
