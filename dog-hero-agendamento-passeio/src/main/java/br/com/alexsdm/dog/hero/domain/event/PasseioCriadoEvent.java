package br.com.alexsdm.dog.hero.domain.event;

import br.com.alexsdm.dog.hero.dto.out.PasseioCadastradoDTO;

public interface PasseioCriadoEvent {
    void notificar(PasseioCadastradoDTO passeioCadastradoDTO);
}
