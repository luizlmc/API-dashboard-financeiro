package br.com.luizlmc.DashboardFinanceiro.controller;

import br.com.luizlmc.DashboardFinanceiro.dto.JournalEntryDTO;
import br.com.luizlmc.DashboardFinanceiro.dto.JournalEntrySummaryDTO;
import br.com.luizlmc.DashboardFinanceiro.repository.filter.JournalEntryFilter;
import br.com.luizlmc.DashboardFinanceiro.serviceImpl.serviceImpl.JournalEntryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/journalentries")
public class JournalEntryController {

    @Autowired
    public JournalEntryServiceImpl journalEntryServiceImpl;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_SEARCH_JOURNALENTRY') and hasAuthority('SCOPE_read')")
    public ResponseEntity<Page<JournalEntryDTO>> search(JournalEntryFilter journalEntryFilter, Pageable pageable){
        return ResponseEntity.ok().body(journalEntryServiceImpl.search(journalEntryFilter, pageable));
    }

    @GetMapping(params = "summary")
    @PreAuthorize("hasAuthority('ROLE_SEARCH_JOURNALENTRY') and hasAuthority('SCOPE_read')")
    public ResponseEntity<Page<JournalEntrySummaryDTO>> summarize(JournalEntryFilter journalEntryFilter, Pageable pageable){
        return ResponseEntity.ok().body(journalEntryServiceImpl.summarize(journalEntryFilter, pageable));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_SEARCH_JOURNALENTRY') and hasAuthority('SCOPE_read')")
    public ResponseEntity<JournalEntryDTO> searchById(@PathVariable Long id) {
        return journalEntryServiceImpl.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_REGISTER_JOURNALENTRY') and hasAuthority('SCOPE_write')")
    public ResponseEntity<JournalEntryDTO> create(@Valid @RequestBody JournalEntryDTO journalEntryDTO, HttpServletResponse response ){
        return ResponseEntity.status(HttpStatus.CREATED).body(journalEntryServiceImpl.create(journalEntryDTO, response));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_REMOVE_JOURNALENTRY') and #oauth2.hasScope('write')")
    public void delete(@PathVariable Long id) {
        journalEntryServiceImpl.delete(id);
    }
}
