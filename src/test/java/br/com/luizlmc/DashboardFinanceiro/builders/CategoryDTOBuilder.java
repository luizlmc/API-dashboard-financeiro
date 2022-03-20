package br.com.luizlmc.DashboardFinanceiro.builders;

import br.com.luizlmc.DashboardFinanceiro.dto.CategoryDTO;

public class CategoryDTOBuilder {

    private CategoryDTO categoryDTO;

    public static CategoryDTOBuilder aCategoryDTO(){
        CategoryDTOBuilder builder = new CategoryDTOBuilder();
        builder.categoryDTO = new CategoryDTO();
        builder.categoryDTO.setName("CategoryDTO1");
        return builder;
    }

    public CategoryDTO now(){
        return categoryDTO;
    }
}
