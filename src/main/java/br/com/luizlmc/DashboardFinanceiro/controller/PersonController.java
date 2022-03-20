package br.com.luizlmc.DashboardFinanceiro.controller;

import br.com.luizlmc.DashboardFinanceiro.dto.PersonDTO;
import br.com.luizlmc.DashboardFinanceiro.serviceImpl.serviceImpl.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonServiceImpl personService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_SEARCH_PERSON') and hasAuthority('SCOPE_read')")
    public ResponseEntity<List<PersonDTO>> listAll() {
        return ResponseEntity.ok(personService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_SEARCH_PERSON') and hasAuthority('SCOPE_read')")
    public ResponseEntity<PersonDTO> getById (@PathVariable Long id) {
        return personService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_REGISTER_PERSON') and hasAuthority('SCOPE_write')")
    public ResponseEntity<PersonDTO> create(@Valid @RequestBody PersonDTO personDTO, HttpServletResponse response) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.createdPerson(personDTO, response));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_REGISTER_PERSON') and hasAuthority('SCOPE_write')")
    public ResponseEntity<PersonDTO> update(@PathVariable Long id, @Valid @RequestBody PersonDTO personDTO){
        return ResponseEntity.ok(personService.updatePerson(id, personDTO));
    }

    @PutMapping("/{id}/active")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_REGISTER_PERSON') and hasAuthority('SCOPE_write')")
    public void updateStatusActive(@PathVariable Long id, @RequestBody Boolean active) {
        personService.updateActive(id, active);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_REMOVE_PERSON') and hasAuthority('SCOPE_write')")
    public void delete (@PathVariable Long id) {
        personService.deletePerson(id);
    }
}
