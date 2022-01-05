package br.com.luizlmc.Dashboardvendas.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authorization")
@Data
public class Authorization {

    @Id
    private Long id;
    private String description;
}