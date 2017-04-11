package br.com.carrinho.teste;

import br.com.carrinho.dao.CarrinhoDAO;
import br.com.carrinho.dao.ProdutoDAO;
import br.com.carrinho.modelo.Carrinho;
import br.com.carrinho.modelo.Produto;
import br.com.carrinho.util.PopulaBanco;

public class TesteCompra {
	public static void main(String[] args) {
		
		PopulaBanco.populaBanco();

		
		CarrinhoDAO carrinhoDAO = new CarrinhoDAO();
		ProdutoDAO produtoDAO = new ProdutoDAO();

		
		Carrinho carrinho = new Carrinho();
		Produto produto = produtoDAO.find(1);

		
		carrinho.adicionaProduto(produto, 2);
		System.out.println("1. " + carrinho.retornaTotal());
		
		carrinho = carrinhoDAO.find(1);

		carrinho.adicionaProduto(new Produto(2), 1);
		System.out.println("2. " + carrinho.retornaTotal());

		carrinho.removeProduto(new Produto(1));
		System.out.println("3. " + carrinho.retornaTotal());		

		carrinho.removeProduto(new Produto(2));
		System.out.println("4. " + carrinho.retornaTotal()); 
		
	}

}
