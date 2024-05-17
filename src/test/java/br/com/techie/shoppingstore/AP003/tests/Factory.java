package br.com.techie.shoppingstore.AP003.tests;

import br.com.techie.shoppingstore.AP003.dto.form.CategoryFORM;
import br.com.techie.shoppingstore.AP003.dto.form.CategoryUpdateFORM;
import br.com.techie.shoppingstore.AP003.mapper.forms.CategoryFormMapper;
import br.com.techie.shoppingstore.AP003.model.Category;

public class Factory {

    public static Category createCategory() {
        return new Category(1L, "Servidores de Aplicação e Web");
    }

    public static CategoryFORM createCategoryForm() {
        Category category = createCategory();
        return new CategoryFORM(category.getName());
    }

    public static CategoryUpdateFORM createCategoryUpdateForm() {
        Category category = createCategory();
        return new CategoryUpdateFORM(category.getId() ,category.getName());
    }
}