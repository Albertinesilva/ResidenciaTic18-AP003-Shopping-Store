package br.com.techie.shoppingstore.AP003.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import br.com.techie.shoppingstore.AP003.dto.TokenResponseDto;
import br.com.techie.shoppingstore.AP003.model.Token;

public class TokenMapper {

    public static TokenResponseDto toDto(Token token) {
        PropertyMap<Token, TokenResponseDto> props = new PropertyMap<Token, TokenResponseDto>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setToken(source.getToken());
            }
        };
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);
        return mapper.map(token, TokenResponseDto.class);
    }

}
