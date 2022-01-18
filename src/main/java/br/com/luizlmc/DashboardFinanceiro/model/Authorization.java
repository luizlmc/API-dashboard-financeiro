package br.com.luizlmc.DashboardFinanceiro.model;

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