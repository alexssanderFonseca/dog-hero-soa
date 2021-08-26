package br.com.alexsdm.dog.hero.domain.usecase

import br.com.alexsdm.dog.hero.domain.entity.Duracao
import br.com.alexsdm.dog.hero.domain.event.PasseioCriadoEvent
import br.com.alexsdm.dog.hero.domain.repository.PasseioRepository
import br.com.alexsdm.dog.hero.dto.in.CadastroPasseioInputDTO
import spock.lang.Specification

import java.time.LocalDateTime

class CadastroPasseioSpecification extends Specification {

    PasseioRepository passeioRepository = Mock(PasseioRepository);
    PasseioCriadoEvent passeioCriadoEvent = Mock(PasseioCriadoEvent);


    def "Deve cadastrar um passeio"() {
        given:
        def pets = new ArrayList<String>();
        pets.add("pet1");
        pets.add("pet2");
        pets.add("pet3");
        def cadastroPasseioInputDTO = CadastroPasseioInputDTO.builder()
                .duracao("30")
                .dataAgendamento(LocalDateTime.now())
                .latidude("123")
                .longitude("1234")
                .pets(pets)
                .build();
        def cadastrarPasseio = new CadastroPasseio(passeioRepository, passeioCriadoEvent);
        when:
        cadastrarPasseio.executar(cadastroPasseioInputDTO);
        then:
        1 * passeioRepository.salvar(_) >> {
            def passeio = it.get(0);
            assert passeio.local.longitude == "1234";
            assert passeio.local.latitude == "123";
            assert passeio.pets.size() == 3;
            assert passeio.duracao == Duracao.TRINTA;
        };
        1 * passeioCriadoEvent.notificar(_);

    }
}
