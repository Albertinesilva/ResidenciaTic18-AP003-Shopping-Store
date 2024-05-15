package br.com.techie.shoppingstore.AP003.tests;

import br.com.techie.shoppingstore.AP003.dto.form.CategoriaDTO;
import br.com.techie.shoppingstore.AP003.model.Categoria;

public class Factory {
  
  public static Categoria createCategoria() {
		Categoria categoria = new Categoria(1L, "Servidores de Aplicação e Web");
		return categoria;		
	}
	
	public static CategoriaDTO createProductDTO() {
		Categoria categoria = createCategoria();
		return new CategoriaDTO(categoria);
	}
}
