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

import br.com.techie.shoppingstore.AP003.dto.form.ProductFORM;
import br.com.techie.shoppingstore.AP003.dto.form.ProductUpdateFORM;
import br.com.techie.shoppingstore.AP003.dto.view.ProductVIEW;
import br.com.techie.shoppingstore.AP003.infra.exception.ErrorMessage;
import br.com.techie.shoppingstore.AP003.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @Operation(summary = "Listar todos os produtos", description = "Recupera uma lista paginada de todos os produtos.", 
            responses = {
	     @ApiResponse(responseCode = "200", description = "Lista de produtos recuperada com sucesso.", 
	                  content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductVIEW.class)))),
	 })
    @GetMapping
    public ResponseEntity<Page<ProductVIEW>> getAll(Pageable pageable) {
        Page<ProductVIEW> products = productService.findAllPaged(pageable);
        return ResponseEntity.ok().body(products);
    }
    
    @Operation(summary = "Recuperar produto pelo ID", description = "Recupera os detalhes de um produto específico pelo seu ID.", 
            responses = {
	     @ApiResponse(responseCode = "200", description = "Produto recuperado com sucesso.", 
	                  content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductVIEW.class))),
	     @ApiResponse(responseCode = "404", description = "Produto não encontrado.", 
	                  content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
	 })
    @GetMapping("/{id}")
    public ResponseEntity<ProductVIEW> getById(@PathVariable Long id) {
        ProductVIEW product = productService.findById(id);
        return ResponseEntity.ok().body(product);
    }
    
    @Operation(summary = "Criar um novo produto", description = "Cria um novo produto com os detalhes fornecidos.", 
            responses = {
	     @ApiResponse(responseCode = "201", description = "Produto criado com sucesso.", 
	                  content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductVIEW.class))),
	     @ApiResponse(responseCode = "422", description = "Dados de entrada inválidos.", 
	                  content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
	 })
    @PostMapping
    public ResponseEntity<ProductVIEW> criate(@RequestBody ProductFORM productForm) {
        ProductVIEW novoProduto = productService.insert(productForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }
    
    @Operation(summary = "Atualizar produto", description = "Atualiza os detalhes de um produto existente.", 
            responses = {
	     @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso.", 
	                  content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductVIEW.class))),
	     @ApiResponse(responseCode = "404", description = "Produto não encontrado.", 
	                  content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
	     @ApiResponse(responseCode = "422", description = "Dados de entrada inválidos ou formatados incorretamente.", 
	                  content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
	 })
    @PutMapping("/{id}")
    public ResponseEntity<ProductVIEW> update(@PathVariable Long id, @RequestBody ProductUpdateFORM productUpdateForm) {
        ProductVIEW produtoAtualizado = productService.update(productUpdateForm);
        return ResponseEntity.ok().body(produtoAtualizado);
    }
    
    @Operation(summary = "Deletar produto", description = "Deleta um produto existente pelo seu ID.", 
            responses = {
	     @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso."),
	     @ApiResponse(responseCode = "404", description = "Produto não encontrado.", 
	                  content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
	 })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
