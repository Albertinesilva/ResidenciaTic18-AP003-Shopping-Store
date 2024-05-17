package br.com.techie.shoppingstore.AP003.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserSystemSenhaDto {

    private String currentpassword;

    private String newPassword;

    private String confirmPassword;

}
