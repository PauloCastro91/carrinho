package br.com.carrinho.util;

import br.com.carrinho.dao.ProdutoDAO;
import br.com.carrinho.modelo.Produto;

public class PopulaBanco {
	
	public static void populaBanco() {
		ProdutoDAO produtoDAO = new ProdutoDAO();

		for (int i = 1; i <= 10; i++) {
			Produto produto = new Produto();
			produto.setNome("Camisa nº " + i);
			produto.setPreco(299.0);
			produto.setDescricao("Camisa destinada a atividades esportivas");

			produtoDAO.save(produto);
		}
	}

}
