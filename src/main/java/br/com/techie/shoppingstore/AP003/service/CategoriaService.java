package br.com.techie.shoppingstore.AP003.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.techie.shoppingstore.AP003.dto.form.CategoriaDTO;
import br.com.techie.shoppingstore.AP003.model.Categoria;
import br.com.techie.shoppingstore.AP003.repository.CategoriaRepository;
import br.com.techie.shoppingstore.AP003.service.exception.DatabaseException;
import br.com.techie.shoppingstore.AP003.service.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaService {

  @Autowired
  private CategoriaRepository repository;

  @Transactional(readOnly = true)
  public Page<CategoriaDTO> findAllPaged(Pageable pageable) {
    Page<Categoria> list = repository.findAll(pageable);
    return list.map(x -> new CategoriaDTO(x));
  }

  @Transactional(readOnly = true)
  public CategoriaDTO findById(Long id) {
    Optional<Categoria> obj = repository.findById(id);
    Categoria entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
    return new CategoriaDTO(entity);
  }

  @Transactional
	public CategoriaDTO insert(CategoriaDTO dto) {
		Categoria entity = new Categoria();
		entity.setNome(dto.getNome());
		entity = repository.save(entity);
		return new CategoriaDTO(entity);
	}

  @Transactional
	public CategoriaDTO update(Long id, CategoriaDTO dto) {
		try {
			Categoria entity = repository.findById(id).get();
			entity.setNome(dto.getNome());
			entity = repository.save(entity);
			return new CategoriaDTO(entity);
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
