package br.com.techie.shoppingstore.AP003.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserSystemCreateDto {

    @NotNull
    @Email(message = "Formato do e-mail está inválido", regexp = "^[a-zA-Z0-9._%+-]+_?@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    private String username;

    private String password;

}
