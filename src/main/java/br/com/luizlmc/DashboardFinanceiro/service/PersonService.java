package br.com.luizlmc.DashboardFinanceiro.service;

import br.com.luizlmc.DashboardFinanceiro.dto.PersonDTO;
import br.com.luizlmc.DashboardFinanceiro.dto.mapper.PersonMapper;
import br.com.luizlmc.DashboardFinanceiro.event.ResourceCreatedEvent;
import br.com.luizlmc.DashboardFinanceiro.model.Person;
import br.com.luizlmc.DashboardFinanceiro.repository.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private ApplicationEventPublisher publisher;

    public List<PersonDTO> findAll(){
        return personRepository.findAll().stream().map(personMapper::toPersonDTO).collect(Collectors.toList());
    }

    public Optional<PersonDTO> findById(Long id) {
        return personRepository.findById(id).map(personMapper::toPersonDTO);
    }

    public PersonDTO createdPerson(PersonDTO personDTO, HttpServletResponse response) {
        Person personSave = personRepository.save(personMapper.toPerson(personDTO));

        publisher.publishEvent(new ResourceCreatedEvent(this, response, personSave.getId()));
        return personMapper.toPersonDTO(personSave);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    public PersonDTO updatePerson(Long id, PersonDTO personDTO) {
        Person personSave = personRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));

        BeanUtils.copyProperties(personDTO, personSave, "id");
        return personMapper.toPersonDTO(personRepository.save(personSave));
    }

    public void updateActive(Long id, Boolean active) {
        Person personSave = personRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        personSave.setActive(active);

        personRepository.save(personSave);
    }
}
