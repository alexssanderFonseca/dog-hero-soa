package br.com.alexsdm.dog.hero.adapter.out.database.repository;

import br.com.alexsdm.dog.hero.adapter.out.database.datamodel.PasseioItem;
import br.com.alexsdm.dog.hero.adapter.out.database.datamodel.mapper.PasseioItemMapper;
import br.com.alexsdm.dog.hero.domain.entity.Passeio;
import br.com.alexsdm.dog.hero.domain.repository.PasseioRepository;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
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
        try {
            dynamoDBMapper.save(passeioItem);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return passeioItemMapper.paraPasseio(passeioItem);
    }

    @Override
    public Optional<Passeio> buscarPeloId(String id) {
        var passeioItem = dynamoDBMapper.load(PasseioItem.class, id);
        return passeioItem == null ? Optional.empty() : Optional.of(passeioItemMapper.paraPasseio(passeioItem));
    }

    @Override
    public void atualizarPasseio(Passeio passeio) {
        var passeioItem = passeioItemMapper.dePasseio(passeio);
        try {
            dynamoDBMapper.save(passeioItem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
