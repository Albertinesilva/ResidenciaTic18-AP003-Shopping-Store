package br.com.techie.shoppingstore.AP003.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import br.com.techie.shoppingstore.AP003.dto.form.CategoryFORM;
import br.com.techie.shoppingstore.AP003.dto.form.CategoryUpdateFORM;
import br.com.techie.shoppingstore.AP003.dto.view.CategoryVIEW;
import br.com.techie.shoppingstore.AP003.infra.exception.ResourceNotFoundException;
import br.com.techie.shoppingstore.AP003.mapper.updates.CategoryUpdateMapper;
import br.com.techie.shoppingstore.AP003.mapper.views.CategoryViewMapper;

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

import br.com.techie.shoppingstore.AP003.model.Category;
import br.com.techie.shoppingstore.AP003.repository.CategoryRepository;
import br.com.techie.shoppingstore.AP003.tests.Factory;

@ExtendWith(SpringExtension.class)
public class CategoryServiceTests {

  @InjectMocks
  private CategoryService categoryService;

  @Mock
  private CategoryViewMapper categoryViewMapper;

  @Mock
  private CategoryUpdateMapper categoryUpdateMapper;

  @Mock
  private CategoryRepository categoryRepository;


  private long existingId;
  private long nonExistingId;
  private long dependentId;
  private PageImpl<Category> page;
  private Category category;
  CategoryFORM categoryForm;
  CategoryUpdateFORM categoryUpdateForm;

  @BeforeEach
  void setUp() throws Exception {
    existingId = 1L;
    nonExistingId = 2L;
    dependentId = 3L;
    category = Factory.createCategory();
    categoryForm = Factory.createCategoryForm();
    categoryUpdateForm = Factory.createCategoryUpdateForm();

    page = new PageImpl<>(List.of(category));

    when(categoryRepository.findAll((Pageable) ArgumentMatchers.any())).thenReturn(page);

    when(categoryRepository.save(ArgumentMatchers.any())).thenReturn(category);

    when(categoryRepository.findById(nonExistingId)).thenThrow(ResourceNotFoundException.class);

    when(categoryRepository.findById(existingId)).thenReturn(Optional.of(category));

    doNothing().when(categoryRepository).deleteById(existingId);
    doThrow(EmptyResultDataAccessException.class).when(categoryRepository).deleteById(nonExistingId);
    doThrow(DataIntegrityViolationException.class).when(categoryRepository).deleteById(dependentId);

  }

  @Test
  @DisplayName("update should return CategoriaForm when id exists")
  public void updateShouldReturnCategoriaFormWhenIdExists() {

    CategoryVIEW result = categoryService.update(categoryUpdateForm);

    Assertions.assertNotNull(result);
  }

  @Test
  @DisplayName("update should throw ResourceNotFoundException when id does not exist")
  public void updateIdShouldThrowResourceNotFoundExceptionWhenIdDosNotExists() {
      // Configura o mock para lançar ResourceNotFoundException quando findById for chamado com nonExistingId
      when(categoryRepository.findById(nonExistingId)).thenThrow(ResourceNotFoundException.class);
      
      // Verifica se o método update lança ResourceNotFoundException ao ser chamado
      Assertions.assertThrows(ResourceNotFoundException.class, () -> {
          // Antes de chamar o método update, garantimos que a configuração do mock esteja aplicada corretamente
          categoryService.update(categoryUpdateForm);
      });
  }
  



  @Test
  @DisplayName("findById should return CategoriaDTO when id exists")
  public void findByIdShouldReturnCategoriaFormWhenIdExists() {

    CategoryVIEW result = categoryService.findById(existingId);

    Assertions.assertNotNull(result);
    verify(categoryRepository, Mockito.times(1)).findById(existingId);
  }

  @Test
  @DisplayName("findById should throw ResourceNotFoundException when id does not exist")
  public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDosNotExists() {

    Assertions.assertThrows(ResourceNotFoundException.class, () -> {
      categoryService.findById(nonExistingId);
    });
  }

  @Test
  @DisplayName("findAllPaged should return page")
  public void findAllPagedShouldReturnPage() {

    Pageable pageable = PageRequest.of(0, 10);

    Page<CategoryVIEW> result = categoryService.findAllPaged(pageable);

    Assertions.assertNotNull(result);
    verify(categoryRepository, Mockito.times(1)).findAll(pageable);
  }

  @Test
  @DisplayName("delete should do nothing when id exists")
  public void deleteShouldDoNothingWhenIdExists() {

    Assertions.assertDoesNotThrow(() -> {
      categoryService.delete(existingId);
    });

    verify(categoryRepository, Mockito.times(1)).deleteById(existingId);
  }

  @Test
  @DisplayName("delete should throw EmptyResultDataAccessException when id does not exist")
  public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {

    Assertions.assertThrows(Exception.class, () -> {
      categoryService.delete(nonExistingId);
    });

    verify(categoryRepository, Mockito.times(1)).deleteById(nonExistingId);
  }

  @Test
  @DisplayName("delete should throw DatabaseException when id is dependent")
  public void deleteShouldThrowDatabaseExceptionWhenIdWhenDependentId() {

    Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
      categoryService.delete(dependentId);
    });

    verify(categoryRepository, Mockito.times(1)).deleteById(dependentId);
  }
}