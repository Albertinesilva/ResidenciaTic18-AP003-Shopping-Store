package br.com.techie.shoppingstore.AP003.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserSystemAlterarSenhaDto {

    private String newPassword;

    private String confirmPassword;

    private String codeverifier;
}