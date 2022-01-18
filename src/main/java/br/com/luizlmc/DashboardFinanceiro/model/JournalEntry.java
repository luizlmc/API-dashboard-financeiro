package br.com.luizlmc.DashboardFinanceiro.model;

import br.com.luizlmc.DashboardFinanceiro.dto.JournalEntryDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "journal_entry")
public class JournalEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    private String description;

    @NotNull
    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @NotNull
    private BigDecimal amount;

    private String observation;

    @NotNull
    @Column(name = "entry_type")
    @Enumerated(EnumType.STRING)
    private JournalEntryType entryType;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;

    @JsonIgnoreProperties("contacts")
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_person")
    private Person person;

    public JournalEntry(JournalEntryDTO journalEntryDTO) {
        id = journalEntryDTO.getId();
        description = journalEntryDTO.getDescription();
        dueDate = journalEntryDTO.getDueDate();
        paymentDate = journalEntryDTO.getPaymentDate();
        amount = journalEntryDTO.getAmount();
        observation = journalEntryDTO.getObservation();
        entryType = journalEntryDTO.getEntryType();
        category = journalEntryDTO.getCategory();
        person = journalEntryDTO.getPerson();
    }
}
