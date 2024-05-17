package br.com.techie.shoppingstore.AP003.mapper.views;

import br.com.techie.shoppingstore.AP003.mapper.Mapper;
import br.com.techie.shoppingstore.AP003.model.ServerAttribute;

public class ServerAttributeViewMapper implements Mapper<ServerAttribute, ServerAttributeFORM> {
    @Override
    public ServerAttributeFORM map(ServerAttribute i) {
        return new ServerAttributeFORM(
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
