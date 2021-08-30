package br.com.alexsdm.dog.hero.domain.usecase;

import br.com.alexsdm.dog.hero.domain.exception.BusinessException;
import br.com.alexsdm.dog.hero.domain.repository.PasseioRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CancelarPasseio {

    private final PasseioRepository passeioRepository;

    public void executar(String idCriador, String idPasseio) {
        var passeio = passeioRepository.buscarPeloId(idCriador,idPasseio).
                orElseThrow(() -> new BusinessException("O passeio informado não foi encontrado"));
        passeio.cancelar(LocalDateTime.now());
        passeioRepository.atualizarPasseio(passeio);
    }
}
