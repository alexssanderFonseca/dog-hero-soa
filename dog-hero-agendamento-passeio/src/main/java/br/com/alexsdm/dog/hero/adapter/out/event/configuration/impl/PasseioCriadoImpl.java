package br.com.alexsdm.dog.hero.adapter.out.event.configuration.impl;

import br.com.alexsdm.dog.hero.domain.event.PasseioCriadoEvent;
import br.com.alexsdm.dog.hero.dto.out.PasseioCriadoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasseioCriadoImpl implements PasseioCriadoEvent {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${topico.passeio.criado}")
    private String topicoPasseioCriado;

    @Autowired
    private final ObjectMapper objectMapper;

    @Override
    public void notificar(PasseioCriadoDTO passeioCriadoDTO) {
        try {
            var passeioCriadoJson = objectMapper.writeValueAsString(passeioCriadoDTO);
            kafkaTemplate.send(topicoPasseioCriado, passeioCriadoJson);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
