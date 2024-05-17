package br.com.techie.shoppingstore.AP003.mapper.views;

import br.com.techie.shoppingstore.AP003.dto.view.ProductVIEW;
import br.com.techie.shoppingstore.AP003.model.Product;
import br.com.techie.shoppingstore.AP003.model.ServerAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductViewMapper{
    @Autowired
    private ServerAttributeViewMapper serverAttributeViewMapper;

    public ProductVIEW map(Product i, ServerAttribute attributes) {
        return new ProductVIEW(
                i.getId(),
                i.getName(),
                i.getCategory().getName(),
                i.getPrice(),
                i.getDescription(),
                i.getStock(),
                serverAttributeViewMapper.map(attributes)
        );
    }
}
