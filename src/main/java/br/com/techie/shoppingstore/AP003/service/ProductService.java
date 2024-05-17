package br.com.techie.shoppingstore.AP003.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import br.com.techie.shoppingstore.AP003.dto.form.ProductFORM;
import br.com.techie.shoppingstore.AP003.infra.exception.DatabaseException;
import br.com.techie.shoppingstore.AP003.infra.exception.ResourceNotFoundException;
import br.com.techie.shoppingstore.AP003.model.Product;
import br.com.techie.shoppingstore.AP003.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;

public class ProductService {

//   @Autowired
//   private ProductRepository productRepository;

//   @Transactional(readOnly = true)
//   public Page<ProductFORM> findAllPaged(Pageable pageable) {
//     Page<Product> list = productRepository.findAll(pageable);
//     return list.map(x -> new ProductFORM(x));
//   }

// @Transactional(readOnly = true)
// public ProductFORM findById(Long id) {
// Optional<Product> obj = productRepository.findById(id);
// Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity
// not found"));
// return new ProductFORM(entity);
// }

//   @Transactional
//   public ProductFORM insert(ProductFORM dto) {
//     Product entity = new Product();
//     copyDtoToEntity(dto, entity);
//     entity = productRepository.save(entity);
//     return new ProductFORM(entity);
//   }

//   @Transactional
//   public ProductFORM update(Long id, ProductFORM dto) {
//     try {
//       Product entity = productRepository.findById(id).get();
//       copyDtoToEntity(dto, entity);
//       entity = productRepository.save(entity);
//       return new ProductFORM(entity);

//     } catch (EntityNotFoundException e) {
//       throw new ResourceNotFoundException("Id not found " + id);
//     }
//   }

//   public void delete(Long id) {
//     try {
//       productRepository.deleteById(id);
//     } catch (EmptyResultDataAccessException e) {
//       throw new ResourceNotFoundException("Id not found " + id);

//     } catch (DataIntegrityViolationException e) {
//       throw new DatabaseException("Integrity violation");
//     }
//   }

//   private void copyDtoToEntity(ProductFORM dto, Product entity) {
//     entity.setNome(dto.name());
//     entity.setPreco(dto.price());
//     entity.setDescricao(dto.description());
//     entity.setQtd_estoque(dto.stock());
//     entity.setUrl_imagem(dto.url_image());
//     entity.setAtributosServidor(dto.chassis());
//     entity.setItemCarrinho(dto);
//     entity.setCategory(dto.category());
//   }
}
