package br.com.alexsdm.dog.hero.entity

import br.com.alexsdm.dog.hero.domain.entity.Duracao
import br.com.alexsdm.dog.hero.domain.entity.Local
import br.com.alexsdm.dog.hero.domain.entity.Passeio
import br.com.alexsdm.dog.hero.domain.entity.Status
import br.com.alexsdm.dog.hero.domain.exception.BusinessException
import spock.lang.Specification

import java.time.LocalDateTime
import java.time.Month

class PasseioSpecification extends Specification {

    def "Deve calcular valor de uma passeio de 30 minutos para apenas 1 pet"() {
        given:
        def pets = new ArrayList()
        pets.add("pet1")
        def duracaopasseio = Duracao.TRINTA
        when:
        def dogpasseio = criaPasseio(pets, duracaopasseio);
        then:
        dogpasseio.getPreco() == 25.00
    }

    def "Deve calcular valor de uma passeio de 30 minutos para mais de um pet"() {
        given:
        def pets = new ArrayList()
        pets.add("pet1")
        pets.add("pet2")
        def duracaoPasseio = Duracao.TRINTA
        when:
        def dogPasseio = criaPasseio(pets, duracaoPasseio);
        then:
        dogPasseio.getPreco() == 40.00
    }

    def "Deve calcular valor de uma passeio de 60 minutos apenas um pet"() {
        given:
        def pets = new ArrayList()
        pets.add("pet1")
        def duracaoPasseio = Duracao.SESSENTA
        when:
        def dogpasseio = criaPasseio(pets, duracaoPasseio);
        then:
        dogpasseio.getPreco() == 35.00
    }

    def "Deve calcular valor de uma passeio de 60 minutos mais de um pet"() {
        given:
        def pets = new ArrayList()
        pets.add("pet1")
        pets.add("pet2")
        pets.add("pet3")
        def duracaoPasseio = Duracao.SESSENTA
        when:
        def dogpasseio = criaPasseio(pets, duracaoPasseio);
        then:
        dogpasseio.getPreco() == 75.00
    }

    def "Nao deve cancelar passeio caso tenha passado do prazo limite"() {
        given:
        def passeio = new Passeio(LocalDateTime.of(2021, Month.OCTOBER, 26, 17, 0), Duracao.TRINTA, new Local("", ""), List.of("pet1"));
        when:
        passeio.cancelar(LocalDateTime.of(2021, Month.OCTOBER, 26, 14, 0));
        then:
        def erro = thrown(BusinessException);
        erro.getMessage() == "O passeio não pode ser cancelado após o prazo de antecedência";

    }

    def "Deve cancelar passeio caso esteja dentro do prazo limite"() {
        given:
        def passeio = new Passeio(LocalDateTime.of(2021, Month.OCTOBER, 26, 17, 0), Duracao.TRINTA, new Local("", ""), List.of("pet1"));
        when:
        passeio.cancelar(LocalDateTime.of(2021, Month.OCTOBER, 26, 12, 0));
        then:
        passeio.status == Status.CANCELADO
    }

    def "Nao deve cancelar passeio caso ja tenha passado a hora da data agendamento"() {
        given:
        def passeio = new Passeio(LocalDateTime.of(2021, Month.OCTOBER, 26, 17, 0), Duracao.TRINTA, new Local("", ""), List.of("pet1"));
        when:
        passeio.cancelar(LocalDateTime.of(2021, Month.OCTOBER, 26, 17, 0, 1));
        then:
        def erro = thrown(BusinessException);
        erro.getMessage() == "O passeio não pode ser cancelado após o prazo de antecedência";
    }

    private Passeio criaPasseio(List<String> petsId, Duracao duracao) {
        return new Passeio(LocalDateTime.now(), duracao, new Local("", ""), petsId);
    }

}