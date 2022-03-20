package br.com.luizlmc.DashboardFinanceiro.builders;

import br.com.luizlmc.DashboardFinanceiro.model.Category;

public class CategoryBuilder {

    private Category category;

    public static CategoryBuilder aCategory(){
        CategoryBuilder builder = new CategoryBuilder();
        builder.category = new Category();
        builder.category.setName("Category1");
        return builder;
    }

    public Category now(){
        return  category;
    }
}
