package br.com.alexsdm.dog.hero.domain.factory;

import br.com.alexsdm.dog.hero.domain.entity.Duracao;
import br.com.alexsdm.dog.hero.domain.entity.Local;
import br.com.alexsdm.dog.hero.domain.entity.Passeio;
import br.com.alexsdm.dog.hero.dto.in.CadastroPasseioInputDTO;

public class PasseioFactory {

    private PasseioFactory() {
    }

    public static Passeio build(CadastroPasseioInputDTO cadastroPasseioInputDTO) {
        var duracao = Duracao.getByTempo(cadastroPasseioInputDTO.getDuracao());
        return new Passeio(cadastroPasseioInputDTO.getDataAgendamento(), duracao, new Local(cadastroPasseioInputDTO.getLatidude(),
                cadastroPasseioInputDTO.getLongitude()), cadastroPasseioInputDTO.getPets());
    }
}
