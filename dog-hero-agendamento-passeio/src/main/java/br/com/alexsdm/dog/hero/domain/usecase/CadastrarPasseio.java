package br.com.alexsdm.dog.hero.domain.usecase;

import br.com.alexsdm.dog.hero.domain.event.PasseioCriadoEvent;
import br.com.alexsdm.dog.hero.domain.factory.PasseioFactory;
import br.com.alexsdm.dog.hero.domain.mapper.PasseioMapper;
import br.com.alexsdm.dog.hero.domain.repository.PasseioRepository;
import br.com.alexsdm.dog.hero.dto.in.CadastroPasseioInputDTO;
import br.com.alexsdm.dog.hero.dto.out.PasseioCadastradoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastrarPasseio {

    private final PasseioRepository passeioRepository;
    private final PasseioCriadoEvent passeioCriadoEvent;

    public PasseioCadastradoDTO executar(CadastroPasseioInputDTO cadastroPasseioInputDTO) {
        var passeio = PasseioFactory.build(cadastroPasseioInputDTO);
        passeio = passeioRepository.salvar(passeio);
        var passeioCadastrado = PasseioMapper.INSTANCIA.paraPasseioCadastrado(passeio);
        passeioCriadoEvent.notificar(passeioCadastrado);
        return passeioCadastrado;
    }


}
