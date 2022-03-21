package br.com.luizlmc.DashboardFinanceiro.repository.journalEntry;

import br.com.luizlmc.DashboardFinanceiro.dto.JournalEntrySummaryDTO;
import br.com.luizlmc.DashboardFinanceiro.model.JournalEntry;
import br.com.luizlmc.DashboardFinanceiro.repository.filter.JournalEntryFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface JournalEntryRepositoryQuery {

    Page<JournalEntry> filter(JournalEntryFilter journalEntryFilter, Pageable pageable);
    Page<JournalEntrySummaryDTO> summarize(JournalEntryFilter journalEntryFilter, Pageable pageable);
}
