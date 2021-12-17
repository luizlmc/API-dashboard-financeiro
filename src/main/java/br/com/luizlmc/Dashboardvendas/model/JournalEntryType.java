package br.com.luizlmc.Dashboardvendas.model;

import lombok.Getter;

@Getter
public enum JournalEntryType {

    RECEITA("INCOME"),
    DESPESA("EXPENSES");

    private final String description;

    private JournalEntryType(String description) {
        this.description = description;
    }
}