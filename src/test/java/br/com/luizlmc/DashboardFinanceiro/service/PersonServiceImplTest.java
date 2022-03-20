package br.com.luizlmc.DashboardFinanceiro.service;

import br.com.luizlmc.DashboardFinanceiro.builders.PersonBuilder;
import br.com.luizlmc.DashboardFinanceiro.builders.PersonDtoBuilder;
import br.com.luizlmc.DashboardFinanceiro.dto.PersonDTO;
import br.com.luizlmc.DashboardFinanceiro.dto.mapper.PersonMapper;
import br.com.luizlmc.DashboardFinanceiro.model.Person;
import br.com.luizlmc.DashboardFinanceiro.repository.PersonRepository;
import br.com.luizlmc.DashboardFinanceiro.service.serviceImpl.PersonServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Garante o funcionamento da classe Pessoa service")
public class PersonServiceImplTest {

    private static final Long ID = 1L;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonMapper personMapper;

    @Mock
    private ApplicationEventPublisher publisher;

    private MockHttpServletResponse response;

    @InjectMocks
    private PersonServiceImpl personService;

    @BeforeEach
    public void setup(){
        response = new MockHttpServletResponse();
    }

    @Test
    @DisplayName("Deve listar todas as pessoas")
    public void mustListAllPersonsTest (){

        List<Person> persons = Arrays.asList(
                PersonBuilder.aPerson().now(),
                PersonBuilder.aPerson().now());

        when(personRepository.findAll()).thenReturn(persons);

        List<PersonDTO> personsDTO = personService.findAll();

        assertAll(
                () -> assertEquals(2, persons.size()),
                () -> assertEquals(persons.stream().map(personMapper::toPersonDTO).collect(Collectors.toList()), personsDTO)
                );
        verify(personRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve buscar pessoa pelo id")
    public void mustGetPersonById(){

        Person person = PersonBuilder.aPerson().withId(ID).now();
        PersonDTO personDTO = PersonDtoBuilder.aPersonDTO().withId(ID).now();

        when(personRepository.findById(ID)).thenReturn(ofNullable(person));
        when(personMapper.toPersonDTO(person)).thenReturn(personDTO);

        Optional<PersonDTO> personDTO2 = this.personService.findById(ID);

        assertTrue(personDTO2.isPresent());
        assertAll(
                ()->assertEquals(personDTO.getId(),personDTO2.get().getId()),
                ()->assertEquals(personDTO.getName(),personDTO2.get().getName()),
                ()-> assertEquals(personDTO.getAddress(),personDTO2.get().getAddress()),
                ()-> assertEquals(personDTO.getActive(),personDTO2.get().getActive())
        );
    }

    @Test
    @DisplayName("Deve criar nova pessoa")
    public void mustCreatedNewPerson(){

        Person person = PersonBuilder.aPerson().withId(ID).now();
        PersonDTO personDTO = PersonDtoBuilder.aPersonDTO().withId(ID).now();

        //PersonDTO personDTO1 = PersonDtoBuilder.aPersonDTO().withId(2L).now();

        when(personRepository.save(any())).thenReturn(person);
        when(personMapper.toPerson(any())).thenReturn(person);
        when(personMapper.toPersonDTO(any())).thenReturn(personDTO);
        doNothing().when(publisher).publishEvent(any());

        PersonDTO personDTOResponse = this.personService.createdPerson(personDTO, response);

        verify(personRepository, times(1)).save(person);
        assertEquals(personDTO, personDTOResponse);
    }

    @Test
    @DisplayName("Deve atualizar atributos de uma pessoa com sucesso")
    public void mustUpdatePersonAttributes(){

        Person person = PersonBuilder.aPerson().withId(ID).now();
        PersonDTO personDTO = PersonDtoBuilder.aPersonDTO().withId(ID).now();

        //PersonDTO personDTO1 = PersonDtoBuilder.aPersonDTO().withId(2L).now();

        when(personRepository.findById(ID)).thenReturn(ofNullable(person));
        when(personMapper.toPersonDTO(any())).thenReturn(personDTO);
        when(personRepository.save(any())).thenReturn(person);

        PersonDTO newPersonDTO = this.personService.updatePerson(ID, personDTO);

        assertEquals(personDTO, newPersonDTO);
    }

    @Test
    @DisplayName("Deve alterar o status ativo da pessoa")
    public void mustUpdateStatusActive(){

        Boolean active = false;
        Person person = PersonBuilder.aPerson().withId(ID).now();
        PersonDTO personDTO = PersonDtoBuilder.aPersonDTO().withId(ID).now();

        when(personRepository.findById(ID)).thenReturn(ofNullable(person));

        personService.updateActive(ID, active);

        verify(personRepository, times(1)).save(person);
    }

    @Test
    @DisplayName("Deve deetar uma pessoa")
    public void mustDeletePerson(){
        doNothing().when(personRepository).deleteById(anyLong());

        personService.deletePerson(anyLong());

        verify(personRepository, times(1)).deleteById(anyLong());
        verify(personRepository).deleteById(anyLong());
    }
}
