package br.com.techie.shoppingstore.AP003.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import br.com.techie.shoppingstore.AP003.dto.form.UserSystemFORM;
import br.com.techie.shoppingstore.AP003.dto.view.UserSystemVIEW;
import br.com.techie.shoppingstore.AP003.model.UserSystem;

import java.util.List;
import java.util.stream.Collectors;

public class UserSystemMapper {

  public static UserSystem toUserSystem(UserSystemFORM createDto) {
    return new ModelMapper().map(createDto, UserSystem.class);
  }

  public static UserSystemVIEW toDto(UserSystem usuario) {
    String role = usuario.getRole().name().substring("ROLE_".length());
    PropertyMap<UserSystem, UserSystemVIEW> props = new PropertyMap<UserSystem, UserSystemVIEW>() {
      @Override
      protected void configure() {
        map().setRole(role);
      }
    };
    ModelMapper mapper = new ModelMapper();
    mapper.addMappings(props);
    return mapper.map(usuario, UserSystemVIEW.class);
  }

  public static List<UserSystemVIEW> toListDto(List<UserSystem> usuarios) {
    return usuarios.stream().map(user -> toDto(user)).collect(Collectors.toList());
  }

}
