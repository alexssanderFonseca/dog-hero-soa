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
        def idPasseio = UUID.randomUUID().toString();
        def cancelamentoPasseio = new CancelamentoPasseio(passeioRepository);
        when:
        cancelamentoPasseio.executar(idPasseio);
        then:
        1 * passeioRepository.buscarPeloId(idPasseio) >> Optional.of(passeio)
        1 * passeioRepository.atualizarPasseio(passeio)
    }

    def "Deve falhar caso nao encontre o passeio"() {
        given:
        def idPasseio = UUID.randomUUID().toString();
        def cancelamentoPasseio = new CancelamentoPasseio(passeioRepository);
        when:
        passeioRepository.buscarPeloId(idPasseio) >> Optional.empty();
        cancelamentoPasseio.executar(idPasseio);
        then:
        def erro = thrown(BusinessException);
        erro.getMessage() == "O passeio informado não foi encontrado"
        0 * passeioRepository.atualizarPasseio(passeio)

    }

}
