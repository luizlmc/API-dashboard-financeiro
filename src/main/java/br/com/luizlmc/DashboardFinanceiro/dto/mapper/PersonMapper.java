package br.com.luizlmc.DashboardFinanceiro.dto.mapper;

import br.com.luizlmc.DashboardFinanceiro.dto.PersonDTO;
import br.com.luizlmc.DashboardFinanceiro.model.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person toPerson(PersonDTO personDTO);
    PersonDTO toPersonDTO(Person person);
}
