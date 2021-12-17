package br.com.luizlmc.Dashboardvendas.dto.mapper;

import br.com.luizlmc.Dashboardvendas.dto.JournalEntryDTO;
import br.com.luizlmc.Dashboardvendas.model.JournalEntry;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JournalEntryMapper {

    JournalEntry toJournalEntry(JournalEntryDTO journalEntryDTO);
    JournalEntryDTO toJournalEntryDTO(JournalEntry journalEntry);
}
