package br.com.carrinho.teste;

import br.com.carrinho.dao.CarrinhoDAO;
import br.com.carrinho.dao.ProdutoDAO;
import br.com.carrinho.modelo.Carrinho;
import br.com.carrinho.modelo.Produto;

public class TesteCompra {
	public static void main(String[] args) {
		carregaProdutos();

		CarrinhoDAO carrinhoDAO = new CarrinhoDAO();
		ProdutoDAO produtoDAO = new ProdutoDAO();

		Carrinho carrinho = new Carrinho();
		Produto produto = produtoDAO.find(new Produto(), 1);

		carrinho.adicionaProduto(produto, 2);
		carrinhoDAO.save(carrinho);
		carrinho.retornaTotal();
		
		carrinho = carrinhoDAO.find(new Carrinho(), 1);

		produto = produtoDAO.find(new Produto(), 2);
		carrinho.adicionaProduto(produto, 1);
		carrinhoDAO.merge(carrinho);
		carrinho.retornaTotal();

		produto = produtoDAO.find(new Produto(), 1);
		carrinho.removeProduto(produto);
		carrinho.retornaTotal();
		

		produto = produtoDAO.find(new Produto(), 2);
		carrinho.removeProduto(produto);
		carrinho.retornaTotal();
		
	}

	public static void carregaProdutos() {
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
