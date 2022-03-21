package br.com.luizlmc.DashboardFinanceiro.builders;

import br.com.luizlmc.DashboardFinanceiro.dto.JournalEntryDTO;
import br.com.luizlmc.DashboardFinanceiro.model.JournalEntryType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class JournalEntryDTOBuilder {

    private JournalEntryDTO journalEntryDTO;

    public static JournalEntryDTOBuilder aJournalEntryDTO(){
        JournalEntryDTOBuilder builder = new JournalEntryDTOBuilder();
        builder.journalEntryDTO = new JournalEntryDTO();
        builder.journalEntryDTO.setDescription("LançamentoDTO1");
        builder.journalEntryDTO.setDueDate(LocalDate.of(2020, 1, 8));
        builder.journalEntryDTO.setPaymentDate(LocalDate.of(2020, 3, 8));
        builder.journalEntryDTO.setAmount(new BigDecimal("2000.00"));
        builder.journalEntryDTO.setObservation("DESPESA");
        builder.journalEntryDTO.setEntryType(JournalEntryType.EXPENSES);
        builder.journalEntryDTO.setCategory(CategoryBuilder.aCategory().now());
        builder.journalEntryDTO.setPerson(PersonBuilder.aPerson().now());
        return builder;
    }

    public JournalEntryDTO now(){
        return journalEntryDTO;
    }
}
