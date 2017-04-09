package br.com.carrinho.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("carrinho");

	public EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

}
