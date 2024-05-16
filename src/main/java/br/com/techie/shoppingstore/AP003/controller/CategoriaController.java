package br.com.techie.shoppingstore.AP003.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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

import br.com.techie.shoppingstore.AP003.dto.form.CategoriaForm;
import br.com.techie.shoppingstore.AP003.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<Page<CategoriaForm>> listar(
    		@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10
	) Pageable paginacao) {
        Page<CategoriaForm> categorias = categoriaService.findAllPaged(paginacao);
        return ResponseEntity.ok().body(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaForm> buscarPorId(@PathVariable Long id) {
        CategoriaForm categoria = categoriaService.findById(id);
        return ResponseEntity.ok().body(categoria);
    }

    @PostMapping
    public ResponseEntity<CategoriaForm> criar(@RequestBody CategoriaForm categoriaForm) {
        CategoriaForm novaCategoria = categoriaService.insert(categoriaForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaCategoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaForm> atualizar(@PathVariable Long id, @RequestBody CategoriaForm categoriaForm) {
        CategoriaForm categoriaAtualizada = categoriaService.update(id, categoriaForm);
        return ResponseEntity.ok().body(categoriaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}