package br.com.luizlmc.Dashboardvendas.dto.mapper;

import br.com.luizlmc.Dashboardvendas.dto.PersonDTO;
import br.com.luizlmc.Dashboardvendas.model.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person toPerson(PersonDTO personDTO);
    PersonDTO toPersonDTO(Person person);
}
