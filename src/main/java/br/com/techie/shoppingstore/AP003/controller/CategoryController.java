package br.com.techie.shoppingstore.AP003.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.techie.shoppingstore.AP003.dto.form.CategoryFORM;
import br.com.techie.shoppingstore.AP003.dto.form.CategoryUpdateFORM;
import br.com.techie.shoppingstore.AP003.dto.view.CategoryVIEW;
import br.com.techie.shoppingstore.AP003.infra.exception.ErrorMessage;
import br.com.techie.shoppingstore.AP003.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    
    @Operation(summary = "Listar todas as categorias", description = "Recupera uma lista paginada de todas as categorias.", 
            responses = {
	     @ApiResponse(responseCode = "200", description = "Lista de categorias recuperada com sucesso.", 
	                  content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CategoryVIEW.class)))),
	 })
    @GetMapping
    public ResponseEntity<Page<CategoryVIEW>> getAll(Pageable pageable) {
        Page<CategoryVIEW> categories = categoryService.findAllPaged(pageable);
        return ResponseEntity.ok().body(categories);
    }
    
    @Operation(summary = "Recuperar categoria pelo ID", description = "Recupera os detalhes de uma categoria específica pelo seu ID.", 
            responses = {
	     @ApiResponse(responseCode = "200", description = "Categoria recuperada com sucesso.", 
	                  content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryVIEW.class))),
	     @ApiResponse(responseCode = "404", description = "Categoria não encontrada.", 
	                  content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
	 })
    @GetMapping("/{id}")
    public ResponseEntity<CategoryVIEW> getById(@PathVariable Long id) {
        CategoryVIEW category = categoryService.findById(id);
        return ResponseEntity.ok().body(category);
    }
    
    @Operation(summary = "Criar uma nova categoria", description = "Cria uma nova categoria com os detalhes fornecidos.", responses = {
        @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso.", 
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryVIEW.class))),
        @ApiResponse(responseCode = "422", description = "Dados de entrada inválidos.", 
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
    })
    @PostMapping
    public ResponseEntity<CategoryVIEW> create(@RequestBody CategoryFORM categoryForm) {
        CategoryVIEW novaCategoria = categoryService.insert(categoryForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaCategoria);
    }
    
    @Operation(summary = "Atualizar categoria", description = "Atualiza os detalhes de uma categoria existente.", 
            responses = {
	     @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso.", 
	                  content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryVIEW.class))),
	     @ApiResponse(responseCode = "404", description = "Categoria não encontrada.", 
	                  content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
	     @ApiResponse(responseCode = "422", description = "Dados de entrada inválidos ou formatados incorretamente.", 
	                  content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
	 })
    @PutMapping("/{id}")
    public ResponseEntity<CategoryVIEW> update(@PathVariable Long id, @RequestBody CategoryUpdateFORM categoryUpdateForm) {
        CategoryVIEW categoriaAtualizada = categoryService.update(categoryUpdateForm);
        return ResponseEntity.ok().body(categoriaAtualizada);
    }
    
    @Operation(summary = "Deletar categoria", description = "Deleta uma categoria existente pelo seu ID.", 
            responses = {
	     @ApiResponse(responseCode = "204", description = "Categoria deletada com sucesso."),
	     @ApiResponse(responseCode = "404", description = "Categoria não encontrada.", 
	                  content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
	 })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
