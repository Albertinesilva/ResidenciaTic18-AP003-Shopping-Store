package br.com.techie.shoppingstore.AP003.mapper.forms;

import br.com.techie.shoppingstore.AP003.dto.form.UserSystemFORM;
import br.com.techie.shoppingstore.AP003.enums.RoleEnum;
import br.com.techie.shoppingstore.AP003.mapper.Mapper;
import br.com.techie.shoppingstore.AP003.model.UserSystem;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserSystemFormMapper implements Mapper<UserSystemFORM, UserSystem> {
    @Override
    public UserSystem map(UserSystemFORM i) {
        return new UserSystem(
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
