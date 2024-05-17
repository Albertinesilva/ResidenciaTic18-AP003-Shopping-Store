package br.com.techie.shoppingstore.AP003.mapper.forms;

import br.com.techie.shoppingstore.AP003.dto.form.ProductFORM;
import br.com.techie.shoppingstore.AP003.mapper.Mapper;
import br.com.techie.shoppingstore.AP003.model.ServerAttribute;
import br.com.techie.shoppingstore.AP003.model.Product;

public class ProductFormMapper implements Mapper<ProductFORM, Product> {
    @Override
    public Product map(ProductFORM i) {
        Product newProduct = new Product();
        ServerAttribute attributes = new ServerAttribute(
                null,
                i.attributes().chassis(),
                i.attributes().cpu(),
                i.attributes().operational_system(),
                i.attributes().chipset(),
                i.attributes().memory(),
                i.attributes().slots(),
                i.attributes().storage(),
                i.attributes().network(),
                newProduct
        );

        newProduct.setName(i.name());
        newProduct.setPrice(i.price());
        newProduct.setDescription(i.description());
        newProduct.setStock(i.stock());
        newProduct.setUrlImage(i.url_image());

        return newProduct;
    }
}
