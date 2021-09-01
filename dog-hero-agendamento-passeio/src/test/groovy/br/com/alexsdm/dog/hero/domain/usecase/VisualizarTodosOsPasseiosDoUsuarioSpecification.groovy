package br.com.alexsdm.dog.hero.domain.usecase

import br.com.alexsdm.dog.hero.domain.repository.PasseioRepository
import br.com.alexsdm.dog.hero.dto.out.PasseioDTO
import br.com.alexsdm.dog.hero.factory.PasseioMockFactory
import spock.lang.Specification

class VisualizarTodosOsPasseiosDoUsuarioSpecification extends Specification {

    PasseioRepository passeioRepository = Mock(PasseioRepository.class);

    def "Deve retornar dos os passeios do usuario"() {
        given:
        def idUsuario = UUID.randomUUID().toString();
        def visualizarTodos = new VisualizarTodosPasseioDoUsuario(passeioRepository);
        when:
        def passeiosDoUsuario = visualizarTodos.executar(idUsuario);
        then:
        1 * passeioRepository.buscarTodosPasseiosDoUsuario(idUsuario) >> Arrays.asList(PasseioMockFactory.cria(), PasseioMockFactory.cria());
        passeiosDoUsuario.size() == 2;
        passeiosDoUsuario.get(0) instanceof PasseioDTO;
        passeiosDoUsuario.get(1) instanceof PasseioDTO;
    }

    def "Deve retornar lista de passeio vazia caso nao encontre passeios de um usuario"() {
        given:
        def idUsuario = UUID.randomUUID().toString();
        def visualizarTodos = new VisualizarTodosPasseioDoUsuario(passeioRepository);
        when:
        def passeiosDoUsuario = visualizarTodos.executar(idUsuario);
        then:
        1 * passeioRepository.buscarTodosPasseiosDoUsuario(idUsuario) >> Arrays.asList();
        passeiosDoUsuario.size() == 0;
    }
}
