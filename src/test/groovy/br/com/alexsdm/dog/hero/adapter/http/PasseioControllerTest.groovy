package br.com.alexsdm.dog.hero.adapter.http

import br.com.alexsdm.dog.hero.domain.usecase.CadastroPasseio
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.core.io.Resource
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@WebMvcTest
class PasseioControllerTest extends Specification {

    @Autowired
    private MockMvc mvc;

    @Value("classpath:data/cadastro_passeio_input.json")
    private Resource cadastroPasseioInputJson;

    @SpringBean
    private CadastroPasseio cadastroPasseio = Mock(CadastroPasseio);

    def "Deve retornar 200 ao cadastrar um passeio"() {
        expect:
        mvc.perform(post("/passeios")
                .contentType("application/json")
                .content(cadastroPasseioInputJson.getInputStream().readAllBytes()))
                .andExpect(status().isOk())

    }

}
