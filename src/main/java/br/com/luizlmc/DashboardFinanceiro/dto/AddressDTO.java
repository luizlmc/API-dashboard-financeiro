package br.com.luizlmc.DashboardFinanceiro.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class AddressDTO {

    private String street;
    private String address_number;
    private String complement;
    private String neighbourhood;
    private String zipcode;
    private String city;
    private String state;
}
