package br.com.luizlmc.Dashboardvendas.service;

import br.com.luizlmc.Dashboardvendas.dto.JournalEntryDTO;
import br.com.luizlmc.Dashboardvendas.dto.JournalEntrySummaryDTO;
import br.com.luizlmc.Dashboardvendas.dto.mapper.JournalEntryMapper;
import br.com.luizlmc.Dashboardvendas.event.ResourceCreatedEvent;
import br.com.luizlmc.Dashboardvendas.model.JournalEntry;
import br.com.luizlmc.Dashboardvendas.model.Person;
import br.com.luizlmc.Dashboardvendas.repository.JournalEntryRepository;
import br.com.luizlmc.Dashboardvendas.repository.PersonRepository;
import br.com.luizlmc.Dashboardvendas.repository.filter.JournalEntryFilter;
import br.com.luizlmc.Dashboardvendas.service.exception.NonexistentOrInactivePersonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private JournalEntryMapper journalEntryMapper;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    public Page<JournalEntryDTO> search(JournalEntryFilter journalEntryFilter, Pageable pageable) {
        return journalEntryRepository.filter(journalEntryFilter, pageable)
                .map(journalEntryMapper::toJournalEntryDTO);
    }

    public Page<JournalEntrySummaryDTO> summarize(JournalEntryFilter journalEntryFilter, Pageable pageable) {
        return journalEntryRepository.summarize(journalEntryFilter, pageable);
    }

    public Optional<JournalEntryDTO> findById(Long id) {
        return journalEntryRepository.findById(id)
                .map(journalEntryMapper::toJournalEntryDTO);
    }

    public JournalEntryDTO create(JournalEntryDTO journalEntryDTO, HttpServletResponse response) {

        Person personSave = personRepository.findById(journalEntryDTO.getPerson().getId())
                .orElseThrow(() -> new EmptyResultDataAccessException(1));

        if (personSave == null || personSave.isInactive()){
            throw new NonexistentOrInactivePersonException();
        }

        JournalEntry journalEntrySave = journalEntryRepository.save(journalEntryMapper.toJournalEntry(journalEntryDTO));

        publisher.publishEvent(new ResourceCreatedEvent(this, response, journalEntrySave.getId()));
        return journalEntryMapper.toJournalEntryDTO(journalEntrySave);
    }

    public void delete(Long id) {
        journalEntryRepository.deleteById(id);
    }
}
