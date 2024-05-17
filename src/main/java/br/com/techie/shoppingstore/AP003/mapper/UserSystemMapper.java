package br.com.techie.shoppingstore.AP003.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import br.com.techie.shoppingstore.AP003.dto.UserSystemCreateDto;
import br.com.techie.shoppingstore.AP003.dto.UserSystemResponseDto;
import br.com.techie.shoppingstore.AP003.model.UserSystem;

import java.util.List;
import java.util.stream.Collectors;

public class UserSystemMapper {

    public static UserSystem toUserSystem(UserSystemCreateDto createDto) {
        return new ModelMapper().map(createDto, UserSystem.class);
    }

    public static UserSystemResponseDto toDto(UserSystem usuario) {
        String role = usuario.getRole().name().substring("ROLE_".length());
        PropertyMap<UserSystem, UserSystemResponseDto> props = new PropertyMap<UserSystem, UserSystemResponseDto>() {
            @Override
            protected void configure() {
                map().setRole(role);
            }
        };
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);
        return mapper.map(usuario, UserSystemResponseDto.class);
    }

    public static List<UserSystemResponseDto> toListDto(List<UserSystem> usuarios) {
        return usuarios.stream().map(user -> toDto(user)).collect(Collectors.toList());
    }

}