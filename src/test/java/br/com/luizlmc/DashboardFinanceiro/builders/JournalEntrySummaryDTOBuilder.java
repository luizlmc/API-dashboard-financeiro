package br.com.luizlmc.DashboardFinanceiro.builders;

import br.com.luizlmc.DashboardFinanceiro.dto.JournalEntrySummaryDTO;
import br.com.luizlmc.DashboardFinanceiro.model.JournalEntryType;

import java.math.BigDecimal;
import java.time.LocalDate;

import static br.com.luizlmc.DashboardFinanceiro.builders.CategoryBuilder.aCategory;
import static br.com.luizlmc.DashboardFinanceiro.builders.PersonBuilder.aPerson;

public class JournalEntrySummaryDTOBuilder {

    private JournalEntrySummaryDTO journalEntrySummaryDTO;

    public static JournalEntrySummaryDTOBuilder aJournalEntryDTO(){
        JournalEntrySummaryDTOBuilder builder = new JournalEntrySummaryDTOBuilder();
        builder.journalEntrySummaryDTO = new JournalEntrySummaryDTO();
        builder.journalEntrySummaryDTO.setDescription("LançamentoDTO1");
        builder.journalEntrySummaryDTO.setDueDate(LocalDate.of(2020, 1, 8));
        builder.journalEntrySummaryDTO.setPaymentDate(LocalDate.of(2020, 3, 8));
        builder.journalEntrySummaryDTO.setAmount(new BigDecimal("2000.00"));
        builder.journalEntrySummaryDTO.setEntryType(JournalEntryType.EXPENSES);
        builder.journalEntrySummaryDTO.setPerson(aPerson().now().toString());
        builder.journalEntrySummaryDTO.setCategory(aCategory().now().toString());
        return builder;
    }

    public JournalEntrySummaryDTO now(){
        return journalEntrySummaryDTO;
    }
}
