package br.com.luizlmc.Dashboardvendas.service;

import br.com.luizlmc.Dashboardvendas.dto.JournalEntryDTO;
import br.com.luizlmc.Dashboardvendas.dto.mapper.JournalEntryMapper;
import br.com.luizlmc.Dashboardvendas.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private JournalEntryMapper journalEntryMapper;

    public List<JournalEntryDTO> search() {
        return journalEntryRepository.findAll()
                .stream()
                .map(journalEntryMapper::toJournalEntryDTO)
                .collect(Collectors.toList());
    }
}
