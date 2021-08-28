package br.com.alexsdm.dog.hero.adapter.out.database.datamodel.mapper;

import br.com.alexsdm.dog.hero.adapter.out.database.datamodel.PasseioItem;
import br.com.alexsdm.dog.hero.domain.entity.Local;
import br.com.alexsdm.dog.hero.domain.entity.Passeio;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PasseioItemMapper {

    PasseioItem dePasseio(Passeio passeio);

    @Mapping(source = "idPasseio", target = "dono")
    Passeio paraPasseio(PasseioItem passeioItem);

    default String paraLocalJson(Local local) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(local);
    }

    default Local deLocalJson(String local) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        var mapLocal = objectMapper.convertValue(local, HashMap.class);
        return new Local(mapLocal.get("latitude").toString(), mapLocal.get("longitude").toString());
    }

    default String paraPetsIdJson(List<String> pets) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(pets);
    }

    default List<String> dePetsIdJson(String pets) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(pets, new TypeReference<>() {
        });
    }

}

