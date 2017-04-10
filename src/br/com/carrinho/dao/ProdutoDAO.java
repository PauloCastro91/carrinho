package br.com.carrinho.dao;

import br.com.carrinho.modelo.Produto;

public class ProdutoDAO extends GenericDAO<Produto> {
	public ProdutoDAO() {
		super(Produto.class);
	}
}
