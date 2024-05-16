package br.com.techie.shoppingstore.AP003.dto.form;

import lombok.Data;

import java.io.Serializable;

import br.com.techie.shoppingstore.AP003.model.AtributosServidor;
import br.com.techie.shoppingstore.AP003.model.Categoria;
import br.com.techie.shoppingstore.AP003.model.ItemCarrinho;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoForm implements Serializable{
  private static final long serialVersionUID = 1L;

  private Long id;
  private String nome;
  private Long preco;
  private String descricao;
  private int qtd_estoque;
  private String url_imagem;
  private AtributosServidor atributosServidor;
  private ItemCarrinho itemCarrinho;
  private Categoria categoria;
}
