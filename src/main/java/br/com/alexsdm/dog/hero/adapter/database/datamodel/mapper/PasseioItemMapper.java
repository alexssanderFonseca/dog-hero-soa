package br.com.alexsdm.dog.hero.adapter.database.datamodel.mapper;

import br.com.alexsdm.dog.hero.adapter.database.datamodel.PasseioItem;
import br.com.alexsdm.dog.hero.domain.entity.Local;
import br.com.alexsdm.dog.hero.domain.entity.Passeio;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PasseioItemMapper {

    PasseioItem dePasseio(Passeio passeio);

    default String paraLocalJson(Local local) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(local);
    }

    default String paraPetsIdJson(List<String> pets) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(pets);
    }
}

