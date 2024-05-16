package br.com.techie.shoppingstore.AP003.dto;

import br.com.techie.shoppingstore.AP003.model.Produto;

public record AtributosServidorDTO(String chassi, String processador, String sistema_operacional, String chipset,
                                   String memoria, String slots, String armazenamento, String rede, Produto produto) {
}
