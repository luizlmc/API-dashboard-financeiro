package br.com.luizlmc.DashboardFinanceiro.dto.mapper;

import br.com.luizlmc.DashboardFinanceiro.dto.CategoryDTO;
import br.com.luizlmc.DashboardFinanceiro.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toCategory(CategoryDTO categoryDTO);
    CategoryDTO toCategoryDTO(Category category);
}
