package br.com.techie.shoppingstore.AP003.dto.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserSystemSenhaFORM {

  @NotNull
  @NotBlank(message = "Senha atual é obrigatória")
  private String currentpassword;

  @NotNull
  @NotBlank(message = "Nova senha é obrigatória")
  private String newPassword;

  @NotNull
  @NotBlank(message = "Confirmação de senha é obrigatória")
  private String confirmPassword;
}
