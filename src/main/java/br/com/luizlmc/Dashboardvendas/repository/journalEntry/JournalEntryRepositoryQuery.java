package br.com.luizlmc.Dashboardvendas.repository.journalEntry;

import br.com.luizlmc.Dashboardvendas.model.JournalEntry;
import br.com.luizlmc.Dashboardvendas.repository.filter.JournalEntryFilter;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JournalEntryRepositoryQuery {

    public List<JournalEntry> filter(JournalEntryFilter journalEntryFilter);
}
