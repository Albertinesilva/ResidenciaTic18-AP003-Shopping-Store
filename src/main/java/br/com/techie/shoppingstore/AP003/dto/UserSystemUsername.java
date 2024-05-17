package br.com.techie.shoppingstore.AP003.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
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
public class UserSystemUsername {

  @NotNull
  @Email(message = "Email format is invalid", regexp = "^[a-zA-Z0-9._%+-]+_?@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
  private String username;
}
