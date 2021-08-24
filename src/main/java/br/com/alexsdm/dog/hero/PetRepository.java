package br.com.alexsdm.dog.hero;

import br.com.alexsdm.dog.hero.domain.entity.Pet;
import java.util.List;

public interface PetRepository {

    List<Pet> buscarPets(List<String> identificadores);
}
