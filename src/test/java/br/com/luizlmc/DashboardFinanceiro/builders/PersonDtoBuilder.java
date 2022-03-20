package br.com.luizlmc.DashboardFinanceiro.builders;

import br.com.luizlmc.DashboardFinanceiro.dto.PersonDTO;
import br.com.luizlmc.DashboardFinanceiro.model.Address;

public class PersonDtoBuilder {

    private PersonDTO personDTO;

    private PersonDtoBuilder() {}

    public static PersonDtoBuilder aPersonDTO(){
        PersonDtoBuilder builder = new PersonDtoBuilder();
        builder.personDTO = new PersonDTO();
        builder.personDTO.setName("Pessoa1");
        builder.personDTO.setAddress(new Address());
        builder.personDTO.setActive(true);
        return builder;
    }

    public PersonDtoBuilder withId(Long id){
        personDTO.setId(id);
        return this;
    }

    public PersonDTO now(){
        return personDTO;
    }
}
