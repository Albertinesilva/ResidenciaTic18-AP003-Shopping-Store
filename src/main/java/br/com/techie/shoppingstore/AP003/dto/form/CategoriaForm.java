package br.com.techie.shoppingstore.AP003.dto.form;

import java.io.Serializable;

import br.com.techie.shoppingstore.AP003.model.Categoria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaForm implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long id;
  private String nome;

  public CategoriaForm(Categoria entity) {
    this.id = entity.getId();
    this.nome = entity.getNome();
  }
}
