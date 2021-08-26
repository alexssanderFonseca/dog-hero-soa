package br.com.alexsdm.dog.hero.domain.entity;

import br.com.alexsdm.dog.hero.domain.exception.BusinessException;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

@Getter
public class Passeio {

    private Status status;
    private final LocalDateTime dataAgendamento;
    private final Duracao duracao;
    private final Local local;
    private final List<String> pets;
    private final Horario horario;
    private BigDecimal preco;
    private Cuidador cuidador;

    private static final int PRAZO_LIMITE_CANCELAMENTO = 5;

    public Passeio(LocalDateTime dataAgendamento, Duracao duracao, Local local,
                   List<String> pets) {
        this.status = Status.AGENDADO;
        this.dataAgendamento = dataAgendamento;
        this.duracao = duracao;
        this.local = local;
        this.pets = pets;
        this.horario = new Horario(dataAgendamento, duracao);
        this.preco = calcularPrecoDoPasseio();
    }

    public void cancelar() {
        if (this.status != Status.INICIADO && this.status != Status.FINALIZADO) {
            throw new BusinessException("O passeio não pode ser cancelado após ser iniciado ou finalizado");
        }
        if (!isPrazoParaCancelamentoValido()) {
            throw new BusinessException("O passeio não pode ser cancelado após o prazo de antecedência");
        }
        this.status = Status.CANCELADO;
    }

    private boolean isPrazoParaCancelamentoValido() {
        var agora = LocalDateTime.now();
        if (agora.isAfter(this.dataAgendamento)) {
            return false;
        }
        var diferencaEntreAgoraDataAtual = Duration.between(agora, this.dataAgendamento).toHours();
        return diferencaEntreAgoraDataAtual <= PRAZO_LIMITE_CANCELAMENTO;
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
