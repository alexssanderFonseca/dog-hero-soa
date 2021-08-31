package br.com.alexsdm.dog.hero.adapter.out.database.datamodel.mapper;

import br.com.alexsdm.dog.hero.adapter.out.database.datamodel.PasseioItem;
import br.com.alexsdm.dog.hero.domain.entity.Local;
import br.com.alexsdm.dog.hero.domain.entity.Passeio;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
@NoArgsConstructor
public abstract class PasseioItemMapper {

    @Autowired
    private ObjectMapper objectMapper;

    @Mapping(source = "id", target = "idPasseio")
    public abstract PasseioItem dePasseio(Passeio passeio);

    @Mapping(source = "idPasseio", target = "id")
    public abstract Passeio paraPasseio(PasseioItem passeioItem);

    @Mapping(source = "idPasseio", target = "id")
    public abstract List<Passeio> paraPasseio(List<PasseioItem> passeioItem);

    public String paraLocalJson(Local local) throws JsonProcessingException {
        return objectMapper.writeValueAsString(local);
    }

    public Local deLocalJson(String local) throws JsonProcessingException {
        var mapLocal = objectMapper.readValue(local, Map.class);
        return new Local(mapLocal.get("latitude").toString(), mapLocal.get("longitude").toString());
    }

    public String paraPetsIdJson(List<String> pets) throws JsonProcessingException {
        return objectMapper.writeValueAsString(pets);
    }

    public List<String> dePetsIdJson(String pets) {
        return objectMapper.convertValue(pets, new TypeReference<>() {
        });
    }

}

