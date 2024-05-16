package br.com.techie.shoppingstore.AP003.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.techie.shoppingstore.AP003.dto.form.CategoryFORM;
import br.com.techie.shoppingstore.AP003.model.Categoria;
import br.com.techie.shoppingstore.AP003.repository.CategoriaRepository;
import br.com.techie.shoppingstore.AP003.service.exception.DatabaseException;
import br.com.techie.shoppingstore.AP003.service.exception.ResourceNotFoundException;

@Service
public class CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  @Transactional(readOnly = true)
  public Page<CategoryFORM> findAllPaged(Pageable pageable) {
    Page<Category> list = categoryRepository.findAll(pageable);
    return list.map(x -> new CategoryFORM(x));
  }

  @Transactional(readOnly = true)
  public CategoryFORM findById(Long id) {
    Optional<Category> obj = categoryRepository.findById(id);
    Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
    return new CategoryFORM(entity);
  }

  @Transactional
	public CategoryFORM insert(CategoryFORM dto) {
		Category entity = new Categoria();
		entity.setNome(dto.name());
		entity = categoryRepository.save(entity);
		return new CategoryForm(entity);
	}

  @Transactional
	public CategoryFORM update(Long id, CategoryFORM dto) {
		try {
			Category entity = categoryRepository.findById(id).get();
			entity.setNome(dto.name());
			entity = categoryRepository.save(entity);
			return new CategoryFORM(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}		
	}

  public void delete(Long id) {
		try {
			categoryRepository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}
}
