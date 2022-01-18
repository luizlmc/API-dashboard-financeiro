package br.com.luizlmc.DashboardFinanceiro.repository;

import br.com.luizlmc.DashboardFinanceiro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByEmail(String email);
}
