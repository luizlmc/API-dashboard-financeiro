package br.com.luizlmc.Dashboardvendas.model;

import br.com.luizlmc.Dashboardvendas.dto.PersonDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 20)
    private String name;

    @Embedded
    private Address address;

    @NotNull
    private Boolean active;

    public Person(PersonDTO personDTO) {
        this.id = personDTO.getId();
        this.name = personDTO.getName();
        this.address = personDTO.getAddress();
        this.active = personDTO.getActive();
    }
}
