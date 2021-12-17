package br.com.luizlmc.Dashboardvendas.dto;

import br.com.luizlmc.Dashboardvendas.model.Category;
import br.com.luizlmc.Dashboardvendas.model.JournalEntry;
import br.com.luizlmc.Dashboardvendas.model.JournalEntryType;
import br.com.luizlmc.Dashboardvendas.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JournalEntryDTO {

    private long id;
    private String description;
    private LocalDate dueDate;
    private LocalDate paymentDate;
    private BigDecimal amount;
    private String observation;
    private JournalEntryType entryType;
    private Category category;
    private Person person;

    public JournalEntryDTO(JournalEntry journalEntry) {
        id = journalEntry.getId();
        description = journalEntry.getDescription();
        dueDate = journalEntry.getDueDate();
        paymentDate = journalEntry.getPaymentDate();
        amount = journalEntry.getAmount();
        observation = journalEntry.getObservation();
        entryType = journalEntry.getEntryType();
        category = journalEntry.getCategory();
        person = journalEntry.getPerson();
    }
}
