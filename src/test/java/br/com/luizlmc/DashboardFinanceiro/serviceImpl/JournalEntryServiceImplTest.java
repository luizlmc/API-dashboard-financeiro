package br.com.luizlmc.DashboardFinanceiro.serviceImpl;

import br.com.luizlmc.DashboardFinanceiro.builders.JournalEntrySummaryDTOBuilder;
import br.com.luizlmc.DashboardFinanceiro.builders.PersonBuilder;
import br.com.luizlmc.DashboardFinanceiro.dto.JournalEntryDTO;
import br.com.luizlmc.DashboardFinanceiro.dto.JournalEntrySummaryDTO;
import br.com.luizlmc.DashboardFinanceiro.dto.mapper.JournalEntryMapper;
import br.com.luizlmc.DashboardFinanceiro.model.JournalEntry;
import br.com.luizlmc.DashboardFinanceiro.model.Person;
import br.com.luizlmc.DashboardFinanceiro.repository.JournalEntryRepository;
import br.com.luizlmc.DashboardFinanceiro.repository.PersonRepository;
import br.com.luizlmc.DashboardFinanceiro.repository.filter.JournalEntryFilter;
import br.com.luizlmc.DashboardFinanceiro.serviceImpl.exception.NonexistentOrInactivePersonException;
import br.com.luizlmc.DashboardFinanceiro.serviceImpl.serviceImpl.JournalEntryServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static br.com.luizlmc.DashboardFinanceiro.builders.JournalEntryBuilder.aJournalEntry;
import static br.com.luizlmc.DashboardFinanceiro.builders.JournalEntryDTOBuilder.aJournalEntryDTO;
import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Garante o funcionamento da classe lançamento service")
public class JournalEntryServiceImplTest {

    @Mock
    private JournalEntryRepository journalEntryRepository;

    @Mock
    private JournalEntryMapper journalEntryMapper;

    @Mock
    private MockHttpServletResponse response;

    @Mock
    private ApplicationEventPublisher publisher;

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private JournalEntryServiceImpl service;

    @Test
    @DisplayName("Deve procurar todos os lançamentos com filtro")
    void mustSearchAllJournalEntry() {

        Pageable pageable = PageRequest.of(0, 1);
        JournalEntryFilter journalEntryFilter = new JournalEntryFilter();

        List<JournalEntry> journalEntries = Arrays.asList(
                aJournalEntry().now(),
                aJournalEntry().now()
        );

        Page<JournalEntry> journalEntryPage = new PageImpl<>(
                Arrays.asList(
                        aJournalEntry().now(),
                        aJournalEntry().now()
                )
        );

        when(journalEntryRepository.filter(journalEntryFilter, pageable)).thenReturn(journalEntryPage);

        Page<JournalEntryDTO> journalEntriesDTO = service.search(journalEntryFilter, pageable);

        assertEquals(journalEntryPage.map(journalEntryMapper::toJournalEntryDTO), journalEntriesDTO);
    }

    @Test
    @DisplayName("Deve resumir uma busca de lançamentos")
    void mustSummarizeSearchJournalEntry() {
        Pageable pageable = PageRequest.of(0, 1);
        JournalEntryFilter journalEntryFilter = new JournalEntryFilter();

        Page<JournalEntrySummaryDTO> journalEntrySummaryDTOS = new PageImpl<JournalEntrySummaryDTO>(
                Arrays.asList(
                        JournalEntrySummaryDTOBuilder.aJournalEntryDTO().now(),
                        JournalEntrySummaryDTOBuilder.aJournalEntryDTO().now()
                ));

        when(journalEntryRepository.summarize(journalEntryFilter, pageable)).thenReturn(journalEntrySummaryDTOS);

        Page<JournalEntrySummaryDTO> journalEntrySummaryDTOPage = service.summarize(journalEntryFilter, pageable);

        assertEquals(journalEntrySummaryDTOS, journalEntrySummaryDTOPage);
    }

    @Test
    @DisplayName("Deve encontrar lançamento por ID")
    void mustFindById() {
        JournalEntry journalEntry = aJournalEntry().now();
        JournalEntryDTO journalEntryDTO = aJournalEntryDTO().now();

        when(journalEntryRepository.findById(any())).thenReturn(ofNullable(journalEntry));
        when(journalEntryMapper.toJournalEntryDTO(any())).thenReturn(journalEntryDTO);

        Optional<JournalEntryDTO> journalEntryDTO2 = service.findById(journalEntryDTO.getId());

        assertTrue(journalEntryDTO2.isPresent());
        assertEquals(journalEntryDTO, journalEntryDTO2.get());
    }

    @Test
    @DisplayName("Deve criar lançamento")
    void mustCreateJournalEntry() {
        JournalEntry journalEntry = aJournalEntry().now();
        JournalEntryDTO journalEntryDTO = aJournalEntryDTO().now();
        Person person = PersonBuilder.aPerson().now();

        when(journalEntryRepository.save(any())).thenReturn(journalEntry);
        when(personRepository.findById(any())).thenReturn(Optional.ofNullable(person));
        when(journalEntryMapper.toJournalEntryDTO(any())).thenReturn(journalEntryDTO);
        doNothing().when(publisher).publishEvent(any());

        JournalEntryDTO journalEntryDTO2 = service.create(journalEntryDTO, response);

        verify(journalEntryRepository, times(1)).save(any());
        assertEquals(journalEntryDTO, journalEntryDTO2);
    }

    @Test
    @DisplayName("Não deve criar lançamento se pessoa for inativa")
    public void shouldNotCreateJournalEntryofPersonToInactive() {

        JournalEntryDTO journalEntryDTO = aJournalEntryDTO().now();
        Person person = PersonBuilder.aPerson().setActive(false).now();

        when(personRepository.findById(any())).thenReturn(Optional.ofNullable(person));

        Exception exception = assertThrows(NonexistentOrInactivePersonException.class, () -> service.create(journalEntryDTO, response));

        assertEquals("Pessoa inexistente ou inativa", exception.getMessage());
    }

    @Test
    @DisplayName("Deve deletar lançamento")
    void delete() {
        doNothing().when(journalEntryRepository).deleteById(anyLong());

        journalEntryRepository.deleteById(anyLong());

        verify(journalEntryRepository, times(1)).deleteById(anyLong());
    }
}