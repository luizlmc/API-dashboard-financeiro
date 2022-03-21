package br.com.luizlmc.DashboardFinanceiro.builders;

import br.com.luizlmc.DashboardFinanceiro.model.JournalEntry;
import br.com.luizlmc.DashboardFinanceiro.model.JournalEntryType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class JournalEntryBuilder {

    private JournalEntry journalEntry;

    public static JournalEntryBuilder aJournalEntry(){
        JournalEntryBuilder builder = new JournalEntryBuilder();
        builder.journalEntry = new JournalEntry();
        builder.journalEntry.setDescription("Lançamento1");
        builder.journalEntry.setDueDate(LocalDate.of(2020, 1, 8));
        builder.journalEntry.setPaymentDate(LocalDate.of(2020, 3, 8));
        builder.journalEntry.setAmount(new BigDecimal("2000.00"));
        builder.journalEntry.setObservation("DESPESA");
        builder.journalEntry.setEntryType(JournalEntryType.EXPENSES);
        builder.journalEntry.setCategory(CategoryBuilder.aCategory().now());
        builder.journalEntry.setPerson(PersonBuilder.aPerson().now());
        return builder;
    }

    public JournalEntry now(){
        return journalEntry;
    }
}
