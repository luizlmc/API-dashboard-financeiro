package br.com.luizlmc.Dashboardvendas.repository;

import br.com.luizlmc.Dashboardvendas.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
