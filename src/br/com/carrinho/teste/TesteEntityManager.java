package br.com.carrinho.teste;

import javax.persistence.EntityManager;

import br.com.carrinho.util.JPAUtil;
import junit.framework.TestCase;

public class TesteEntityManager extends TestCase{
	
	public void testeConexao() {
		EntityManager manager = new JPAUtil().getEntityManager();
		assertNotNull(manager);
		System.out.println("Sucesso = " + (manager != null));
	}
}
