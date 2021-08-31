package br.com.alexsdm.dog.hero.domain.usecase

import br.com.alexsdm.dog.hero.domain.entity.Duracao
import br.com.alexsdm.dog.hero.domain.exception.BusinessException
import br.com.alexsdm.dog.hero.domain.repository.PasseioRepository
import spock.lang.Specification

import static br.com.alexsdm.dog.hero.factory.PasseioMockFactory.cria

class VisualizarPasseioSpecification extends Specification {

    PasseioRepository passeioRepository = Mock(PasseioRepository);

    def "Deve retornar dados de visualizacao do passeio"() {
        given:
        def idUsuario = UUID.randomUUID().toString();
        def idPasseio = UUID.randomUUID().toString();
        def visualizarPasseio = new VisualizarPasseio(passeioRepository);
        def pet = cria(List.of("pet1"), Duracao.SESSENTA);
        when:
        visualizarPasseio.executar(idPasseio, idUsuario);
        then:
        1 * passeioRepository.buscarPeloId(idPasseio, idUsuario) >> Optional.of(pet); ;

    }

    def "Deve lancar exception caso passeio nao exista"() {
        given:
        def idUsuario = UUID.randomUUID().toString();
        def idPasseio = UUID.randomUUID().toString();
        def visualizarPasseio = new VisualizarPasseio(passeioRepository);
        when:
        visualizarPasseio.executar(idPasseio, idUsuario);
        then:
        1 * passeioRepository.buscarPeloId(idPasseio, idUsuario) >> Optional.empty(); ;
        var erro = thrown(BusinessException);
        erro.getMessage() == "O passeio informado não foi encontrado";

    }

}
