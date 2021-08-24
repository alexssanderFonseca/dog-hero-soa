package br.com.alexsdm.dog.hero.domain.usecase;

import br.com.alexsdm.dog.hero.domain.factory.PasseioFactory;
import br.com.alexsdm.dog.hero.dto.CadastroPasseioInputDTO;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CadastroPasseio {

    private final PasseioRepository passeioRepository;

    public void executar(CadastroPasseioInputDTO cadastroPasseioInputDTO) {
        var passeio = PasseioFactory.build(cadastroPasseioInputDTO);
        passeio.setPreco(BigDecimal.ONE);
        passeioRepository.salvar(passeio);
    }
}
