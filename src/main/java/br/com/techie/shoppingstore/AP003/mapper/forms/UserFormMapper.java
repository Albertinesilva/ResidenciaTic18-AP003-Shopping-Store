package br.com.techie.shoppingstore.AP003.mapper.forms;

import br.com.techie.shoppingstore.AP003.dto.form.UserFORM;
import br.com.techie.shoppingstore.AP003.mapper.Mapper;
import br.com.techie.shoppingstore.AP003.model.User;

public class UserFormMapper implements Mapper<UserFORM, User> {
    @Override
    public User map(UserFORM i) {
        return new User(
                null,
                i.email(),
                i.username(),
                i.password(),
                i.passwordConfirm(),
                true
        );
    }
}
