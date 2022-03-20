package br.com.luizlmc.DashboardFinanceiro.serviceImpl;

import br.com.luizlmc.DashboardFinanceiro.dto.JournalEntryDTO;
import br.com.luizlmc.DashboardFinanceiro.dto.JournalEntrySummaryDTO;
import br.com.luizlmc.DashboardFinanceiro.repository.filter.JournalEntryFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public interface JournalEntryService {

    Page<JournalEntryDTO> search(JournalEntryFilter journalEntryFilter, Pageable pageable);
    Page<JournalEntrySummaryDTO> summarize(JournalEntryFilter journalEntryFilter, Pageable pageable);
    Optional<JournalEntryDTO> findById(Long id);
    JournalEntryDTO create(JournalEntryDTO journalEntryDTO, HttpServletResponse response);
    void delete(Long id);
}
