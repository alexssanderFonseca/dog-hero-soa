package br.com.alexsdm.dog.hero.domain.mapper;

import br.com.alexsdm.dog.hero.domain.entity.Passeio;
import br.com.alexsdm.dog.hero.dto.out.PasseioCadastradoDTO;
import br.com.alexsdm.dog.hero.dto.out.PasseioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PasseioMapper {

    PasseioMapper INSTANCIA = Mappers.getMapper(PasseioMapper.class);

    @Mapping(source = "passeio.local.latitude", target = "latitude")
    @Mapping(source = "passeio.local.longitude", target = "longitude")
    PasseioCadastradoDTO paraPasseioCadastrado(Passeio passeio);

    PasseioDTO paraPasseioDTO(Passeio passeio);
}
