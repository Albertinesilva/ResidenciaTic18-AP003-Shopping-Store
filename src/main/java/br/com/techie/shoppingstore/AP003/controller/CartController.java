package br.com.techie.shoppingstore.AP003.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.techie.shoppingstore.AP003.dto.form.CartFORM;
import br.com.techie.shoppingstore.AP003.dto.form.CartUpdateFORM;
import br.com.techie.shoppingstore.AP003.dto.view.CartVIEW;
import br.com.techie.shoppingstore.AP003.dto.view.UserSystemVIEW;
import br.com.techie.shoppingstore.AP003.infra.exception.ErrorMessage;
import br.com.techie.shoppingstore.AP003.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "carts", description = "Contém todas as operações para cadastro, edição e leitura de carrinhos de compras.")
@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;
    
    @Operation(summary = "Listar todos os carrinhos de compras", description = "Recupera uma lista paginada de todos os carrinhos de compras.", 
            security = @SecurityRequirement(name = "security"), responses = {
	     @ApiResponse(responseCode = "200", description = "Lista de carrinhos recuperada com sucesso.", 
	                  content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CartVIEW.class)))),
    })
    @GetMapping
    public ResponseEntity<Page<CartVIEW>> getAll(Pageable pageable) {
        Page<CartVIEW> carts = cartService.findAllPaged(pageable);
        return ResponseEntity.ok().body(carts);
    }
    
    @Operation(summary = "Recuperar carrinho pelo ID", description = "Recupera os detalhes de um carrinho específico pelo seu ID.", 
            security = @SecurityRequirement(name = "security"), responses = {
	     @ApiResponse(responseCode = "200", description = "Carrinho recuperado com sucesso.", 
	                  content = @Content(mediaType = "application/json", schema = @Schema(implementation = CartVIEW.class))),
	     @ApiResponse(responseCode = "403", description = "Usuário sem permissão para acessar este recurso.", 
	                  content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
	     @ApiResponse(responseCode = "404", description = "Carrinho não encontrado.", 
                  content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<CartVIEW> getById(@PathVariable Long id) {
        CartVIEW cart = cartService.findById(id);
        return ResponseEntity.ok().body(cart);
    }
    
    @Operation(summary = "Criar um novo carrinho", description = "Cria um novo carrinho de compras com os detalhes fornecidos.", responses = {
        @ApiResponse(responseCode = "201", description = "Carrinho criado com sucesso.", 
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = CartVIEW.class))),
        @ApiResponse(responseCode = "422", description = "Dados de entrada inválidos.", 
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
    })
    @PostMapping
    public ResponseEntity<CartVIEW> create(@RequestBody CartFORM cartForm) {
        CartVIEW novoCart = cartService.insert(cartForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCart);
    }
    
    @Operation(summary = "Atualizar carrinho", description = "Atualiza os detalhes de um carrinho existente.", 
            security = @SecurityRequirement(name = "security"), responses = {
	     @ApiResponse(responseCode = "200", description = "Carrinho atualizado com sucesso.", 
	                  content = @Content(mediaType = "application/json", schema = @Schema(implementation = CartVIEW.class))),
	     @ApiResponse(responseCode = "403", description = "Usuário sem permissão para acessar este recurso.", 
	                  content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
	     @ApiResponse(responseCode = "422", description = "Dados de entrada inválidos ou formatados incorretamente.", 
	                  content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
	 })
    @PutMapping("/{id}")
    public ResponseEntity<CartVIEW> update(@PathVariable Long id, @RequestBody CartUpdateFORM cartUpdateForm) {
        CartVIEW cartAtualizado = cartService.update(cartUpdateForm);
        return ResponseEntity.ok().body(cartAtualizado);
    }
    
    @Operation(summary = "Deletar carrinho", description = "Deleta um carrinho existente pelo seu ID.", 
            security = @SecurityRequirement(name = "security"), responses = {
	     @ApiResponse(responseCode = "204", description = "Carrinho deletado com sucesso."),
	     @ApiResponse(responseCode = "403", description = "Usuário sem permissão para acessar este recurso.", 
	                  content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
	     @ApiResponse(responseCode = "422", description = "Dados de entrada inválidos ou formatados incorretamente.", 
	                  content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
	 })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cartService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
