package br.com.luizlmc.DashboardFinanceiro.dto;

import br.com.luizlmc.DashboardFinanceiro.model.Address;
import br.com.luizlmc.DashboardFinanceiro.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    private Long id;
    private String name;
    @Embedded
    private Address address;
    private Boolean active;

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.address = person.getAddress();
        this.active = person.getActive();
    }
}
