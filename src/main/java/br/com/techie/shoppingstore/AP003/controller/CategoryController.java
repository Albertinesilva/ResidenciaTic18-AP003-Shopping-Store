package br.com.techie.shoppingstore.AP003.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.techie.shoppingstore.AP003.dto.form.CategoryFORM;
import br.com.techie.shoppingstore.AP003.dto.form.CategoryUpdateFORM;
import br.com.techie.shoppingstore.AP003.dto.view.CategoryVIEW;
import br.com.techie.shoppingstore.AP003.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Page<CategoryVIEW>> getAll(Pageable pageable) {
        Page<CategoryVIEW> categories = categoryService.findAllPaged(pageable);
        return ResponseEntity.ok().body(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryVIEW> getById(@PathVariable Long id) {
        CategoryVIEW category = categoryService.findById(id);
        return ResponseEntity.ok().body(category);
    }

    @PostMapping
    public ResponseEntity<CategoryVIEW> create(@RequestBody CategoryFORM categoryForm) {
        CategoryVIEW novaCategoria = categoryService.insert(categoryForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaCategoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryVIEW> update(@PathVariable Long id, @RequestBody CategoryUpdateFORM categoryUpdateForm) {
        CategoryVIEW categoriaAtualizada = categoryService.update(categoryUpdateForm);
        return ResponseEntity.ok().body(categoriaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
