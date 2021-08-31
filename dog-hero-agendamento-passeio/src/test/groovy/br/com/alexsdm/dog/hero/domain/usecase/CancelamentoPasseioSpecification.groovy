package br.com.alexsdm.dog.hero.domain.usecase


import br.com.alexsdm.dog.hero.domain.entity.Passeio
import br.com.alexsdm.dog.hero.domain.exception.BusinessException
import br.com.alexsdm.dog.hero.domain.repository.PasseioRepository
import spock.lang.Specification

class CancelamentoPasseioSpecification extends Specification {

    def passeioRepository = Mock(PasseioRepository);
    def passeio = Mock(Passeio);

    def "Deve cancelar passeio"() {
        given:
        def idUsuario = UUID.randomUUID().toString();
        def idPasseio = UUID.randomUUID().toString();
        def cancelamentoPasseio = new CancelarPasseio(passeioRepository);
        when:
        cancelamentoPasseio.executar(idPasseio, idUsuario);
        then:
        1 * passeioRepository.buscarPeloId(idUsuario, idPasseio) >> Optional.of(passeio)
        1 * passeioRepository.atualizarPasseio(passeio)
    }

    def "Deve falhar caso nao encontre o passeio"() {
        given:
        def idUsuario = UUID.randomUUID().toString();
        def idPasseio = UUID.randomUUID().toString();
        def cancelamentoPasseio = new CancelarPasseio(passeioRepository);
        when:
        passeioRepository.buscarPeloId(idPasseio, idUsuario) >> Optional.empty();
        cancelamentoPasseio.executar(idUsuario, idPasseio);
        then:
        def erro = thrown(BusinessException);
        erro.getMessage() == "O passeio informado não foi encontrado"
        0 * passeioRepository.atualizarPasseio(passeio)

    }

}
