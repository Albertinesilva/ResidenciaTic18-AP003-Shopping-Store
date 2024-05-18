package br.com.techie.shoppingstore.AP003.mapper.forms;

import br.com.techie.shoppingstore.AP003.dto.form.UserFORM;
import br.com.techie.shoppingstore.AP003.enums.RoleEnum;
import br.com.techie.shoppingstore.AP003.mapper.Mapper;
import br.com.techie.shoppingstore.AP003.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserFormMapper implements Mapper<UserFORM, User> {
    @Override
    public User map(UserFORM i) {
        return new User(
                null,
                i.email(),
                i.username(),
                i.password(),
                i.passwordConfirm(),
                true,
                RoleEnum.ROLE_CLIENT,
                LocalDateTime.now(),
                LocalDateTime.now(),
                i.username(),
                i.username(),
                null
        );
    }
}
