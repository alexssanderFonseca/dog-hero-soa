package br.com.alexsdm.dog.hero.adapter.database.repository;

import br.com.alexsdm.dog.hero.adapter.database.datamodel.mapper.PasseioItemMapper;
import br.com.alexsdm.dog.hero.domain.entity.Passeio;
import br.com.alexsdm.dog.hero.domain.usecase.PasseioRepository;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PasseioRepositoryDynamoImpl implements PasseioRepository {

    private final DynamoDBMapper dynamoDBMapper;
    private final ObjectMapper objectMapper;
    private final PasseioItemMapper passeioItemMapper;

    @Override
    public void salvar(Passeio passeio) {
        var passeioItem = passeioItemMapper.dePasseio(passeio);
        try {
            var idDono = generateUUID();
            var idPasseio = generateUUID();
            passeioItem.setIdDono(idDono);
            passeioItem.setIdPasseio(idPasseio);
            dynamoDBMapper.save(passeioItem);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
