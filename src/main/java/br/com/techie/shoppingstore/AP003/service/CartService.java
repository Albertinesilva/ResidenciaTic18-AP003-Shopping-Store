// package br.com.techie.shoppingstore.AP003.service;

// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.dao.DataIntegrityViolationException;
// import org.springframework.dao.EmptyResultDataAccessException;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// import br.com.techie.shoppingstore.AP003.infra.exception.DatabaseException;
// import br.com.techie.shoppingstore.AP003.infra.exception.ResourceNotFoundException;
// import br.com.techie.shoppingstore.AP003.model.Carrinho;
// import br.com.techie.shoppingstore.AP003.repository.CarrinhoRepository;
// import jakarta.persistence.EntityNotFoundException;

// @Service
// public class CartService {

//   @Autowired
//   private CartRepository cartRepository;

//   @Transactional(readOnly = true)
//   public Page<CartForm> findAllPaged(Pageable pageable) {
//     Page<Cart> list = cartRepository.findAll(pageable);
//     return list.map(x -> new CartForm(x));
//   }

//   @Transactional(readOnly = true)
//   public CartForm findById(Long id) {
//     Optional<Cart> obj = cartRepository.findById(id);
//     Carrinho entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
//     return new CartForm(entity);
//   }

//   @Transactional
//   public CartForm insert(CartForm dto) {
//     Cart entity = new Carrinho();
//     copyDtoToEntity(dto, entity);
//     entity = cartRepository.save(entity);
//     return new CartForm(entity);
//   }

//   @Transactional
//   public Carrinho update(Long id, Carrinho dto) {
//     try {
//       Cart entity = cartRepository.findById(id).get();
//       return new CartForm(entity);

//     } catch (EntityNotFoundException e) {
//       throw new ResourceNotFoundException("Id not found " + id);
//     }
//   }

//   public void delete(Long id) {
//     try {
//       cartRepository.deleteById(id);
//     } catch (EmptyResultDataAccessException e) {
//       throw new ResourceNotFoundException("Id not found " + id);

//     } catch (DataIntegrityViolationException e) {
//       throw new DatabaseException("Integrity violation");
//     }
//   }

//   private void copyDtoToEntity(CartForm dto, Cart entity) {
//     entity.setUsuario(dto.getUsuario());
//     entity.setPagamento(dto.getPagamento());
//     entity.setItem_carrinho_id(dto.getItem_carrinho_id());
//     entity.setPreco_total(dto.getPreco_total());
//     entity.setQtd_itens(dto.getQtd_itens());
//     entity.setDt_pedido(dto.getDt_pedido());
//     entity.setStatus(dto.getStatus());
//   }
// }
