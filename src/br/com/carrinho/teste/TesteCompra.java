package br.com.carrinho.teste;

import javax.persistence.EntityManager;

import br.com.carrinho.modelo.Carrinho;
import br.com.carrinho.modelo.Produto;
import br.com.carrinho.util.JPAUtil;

public class TesteCompra {
	public static void main(String[] args) {
		carregaProdutos();

		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();
		Carrinho carrinho = new Carrinho();

		carrinho.adicionaProduto(manager.find(Produto.class, 2), 3);
		manager.persist(carrinho);
		manager.getTransaction().commit();

		manager.close();
	}

	public static void carregaProdutos() {

		EntityManager manager = new JPAUtil().getEntityManager();

		manager.getTransaction().begin();

		for (int i = 1; i <= 10; i++) {
			Produto produto = new Produto();
			produto.setNome("Camisa nº " + i);
			produto.setPreco(299.0);
			produto.setDescricao("Camisa destinada a atividades esportivas");

			manager.persist(produto);
		}
		manager.getTransaction().commit();

		manager.close();

	}
	
}
