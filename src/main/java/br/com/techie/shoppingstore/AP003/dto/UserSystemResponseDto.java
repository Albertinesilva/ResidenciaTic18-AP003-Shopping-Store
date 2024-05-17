package br.com.techie.shoppingstore.AP003.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserSystemResponseDto {

    private Long id;
    private String username;
    private String role;
}
