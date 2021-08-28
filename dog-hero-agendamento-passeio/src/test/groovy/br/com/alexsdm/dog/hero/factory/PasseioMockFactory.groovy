package br.com.alexsdm.dog.hero.factory

import br.com.alexsdm.dog.hero.domain.entity.Duracao
import br.com.alexsdm.dog.hero.domain.entity.Local
import br.com.alexsdm.dog.hero.domain.entity.Passeio
import br.com.alexsdm.dog.hero.domain.mapper.PasseioMapper
import br.com.alexsdm.dog.hero.dto.out.PasseioDTO

import java.time.LocalDateTime

class PasseioMockFactory {

    static Passeio cria(List<String> petsId, Duracao duracao) {
        return new Passeio(LocalDateTime.now(), duracao, new Local("", ""), petsId);
    }

    static Passeio cria() {
        return new Passeio(LocalDateTime.now(), Duracao.SESSENTA, new Local("", ""), List.of("pets1"));
    }

    static PasseioDTO criaDTO(){
        return PasseioMapper.INSTANCIA.paraPasseioDTO(cria());
    }
}
