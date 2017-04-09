package br.com.carrinho.teste;

import javax.persistence.EntityManager;

import br.com.carrinho.util.JPAUtil;

public class TesteEntityManager {
	
	public static void main(String[] args) {
		EntityManager manager = new JPAUtil().getEntityManager();
		System.out.println("Sucesso = " + (manager != null));
	}
}
