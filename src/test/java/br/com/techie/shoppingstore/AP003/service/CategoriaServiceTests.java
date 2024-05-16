package br.com.techie.shoppingstore.AP003.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.techie.shoppingstore.AP003.dto.form.CategoriaForm;
import br.com.techie.shoppingstore.AP003.model.Categoria;
import br.com.techie.shoppingstore.AP003.repository.CategoriaRepository;
import br.com.techie.shoppingstore.AP003.service.exception.DatabaseException;
import br.com.techie.shoppingstore.AP003.service.exception.ResourceNotFoundException;
import br.com.techie.shoppingstore.AP003.tests.Factory;
import jakarta.persistence.EntityNotFoundException;

@ExtendWith(SpringExtension.class)
public class CategoriaServiceTests {

  @InjectMocks
  private CategoriaService categoriaService;

  @Mock
  private CategoriaRepository categoriaRepository;

  private long existingId;
  private long nonExistingId;
  private long dependentId;
  private PageImpl<Categoria> page;
  private Categoria categoria;
  CategoriaForm categoriaForm;

  @BeforeEach
  void setUp() throws Exception {
    existingId = 1L;
    nonExistingId = 2L;
    dependentId = 3L;
    categoria = Factory.createCategoria();
    categoriaForm = Factory.createCategoriaForm();
    page = new PageImpl<>(List.of(categoria));

    when(categoriaRepository.findAll((Pageable) ArgumentMatchers.any())).thenReturn(page);

    when(categoriaRepository.save(ArgumentMatchers.any())).thenReturn(categoria);

    when(categoriaRepository.findById(existingId)).thenReturn(Optional.of(categoria));
    when(categoriaRepository.findById(nonExistingId)).thenReturn(Optional.empty());

    doNothing().when(categoriaRepository).deleteById(existingId);
    doThrow(EmptyResultDataAccessException.class).when(categoriaRepository).deleteById(nonExistingId);
    doThrow(DataIntegrityViolationException.class).when(categoriaRepository).deleteById(dependentId);
  }

  @Test
  @DisplayName("update should return CategoriaForm when id exists")
  public void updateShouldReturnCategoriaFormWhenIdExists() {

    CategoriaForm result = categoriaService.update(existingId, categoriaForm);

    Assertions.assertNotNull(result);
  }

  @Test
  @DisplayName("update should throw ResourceNotFoundException when id does not exist")
  public void updateIdShouldThrowResourceNotFoundExceptionWhenIdDosNotExists() {
    when(categoriaRepository.findById(nonExistingId)).thenThrow(EntityNotFoundException.class);
    Assertions.assertThrows(ResourceNotFoundException.class, () -> {
      categoriaService.update(nonExistingId, categoriaForm);
    });
  }

  @Test
  @DisplayName("findById should return CategoriaDTO when id exists")
  public void findByIdShouldReturnCategoriaFormWhenIdExists() {

    CategoriaForm result = categoriaService.findById(existingId);

    Assertions.assertNotNull(result);
    verify(categoriaRepository, Mockito.times(1)).findById(existingId);
  }

  @Test
  @DisplayName("findById should throw ResourceNotFoundException when id does not exist")
  public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDosNotExists() {

    Assertions.assertThrows(ResourceNotFoundException.class, () -> {
      categoriaService.findById(nonExistingId);
    });
  }

  @Test
  @DisplayName("findAllPaged should return page")
  public void findAllPagedShouldReturnPage() {

    Pageable pageable = PageRequest.of(0, 10);

    Page<CategoriaForm> result = categoriaService.findAllPaged(pageable);

    Assertions.assertNotNull(result);
    verify(categoriaRepository, Mockito.times(1)).findAll(pageable);
  }

  @Test
  @DisplayName("delete should do nothing when id exists")
  public void deleteShouldDoNothingWhenIdExists() {

    Assertions.assertDoesNotThrow(() -> {
      categoriaService.delete(existingId);
    });

    verify(categoriaRepository, Mockito.times(1)).deleteById(existingId);
  }

  @Test
  @DisplayName("delete should throw EmptyResultDataAccessException when id does not exist")
  public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {

    Assertions.assertThrows(ResourceNotFoundException.class, () -> {
      categoriaService.delete(nonExistingId);
    });

    verify(categoriaRepository, Mockito.times(1)).deleteById(nonExistingId);
  }

  @Test
  @DisplayName("delete should throw DatabaseException when id is dependent")
  public void deleteShouldThrowDatabaseExceptionWhenIdWhenDependentId() {

    Assertions.assertThrows(DatabaseException.class, () -> {
      categoriaService.delete(dependentId);
    });

    verify(categoriaRepository, Mockito.times(1)).deleteById(dependentId);
  }
}