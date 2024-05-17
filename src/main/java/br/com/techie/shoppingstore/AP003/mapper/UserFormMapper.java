package br.com.techie.shoppingstore.AP003.mapper;

import br.com.techie.shoppingstore.AP003.dto.form.UserFORM;
import br.com.techie.shoppingstore.AP003.model.Usuario;

public class UserFormMapper implements Mapper<UserFORM, Usuario> {
    @Override
    public Usuario map(UserFORM i) {
        return new Usuario(
                null,
                i.email(),
                null, // TODO Retirar Username da entidade
                i.password(),
                i.passwordConfirm(),
                true,
                null // TODO Retirar referência à entidade carrinho
        );
    }
}
