package br.com.luizlmc.Dashboardvendas.repository.journalEntry;

import br.com.luizlmc.Dashboardvendas.model.JournalEntry;
import br.com.luizlmc.Dashboardvendas.repository.filter.JournalEntryFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface JournalEntryRepositoryQuery {

    public Page<JournalEntry> filter(JournalEntryFilter journalEntryFilter, Pageable pageable);
}
