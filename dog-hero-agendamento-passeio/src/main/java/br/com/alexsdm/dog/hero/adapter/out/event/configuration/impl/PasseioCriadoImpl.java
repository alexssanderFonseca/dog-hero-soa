package br.com.alexsdm.dog.hero.adapter.out.event.configuration.impl;

import br.com.alexsdm.dog.hero.dto.out.PasseioCadastradoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasseioCriadoImpl implements br.com.alexsdm.dog.hero.domain.event.PasseioCriadoEvent {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${topico.passeio.criado}")
    private String topicoPasseioCriado;

    @Autowired
    private final ObjectMapper objectMapper;

    @Override
    public void notificar(PasseioCadastradoDTO passeioCadastradoDTO) {
        try {
            var passeioCriadoJson = objectMapper.writeValueAsString(passeioCadastradoDTO);
            kafkaTemplate.send(topicoPasseioCriado, passeioCriadoJson);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
