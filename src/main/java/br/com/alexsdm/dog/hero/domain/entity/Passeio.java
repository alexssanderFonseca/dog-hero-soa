package br.com.alexsdm.dog.hero.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

@Getter
public class Passeio {

    private final Status status;
    private final LocalDateTime dataAgendamento;
    private final Duracao duracao;
    private final Local local;
    private final List<String> pets;
    private final Horario horario;
    private BigDecimal preco;
    private Cuidador cuidador;

    public Passeio(LocalDateTime dataAgendamento, Duracao duracao, Local local,
                   List<String> pets) {
        this.status = Status.AGENDADA;
        this.dataAgendamento = dataAgendamento;
        this.duracao = duracao;
        this.local = local;
        this.pets = pets;
        this.horario = new Horario(dataAgendamento, duracao);
        this.preco = calcularPrecoDoPasseio();
    }

    private BigDecimal calcularPrecoDoPasseio() {
        return this.duracao.getValorBase().add(getValorAdicionalPorDogExtra());
    }

    private BigDecimal getValorAdicionalPorDogExtra() {
        var qtdPetsExtras = this.pets.size() - 1;
        return this.duracao.getValorAdicionalPorDogExtra().multiply(BigDecimal.valueOf(qtdPetsExtras));
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public void setCuidador(Cuidador cuidador) {
        this.cuidador = cuidador;
    }
}
