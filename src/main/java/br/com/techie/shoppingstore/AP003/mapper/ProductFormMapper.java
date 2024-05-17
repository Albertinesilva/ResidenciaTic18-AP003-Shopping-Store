package br.com.techie.shoppingstore.AP003.mapper;

import br.com.techie.shoppingstore.AP003.dto.form.ProductFORM;
import br.com.techie.shoppingstore.AP003.model.AtributosServidor;
import br.com.techie.shoppingstore.AP003.model.Produto;

public class ProductFormMapper implements Mapper<ProductFORM, Produto> {
    @Override
    public Produto map(ProductFORM i) {
        Produto newProduct = new Produto();
        AtributosServidor attributes = new AtributosServidor(
                null,
                i.chassis(),
                i.cpu(),
                i.operational_system(),
                i.chipset(),
                i.memory(),
                i.slots(),
                i.storage(),
                i.network(),
                newProduct
        );

        newProduct.setNome(i.name());
        newProduct.setPreco(i.price().longValue());
        newProduct.setDescricao(i.description());
        newProduct.setQtd_estoque(i.stock());
        newProduct.setUrl_imagem(i.url_image());
        newProduct.setAtributosServidor(attributes);

        return newProduct;
    }
}
