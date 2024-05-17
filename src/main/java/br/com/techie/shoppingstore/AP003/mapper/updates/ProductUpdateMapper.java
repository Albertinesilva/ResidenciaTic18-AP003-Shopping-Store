package br.com.techie.shoppingstore.AP003.mapper.updates;

import br.com.techie.shoppingstore.AP003.dto.form.ProductUpdateFORM;
import br.com.techie.shoppingstore.AP003.model.Product;

public class ProductUpdateMapper {
    public Product map(ProductUpdateFORM form, Product oldProduct){
        oldProduct.setStock(form.stock());
        oldProduct.setName(form.name());
        oldProduct.setPrice(form.price());
        oldProduct.setDescription(form.description());
        oldProduct.setUrlImage(form.url_image());
        return oldProduct;
    }
}
