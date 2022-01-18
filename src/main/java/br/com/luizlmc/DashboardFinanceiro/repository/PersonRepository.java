package br.com.luizlmc.DashboardFinanceiro.repository;

import br.com.luizlmc.DashboardFinanceiro.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
