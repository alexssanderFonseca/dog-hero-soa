package br.com.alexsdm.dog.hero.domain.usecase;

import br.com.alexsdm.dog.hero.domain.exception.BusinessException;
import br.com.alexsdm.dog.hero.domain.repository.PasseioRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CancelamentoPasseio {

    private final PasseioRepository passeioRepository;

    public void executar(String idPasseio) {
        var passeio = passeioRepository.buscarPeloId(idPasseio).
                orElseThrow(() -> new BusinessException("O passeio informado não foi encontrado"));
        passeio.cancelar(LocalDateTime.now());
        passeioRepository.atualizarPasseio(passeio);
    }
}