package br.com.alexsdm.dog.hero.adapter.in.http


import br.com.alexsdm.dog.hero.domain.usecase.CadastrarPasseio
import br.com.alexsdm.dog.hero.domain.usecase.CancelarPasseio
import br.com.alexsdm.dog.hero.domain.usecase.VisualizarPasseio
import br.com.alexsdm.dog.hero.dto.in.CadastroPasseioInputDTO
import br.com.alexsdm.dog.hero.dto.out.PasseioCadastradoDTO
import br.com.alexsdm.dog.hero.factory.PasseioMockFactory
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.core.io.Resource
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import static org.hamcrest.Matchers.containsString
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
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
    CadastrarPasseio cadastroPasseio = Mock(CadastrarPasseio);

    @SpringBean
    CancelarPasseio cancelamentoPasseio = Mock(CancelarPasseio);

    @SpringBean
    VisualizarPasseio encontrarPasseio = Mock(VisualizarPasseio);


    def "Deve retornar 201 ao cadastrar um passeio com header location"() {
        given:
        def passeioCadastrado = getPasseioCadastrado();
        def passeioJson = new String(cadastroPasseioInputJson.getInputStream().readAllBytes());
        def formatadorData = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        def dataAgendamento = LocalDateTime.now().plusDays(5).format(formatadorData);

        def passeioJsonComDataCadastroValida = passeioJson.replace("{{data_agendamento}}", "${dataAgendamento}");
        cadastroPasseio.executar(_ as CadastroPasseioInputDTO) >> passeioCadastrado
        expect:
        mvc.perform(post("/passeios")
                .contentType("application/json")
                .content(passeioJsonComDataCadastroValida))
                .andExpect(status().isCreated())
                .andExpect(header().exists("location"))
                .andExpect(header().string("location", containsString(passeioCadastrado.getId())))

    }

    def "Deve retornar 204 ao cancelar passeio"() {
        given:
        def idCriador = UUID.randomUUID().toString();
        def idPasseio = UUID.randomUUID().toString();
        expect:
        mvc.perform(patch("/passeios/${idCriador}/${idPasseio}/cancelar"))
                .andExpect(status().isNoContent())
    }

    def "Deve retonar 200 e o passeio encontrado no corpo da requisicao"() {
        given:
        def passeioDTO = PasseioMockFactory.criaDTO()
        def idCriador = UUID.randomUUID().toString();
        def idPasseio = UUID.randomUUID().toString();
        encontrarPasseio.executar(idCriador, idPasseio) >> passeioDTO;
        expect:
        mvc.perform(get("/passeios/${idCriador}/${idPasseio}"))
                .andExpect(status().isOk())
                .andReturn();

    }

    private PasseioCadastradoDTO getPasseioCadastrado() {
        return new PasseioCadastradoDTO(UUID.randomUUID().toString(), "12345", LocalDateTime.now(), "xxxxxxx", "xxxxxx", "30", List.of("pet1"));
    }


}
