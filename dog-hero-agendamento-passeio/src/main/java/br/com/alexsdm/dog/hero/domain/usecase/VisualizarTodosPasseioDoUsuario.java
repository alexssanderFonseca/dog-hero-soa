package br.com.alexsdm.dog.hero.domain.usecase;

import br.com.alexsdm.dog.hero.domain.mapper.PasseioMapper;
import br.com.alexsdm.dog.hero.domain.repository.PasseioRepository;
import br.com.alexsdm.dog.hero.dto.out.PasseioDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisualizarTodosPasseioDoUsuario {

    private final PasseioRepository passeioRepository;

    public List<PasseioDTO> executar(String idUsuario) {
        var passeios = passeioRepository.buscarTodosPasseiosDoUsuario(idUsuario);
        return PasseioMapper.INSTANCIA.paraPasseioDTO(passeios);
    }
}
