package br.com.alexsdm.dog.hero.adapter.out.database.repository;

import br.com.alexsdm.dog.hero.adapter.out.database.datamodel.PasseioItem;
import br.com.alexsdm.dog.hero.adapter.out.database.datamodel.mapper.PasseioItemMapper;
import br.com.alexsdm.dog.hero.domain.entity.Passeio;
import br.com.alexsdm.dog.hero.domain.repository.PasseioRepository;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PasseioRepositoryDynamoImpl implements PasseioRepository {

    private final DynamoDBMapper dynamoDBMapper;
    private final PasseioItemMapper passeioItemMapper;

    @Override
    public Passeio salvar(Passeio passeio) {
        var passeioItem = passeioItemMapper.dePasseio(passeio);
        dynamoDBMapper.save(passeioItem);
        return passeioItemMapper.paraPasseio(passeioItem);
    }

    @Override
    public Optional<Passeio> buscarPeloId(String idUsuario, String idPasseio) {
        var passeioItem = dynamoDBMapper.load(PasseioItem.class, idUsuario, idPasseio);
        return passeioItem == null ? Optional.empty() : Optional.of(passeioItemMapper.paraPasseio(passeioItem));
    }

    @Override
    public  List<Passeio> buscarTodosPasseiosDoUsuario(String idUsuario) {
        var filter = new HashMap<String, AttributeValue>();
        filter.put(":idUsuario", new AttributeValue().withS(idUsuario));
        var query = new DynamoDBQueryExpression<PasseioItem>()
                .withKeyConditionExpression("usuario_id = :idUsuario")
                .withExpressionAttributeValues(filter);
        List<PasseioItem> passeiosItem = dynamoDBMapper.query(PasseioItem.class, query);
        return passeioItemMapper.paraPasseio(passeiosItem);
    }

    @Override
    public void atualizarPasseio(Passeio passeio) {
        var passeioItem = passeioItemMapper.dePasseio(passeio);
        dynamoDBMapper.save(passeioItem);
    }

}
