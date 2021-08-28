package br.com.alexsdm.dog.hero.domain.usecase;

import br.com.alexsdm.dog.hero.domain.exception.BusinessException;
import br.com.alexsdm.dog.hero.domain.mapper.PasseioMapper;
import br.com.alexsdm.dog.hero.domain.repository.PasseioRepository;
import br.com.alexsdm.dog.hero.dto.out.PasseioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisualizarPasseio {

    private final PasseioRepository passeioRepository;

    public PasseioDTO executar(String id) {
        var passeio = passeioRepository.buscarPeloId(id)
                .orElseThrow(() -> new BusinessException("O passeio informado não foi encontrado"));
        return PasseioMapper.INSTANCIA.paraPasseioDTO(passeio);
    }

}
