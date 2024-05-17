package br.com.techie.shoppingstore.AP003.dto.view;

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
public class TokenVIEW {

  private Long id;
  private String token;
  private UserSystemVIEW userSystem;
}
