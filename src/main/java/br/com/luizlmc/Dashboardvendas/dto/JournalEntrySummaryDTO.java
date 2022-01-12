package br.com.luizlmc.Dashboardvendas.dto;

import br.com.luizlmc.Dashboardvendas.model.JournalEntryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class JournalEntrySummaryDTO {

    private long id;
    private String description;
    private LocalDate dueDate;
    private LocalDate paymentDate;
    private BigDecimal amount;
    private JournalEntryType entryType;
    private String category;
    private String person;
}
