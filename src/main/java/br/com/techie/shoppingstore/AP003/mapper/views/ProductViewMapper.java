package br.com.techie.shoppingstore.AP003.mapper.views;

import br.com.techie.shoppingstore.AP003.dto.view.ProductVIEW;
import br.com.techie.shoppingstore.AP003.mapper.Mapper;
import br.com.techie.shoppingstore.AP003.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductViewMapper implements Mapper<Product, ProductVIEW> {
    @Override
    public ProductVIEW map(Product i) {
        return new ProductVIEW(
                i.getId(),
                i.getName(),
                i.getCategory().getName(),
                i.getPrice(),
                i.getDescription(),
                i.getStock(),
                i.getChassis(),
                i.getCpu(),
                i.getOperationalSystem(),
                i.getChipset(),
                i.getMemory(),
                i.getSlots(),
                i.getStorage(),
                i.getNetwork()
        );
    }
}
