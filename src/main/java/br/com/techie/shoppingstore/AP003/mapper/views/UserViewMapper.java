package br.com.techie.shoppingstore.AP003.mapper.views;

import br.com.techie.shoppingstore.AP003.dto.view.UserVIEW;
import br.com.techie.shoppingstore.AP003.mapper.Mapper;
import br.com.techie.shoppingstore.AP003.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserViewMapper implements Mapper<User, UserVIEW> {
    @Override
    public UserVIEW map(User i) {
        return new UserVIEW(
                i.getId(),
                i.getUsername(),
                i.getEmail()
        );
    }
}
