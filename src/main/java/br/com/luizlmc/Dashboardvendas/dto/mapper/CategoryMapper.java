package br.com.luizlmc.Dashboardvendas.dto.mapper;

import br.com.luizlmc.Dashboardvendas.dto.CategoryDTO;
import br.com.luizlmc.Dashboardvendas.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toCategory(CategoryDTO categoryDTO);
    CategoryDTO toCategoryDTO(Category category);
}
