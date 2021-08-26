package br.com.alexsdm.dog.hero.domain.mapper;

import br.com.alexsdm.dog.hero.domain.entity.Passeio;
import br.com.alexsdm.dog.hero.dto.out.PasseioCriadoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PasseioCriadoMapper {

    PasseioCriadoMapper INSTANCIA = Mappers.getMapper(PasseioCriadoMapper.class);

    @Mapping(source = "passeio.local.latitude", target = "latitude")
    @Mapping(source = "passeio.local.longitude", target = "longitude")
    PasseioCriadoDTO dePasseio(Passeio passeio);
}
