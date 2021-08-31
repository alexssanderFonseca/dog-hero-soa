package br.com.alexsdm.dog.hero.domain.entity;

import br.com.alexsdm.dog.hero.domain.exception.BusinessException;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

@Getter
public class Passeio {

    private String id;
    private String usuarioId;
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
                   List<String> pets, String usuarioId) {
        this.status = Status.AGENDADO;
        if (!isDataCadastroValida(dataAgendamento)) {
            throw new BusinessException("O passeio não pode ser cadastrado com uma data no passado");
        }
        this.dataAgendamento = dataAgendamento;
        this.duracao = duracao;
        this.local = local;
        this.pets = pets;
        this.usuarioId = usuarioId;
        this.horario = new Horario(dataAgendamento, duracao);
        this.preco = calcularPrecoDoPasseio();
    }

    private boolean isDataCadastroValida(LocalDateTime dataAgendamento) {
        return LocalDateTime.now().isBefore(dataAgendamento);
    }

    private BigDecimal calcularPrecoDoPasseio() {
        return this.duracao.getValorBase().add(getValorAdicionalPorDogExtra());
    }

    private BigDecimal getValorAdicionalPorDogExtra() {
        var qtdPetsExtras = this.pets.size() - 1;
        return this.duracao.getValorAdicionalPorDogExtra().multiply(BigDecimal.valueOf(qtdPetsExtras));
    }

    public void cancelar(LocalDateTime horarioCancelamento) {
        if (this.status == Status.INICIADO || this.status == Status.FINALIZADO) {
            throw new BusinessException("O passeio não pode ser cancelado após ser iniciado ou finalizado");
        }
        if (isTempoLimiteParaCancelamentoAtingido(horarioCancelamento)) {
            throw new BusinessException("O passeio não pode ser cancelado após o prazo de antecedência");
        }
        this.status = Status.CANCELADO;
    }

    private boolean isTempoLimiteParaCancelamentoAtingido(LocalDateTime horarioCancelamento) {
        if (horarioCancelamento.isAfter(this.dataAgendamento)) {
            return true;
        }
        var diferencaEntreDataAgendadaECancelamento = Duration.between(horarioCancelamento, this.dataAgendamento).toHours();
        return diferencaEntreDataAgendadaECancelamento < PRAZO_LIMITE_CANCELAMENTO;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public void setCuidador(Cuidador cuidador) {
        this.cuidador = cuidador;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setId(String id) {
        this.id = id;
    }
}
