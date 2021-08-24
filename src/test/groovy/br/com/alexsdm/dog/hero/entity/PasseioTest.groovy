package br.com.alexsdm.dog.hero.entity

import br.com.alexsdm.dog.hero.domain.entity.Duracao
import br.com.alexsdm.dog.hero.domain.entity.Local
import br.com.alexsdm.dog.hero.domain.entity.Passeio
import spock.lang.Specification

import java.time.LocalDateTime

class PasseioTest extends Specification {

    def "Deve calcular valor de uma passeio de 30 minutos para apenas 1 pet"() {
        given:
        def pets = new ArrayList()
        pets.add("pet1")
        def duracaopasseio = Duracao.TRINTA
        when:
        def dogpasseio = mockPasseio(pets, duracaopasseio);
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
        def dogPasseio = mockPasseio(pets, duracaoPasseio);
        then:
        dogPasseio.getPreco() == 40.00
    }

    def "Deve calcular valor de uma passeio de 60 minutos apenas um pet"() {
        given:
        def pets = new ArrayList()
        pets.add("pet1")
        def duracaoPasseio = Duracao.SESSENTA
        when:
        def dogpasseio = mockPasseio(pets, duracaoPasseio);
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
        def dogpasseio = mockPasseio(pets, duracaoPasseio);
        then:
        dogpasseio.getPreco() == 75.00
    }

    private Passeio mockPasseio(List<String> petsId, Duracao duracao) {
        return new Passeio(LocalDateTime.now(), duracao, new Local("", ""), petsId);
    }
}