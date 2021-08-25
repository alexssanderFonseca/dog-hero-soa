package br.com.alexsdm.dog.hero.domain.event;

import br.com.alexsdm.dog.hero.dto.out.PasseioCriadoDTO;

public interface PasseioCriadoEvent {
    void notificar(PasseioCriadoDTO passeioCriadoDTO);
}
