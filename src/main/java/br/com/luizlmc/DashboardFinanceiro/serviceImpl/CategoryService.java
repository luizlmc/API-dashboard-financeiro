package br.com.luizlmc.DashboardFinanceiro.serviceImpl;

import br.com.luizlmc.DashboardFinanceiro.dto.CategoryDTO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<CategoryDTO> findAll();
    CategoryDTO createdCategory(CategoryDTO categoryDTO, HttpServletResponse response);
    Optional<CategoryDTO> findById(Long id);
}
