package br.com.techie.shoppingstore.AP003.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import br.com.techie.shoppingstore.AP003.model.Produto;
import br.com.techie.shoppingstore.AP003.repository.ProdutoRepository;
import br.com.techie.shoppingstore.AP003.service.exception.DatabaseException;
import br.com.techie.shoppingstore.AP003.service.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

public class ProdutoService {

  @Autowired
  private ProdutoRepository repository;

  @Transactional(readOnly = true)
  public Page<ProdutoForm> findAllPaged(Pageable pageable) {
    Page<Produto> list = repository.findAll(pageable);
    return list.map(x -> new ProdutoForm(x));
  }

  @Transactional(readOnly = true)
  public ProdutoForm findById(Long id) {
    Optional<Produto> obj = repository.findById(id);
    Produto entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
    return new ProdutoForm(entity);
  }

  @Transactional
  public ProdutoForm insert(ProdutoForm dto) {
    Produto entity = new Produto();
    copyDtoToEntity(dto, entity);
    entity = repository.save(entity);
    return new ProdutoForm(entity);
  }

  @Transactional
  public ProdutoForm update(Long id, ProdutoForm dto) {
    try {
      Produto entity = repository.findById(id).get();
      copyDtoToEntity(dto, entity);
      entity = repository.save(entity);
      return new ProdutoForm(entity);

    } catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException("Id not found " + id);
    }
  }

  public void delete(Long id) {
    try {
      repository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      throw new ResourceNotFoundException("Id not found " + id);

    } catch (DataIntegrityViolationException e) {
      throw new DatabaseException("Integrity violation");
    }
  }

  private void copyDtoToEntity(ProdutoForm dto, Produto entity) {
    entity.setNome(dto.getNome());
    entity.setPreco(dto.getPreco());
    entity.setDescricao(dto.getDescricao());
    entity.setQtd_estoque(dto.getQtd_estoque());
    entity.setUrl_imagem(dto.getUrl_imagem());
    entity.setAtributosServidor(dto.getAtributosServidor());
    entity.setItemCarrinho(dto.getItemCarrinho());
    entity.setCategoria(dto.getCategoria());
  }
}
