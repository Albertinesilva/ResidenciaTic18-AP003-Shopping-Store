package br.com.techie.shoppingstore.AP003.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.techie.shoppingstore.AP003.model.Carrinho;
import br.com.techie.shoppingstore.AP003.repository.CarrinhoRepository;
import br.com.techie.shoppingstore.AP003.service.exception.DatabaseException;
import br.com.techie.shoppingstore.AP003.service.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CarrinhoService {

  @Autowired
  private CarrinhoRepository carrinhoRepository;

  @Transactional(readOnly = true)
  public Page<CarrinhoForm> findAllPaged(Pageable pageable) {
    Page<Carrinho> list = carrinhoRepository.findAll(pageable);
    return list.map(x -> new CarrinhoForm(x));
  }

  @Transactional(readOnly = true)
  public CarrinhoForm findById(Long id) {
    Optional<Carrinho> obj = carrinhoRepository.findById(id);
    Carrinho entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
    return new CarrinhoForm(entity);
  }

  @Transactional
  public CarrinhoForm insert(CarrinhoForm dto) {
    Carrinho entity = new Carrinho();
    copyDtoToEntity(dto, entity);
    entity = carrinhoRepository.save(entity);
    return new CarrinhoForm(entity);
  }

  @Transactional
  public Carrinho update(Long id, Carrinho dto) {
    try {
      Carrinho entity = carrinhoRepository.findById(id).get();
      return new CarrinhoForm(entity);

    } catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException("Id not found " + id);
    }
  }

  public void delete(Long id) {
    try {
      carrinhoRepository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      throw new ResourceNotFoundException("Id not found " + id);

    } catch (DataIntegrityViolationException e) {
      throw new DatabaseException("Integrity violation");
    }
  }

  private void copyDtoToEntity(CarrinhoForm dto, Carrinho entity) {
    entity.setUsuario(dto.getUsuario());
    entity.setPagamento(dto.getPagamento());
    entity.setItem_carrinho_id(dto.getItem_carrinho_id());
    entity.setPreco_total(dto.getPreco_total());
    entity.setQtd_itens(dto.getQtd_itens());
    entity.setDt_pedido(dto.getDt_pedido());
    entity.setStatus(dto.getStatus());
  }
}
