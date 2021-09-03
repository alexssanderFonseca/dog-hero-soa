package br.com.alexsdm.dog.hero.adapter.in.http

import br.com.alexsdm.dog.hero.domain.usecase.VisualizarTodosPasseioDoUsuario
import br.com.alexsdm.dog.hero.factory.PasseioMockFactory
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc
@WebMvcTest(UsuarioController.class)
class UsuarioControllerSpecification extends Specification {

    @Autowired
    MockMvc mvc;

    @SpringBean
    VisualizarTodosPasseioDoUsuario visualizarTodosPasseioDoUsuario = Mock(VisualizarTodosPasseioDoUsuario);

    def "Deve retornar 200 ao visualizar todos os passeios de um usuario"() {
        def idUsuario = UUID.randomUUID().toString();
        visualizarTodosPasseioDoUsuario.executar(idUsuario) >> Arrays.asList(PasseioMockFactory.criaDTO(), PasseioMockFactory.criaDTO());
        expect:
        mvc.perform(get("/usuarios/${idUsuario}/passeios"))
                .andExpect(status().isOk())
                .andReturn();
    }
}
