package br.com.luizlmc.DashboardFinanceiro.repository;

import br.com.luizlmc.DashboardFinanceiro.model.JournalEntry;
import br.com.luizlmc.DashboardFinanceiro.repository.journalEntry.JournalEntryRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalEntryRepository extends JpaRepository<JournalEntry, Long>, JournalEntryRepositoryQuery {
}
