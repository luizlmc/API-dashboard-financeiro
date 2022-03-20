package br.com.luizlmc.DashboardFinanceiro.serviceImpl;

import br.com.luizlmc.DashboardFinanceiro.dto.PersonDTO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public interface PersonService {

    List<PersonDTO> findAll();
    Optional<PersonDTO> findById(Long id);
    PersonDTO createdPerson(PersonDTO personDTO, HttpServletResponse response);
    void deletePerson(Long id);
    PersonDTO updatePerson(Long id, PersonDTO personDTO);
    void updateActive(Long id, Boolean active);
}
