package br.com.techie.shoppingstore.AP003.mapper.forms;

import br.com.techie.shoppingstore.AP003.dto.form.ProductFORM;
import br.com.techie.shoppingstore.AP003.model.Product;
import br.com.techie.shoppingstore.AP003.model.ServerAttribute;

public class ServerAttributeFormMapper {
    public ServerAttribute map(ProductFORM i, Product product) {
        return new ServerAttribute(
                null,
                i.attributes().chassis(),
                i.attributes().cpu(),
                i.attributes().operational_system(),
                i.attributes().chipset(),
                i.attributes().memory(),
                i.attributes().slots(),
                i.attributes().storage(),
                i.attributes().network(),
                product
        );    }
}
