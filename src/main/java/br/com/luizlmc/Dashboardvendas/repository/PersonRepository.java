package br.com.luizlmc.Dashboardvendas.repository;

import br.com.luizlmc.Dashboardvendas.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
