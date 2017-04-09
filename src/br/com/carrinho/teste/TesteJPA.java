package br.com.carrinho.teste;

import javax.persistence.EntityManager;

import br.com.carrinho.modelo.Produto;
import br.com.carrinho.util.JPAUtil;

public class TesteJPA {

	public static void main(String[] args) {

		double inicio = System.currentTimeMillis();

		Produto produto = new Produto();
		produto.setNome("Camisa de Futebol");
		produto.setPreco(299.0);
		produto.setDescricao("Camisa destinada a atividades esportivas");
		

		EntityManager manager = new JPAUtil().getEntityManager();

		manager.getTransaction().begin();

		manager.persist(produto);

		manager.getTransaction().commit();

		manager.close();

		double fim = System.currentTimeMillis();
		System.out.println("Executado em: " + (fim - inicio) / 1000 + "s");

	}
}
