package br.com.luizlmc.DashboardFinanceiro.dto.mapper;

import br.com.luizlmc.DashboardFinanceiro.dto.JournalEntryDTO;
import br.com.luizlmc.DashboardFinanceiro.model.JournalEntry;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JournalEntryMapper {

    JournalEntry toJournalEntry(JournalEntryDTO journalEntryDTO);
    JournalEntryDTO toJournalEntryDTO(JournalEntry journalEntry);
}
