package br.com.alexsdm.dog.hero.adapter.in.http

import br.com.alexsdm.dog.hero.domain.usecase.CadastroPasseio
import br.com.alexsdm.dog.hero.domain.usecase.CancelamentoPasseio
import br.com.alexsdm.dog.hero.domain.usecase.EncontrarPasseio
import br.com.alexsdm.dog.hero.dto.in.CadastroPasseioInputDTO
import br.com.alexsdm.dog.hero.dto.out.PasseioCadastradoDTO
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.core.io.Resource
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import java.time.LocalDateTime

import static org.hamcrest.Matchers.containsString
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc
@WebMvcTest
class PasseioControllerSpecification extends Specification {

    @Autowired
    MockMvc mvc;

    @Value("classpath:data/cadastro_passeio_input.json")
    Resource cadastroPasseioInputJson;

    @SpringBean
    CadastroPasseio cadastroPasseio = Mock(CadastroPasseio);

    @SpringBean
    CancelamentoPasseio cancelamentoPasseio = Mock(CancelamentoPasseio);

    @SpringBean
    EncontrarPasseio encontrarPasseio = Mock(EncontrarPasseio);


    def "Deve retornar 201 ao cadastrar um passeio com header location"() {
        given:
        def passeioCadastrado = getPasseioCadastrado();
        cadastroPasseio.executar(_ as CadastroPasseioInputDTO) >> passeioCadastrado
        expect:
        mvc.perform(post("/passeios")
                .contentType("application/json")
                .content(cadastroPasseioInputJson.getInputStream().readAllBytes()))
                .andExpect(status().isCreated())
                .andExpect(header().exists("location"))
                .andExpect(header().string("location", containsString(passeioCadastrado.getId())))

    }

    def "Deve retornar 200 ao cancelar passeio"() {
        expect:
        def id = UUID.randomUUID().toString();
        mvc.perform(patch("/passeios/${id}/cancelar"))
                .andExpect(status().isNoContent())
    }

    private PasseioCadastradoDTO getPasseioCadastrado() {
        return new PasseioCadastradoDTO(UUID.randomUUID().toString(), LocalDateTime.now(), "xxxxxxx", "xxxxxx", "30", List.of("pet1"));
    }

}
