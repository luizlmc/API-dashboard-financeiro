package br.com.luizlmc.DashboardFinanceiro.builders;

import br.com.luizlmc.DashboardFinanceiro.model.Address;
import br.com.luizlmc.DashboardFinanceiro.model.Person;

public class PersonBuilder {

    private Person person;

    public static PersonBuilder aPerson(){
        PersonBuilder builder = new PersonBuilder();
        builder.person = new Person();
        builder.person.setName("PessoaDTO1");
        builder.person.setAddress(new Address());
        builder.person.setActive(true);
        return builder;
    }

    public PersonBuilder withId(Long id){
        person.setId(id);
        return this;
    }

    public PersonBuilder setActive(boolean active){
        person.setActive(active);
        return this;
    }

    public Person now(){
        return person;
    }
}
