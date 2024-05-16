package br.com.techie.shoppingstore.AP003.controller;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.techie.shoppingstore.AP003.dto.form.CategoriaForm;
import br.com.techie.shoppingstore.AP003.service.CategoriaService;
import br.com.techie.shoppingstore.AP003.service.exception.ResourceNotFoundException;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CategoriaController.class)
@AutoConfigureMockMvc
public class CategoriaControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoriaService categoriaService;

    private final Long existingId = 1L;
    private final Long nonExistingId = 2L;
    private final Long dependentId = 3L;
    private final CategoriaForm categoriaForm = new CategoriaForm(existingId, "Categoria Teste");
    private final Page<CategoriaForm> page = new PageImpl<>(List.of(categoriaForm));

    @Test
    @DisplayName("Deve retornar lista paginada de categorias")
    public void listarDeveRetornarListaPaginadaDeCategorias() throws Exception {
        when(categoriaService.findAllPaged(ArgumentMatchers.any(Pageable.class))).thenReturn(page);

        mockMvc.perform(MockMvcRequestBuilders.get("/categorias")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id").value(existingId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].nome").value("Categoria Teste"));
    }

    @Test
    @DisplayName("Deve retornar categoria por ID")
    public void buscarPorIdDeveRetornarCategoriaPorId() throws Exception {
        when(categoriaService.findById(existingId)).thenReturn(categoriaForm);

        mockMvc.perform(MockMvcRequestBuilders.get("/categorias/{id}", existingId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(existingId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Categoria Teste"));
    }

    @Test
    @DisplayName("Deve retornar status 404 quando buscar por ID que não existe")
    public void buscarPorIdDeveRetornarStatus404QuandoBuscarPorIdQueNaoExiste() throws Exception {
        when(categoriaService.findById(nonExistingId)).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/categorias/{id}", nonExistingId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    
    @Test
    @DisplayName("Deve criar uma nova categoria")
    public void criarDeveCriarNovaCategoria() throws Exception {
        when(categoriaService.insert(ArgumentMatchers.any(CategoriaForm.class))).thenReturn(categoriaForm);

        mockMvc.perform(MockMvcRequestBuilders.post("/categorias")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nome\": \"Categoria Teste\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(existingId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Categoria Teste"));
    }

    @Test
    @DisplayName("Deve atualizar uma categoria existente")
    public void atualizarDeveAtualizarCategoriaExistente() throws Exception {
        when(categoriaService.update(existingId, categoriaForm)).thenReturn(categoriaForm);

        mockMvc.perform(MockMvcRequestBuilders.put("/categorias/{id}", existingId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nome\": \"Categoria Teste\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(existingId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Categoria Teste"));
    }

    @Test
    @DisplayName("Deve deletar uma categoria existente")
    public void deletarDeveDeletarCategoriaExistente() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/categorias/{id}", existingId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        verify(categoriaService, times(1)).delete(existingId);
    }

    @Test
    @DisplayName("Deve retornar status 404 ao tentar deletar uma categoria inexistente")
    public void deletarDeveRetornarStatus404AoTentarDeletarCategoriaInexistente() throws Exception {
        doThrow(ResourceNotFoundException.class).when(categoriaService).delete(nonExistingId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/categorias/{id}", nonExistingId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        verify(categoriaService, times(1)).delete(nonExistingId);
    }
    
}

