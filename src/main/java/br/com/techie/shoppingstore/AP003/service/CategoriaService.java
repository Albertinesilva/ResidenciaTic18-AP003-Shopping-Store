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

import br.com.techie.shoppingstore.AP003.dto.form.CategoriaForm;
import br.com.techie.shoppingstore.AP003.model.Categoria;
import br.com.techie.shoppingstore.AP003.repository.CategoriaRepository;
import br.com.techie.shoppingstore.AP003.service.exception.DatabaseException;
import br.com.techie.shoppingstore.AP003.service.exception.ResourceNotFoundException;

@Service
public class CategoriaService {

  @Autowired
  private CategoriaRepository repository;

  @Transactional(readOnly = true)
  public Page<CategoriaForm> findAllPaged(Pageable pageable) {
    Page<Categoria> list = repository.findAll(pageable);
    return list.map(x -> new CategoriaForm(x));
  }

  @Transactional(readOnly = true)
  public CategoriaForm findById(Long id) {
    Optional<Categoria> obj = repository.findById(id);
    Categoria entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
    return new CategoriaForm(entity);
  }

  @Transactional
	public CategoriaForm insert(CategoriaForm dto) {
		Categoria entity = new Categoria();
		entity.setNome(dto.getNome());
		entity = repository.save(entity);
		return new CategoriaForm(entity);
	}

  @Transactional
	public CategoriaForm update(Long id, CategoriaForm dto) {
		try {
			Categoria entity = repository.findById(id).get();
			entity.setNome(dto.getNome());
			entity = repository.save(entity);
			return new CategoriaForm(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}		
	}

  public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}
}
