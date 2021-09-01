package br.com.alexsdm.dog.hero.entity

import br.com.alexsdm.dog.hero.domain.entity.Duracao
import br.com.alexsdm.dog.hero.domain.entity.Local
import br.com.alexsdm.dog.hero.domain.entity.Passeio
import br.com.alexsdm.dog.hero.domain.entity.Status
import br.com.alexsdm.dog.hero.domain.exception.BusinessException

import java.time.LocalDate

import static br.com.alexsdm.dog.hero.factory.PasseioMockFactory.cria
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
        def dogpasseio = cria(pets, duracaopasseio);
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
        def dogPasseio = cria(pets, duracaoPasseio);
        then:
        dogPasseio.getPreco() == 40.00
    }

    def "Deve calcular valor de uma passeio de 60 minutos apenas um pet"() {
        given:
        def pets = new ArrayList()
        pets.add("pet1")
        def duracaoPasseio = Duracao.SESSENTA
        when:
        def dogpasseio = cria(pets, duracaoPasseio);
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
        def dogpasseio = cria(pets, duracaoPasseio);
        then:
        dogpasseio.getPreco() == 75.00
    }

    def "Nao deve cancelar passeio caso tenha passado do prazo limite"() {
        given:
        def passeio = new Passeio(LocalDateTime.of(2021, Month.OCTOBER, 26, 17, 0), Duracao.TRINTA, new Local("", ""), List.of("pet1"), "12345");
        when:
        passeio.cancelar(LocalDateTime.of(2021, Month.OCTOBER, 26, 14, 0));
        then:
        def erro = thrown(BusinessException);
        erro.getMessage() == "O passeio não pode ser cancelado após o prazo de antecedência";

    }

    def "Deve cancelar passeio caso esteja dentro do prazo limite"() {
        given:
        def passeio = new Passeio(LocalDateTime.of(2021, Month.OCTOBER, getDiaAgendamentoValido(), 17, 0), Duracao.TRINTA, new Local("", ""), List.of("pet1"), "12345");
        when:
        passeio.cancelar(LocalDateTime.of(2021, Month.OCTOBER, LocalDateTime.now().getDayOfMonth(), 12, 0));
        then:
        passeio.status == Status.CANCELADO
    }

    def "Nao deve cancelar passeio caso ja tenha passado a hora do agendamento"() {
        given:
        def passeio = new Passeio(LocalDateTime.of(2021, Month.OCTOBER, 26, 17, 0), Duracao.TRINTA, new Local("", ""), List.of("pet1"), "12345");
        when:
        passeio.cancelar(LocalDateTime.of(2021, Month.OCTOBER, 26, 17, 0, 1));
        then:
        def erro = thrown(BusinessException);
        erro.getMessage() == "O passeio não pode ser cancelado após o prazo de antecedência";
    }

    def "Nao deve criar passeio caso data de agendamento esteja no passado"() {
        given:
        def passeio;
        when:
        passeio = new Passeio(LocalDateTime.now().minusDays(1), Duracao.TRINTA, new Local("", ""), List.of("pet1"), "12345");
        then:
        def erro = thrown(BusinessException);
        erro.getMessage() == "O passeio não pode ser cadastrado com uma data no passado";
    }

    private int getDiaAgendamentoValido() {
        return LocalDate.now().plusDays(1).getDayOfMonth();
    }


}