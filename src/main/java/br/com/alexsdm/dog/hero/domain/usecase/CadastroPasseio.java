package br.com.alexsdm.dog.hero.domain.usecase;

import br.com.alexsdm.dog.hero.domain.entity.Passeio;
import br.com.alexsdm.dog.hero.domain.event.PasseioCriadoEvent;
import br.com.alexsdm.dog.hero.domain.factory.PasseioFactory;
import br.com.alexsdm.dog.hero.domain.mapper.PasseioCriadoMapper;
import br.com.alexsdm.dog.hero.domain.repository.PasseioRepository;
import br.com.alexsdm.dog.hero.dto.in.CadastroPasseioInputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CadastroPasseio {

    private final PasseioRepository passeioRepository;
    private final PasseioCriadoEvent passeioCriadoEvent;

    public void executar(CadastroPasseioInputDTO cadastroPasseioInputDTO) {
        var passeio = PasseioFactory.build(cadastroPasseioInputDTO);
        salvar(passeio);
        notificar(passeio);
    }

    private void salvar(Passeio passeio) {
        passeioRepository.salvar(passeio);
    }

    private void notificar(Passeio passeio) {
        var passeioCriadoDTO = PasseioCriadoMapper.INSTANCIA.dePasseio(passeio);
        passeioCriadoEvent.notificar(passeioCriadoDTO);
    }


}
