package br.com.carrinho.dao;

import org.hibernate.Hibernate;

import br.com.carrinho.modelo.Carrinho;

public class CarrinhoDAO extends GenericDAO<Carrinho> {

	public CarrinhoDAO(){
		super(Carrinho.class);
	}
	
	public Carrinho find(Carrinho clazz, Integer id) {
		Carrinho c = null;
		try {
			beginTransaction();
			c = manager.find(Carrinho.class, id);
			Hibernate.initialize(c.getItens());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTransaction();
		}
		return c;
	}
	
	public void delete(Carrinho c) {
		try {
			beginTransaction();
			c = manager.find(Carrinho.class, c.getId());
			c.getItens();
			manager.merge(c);
			manager.remove(c);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTransaction();
		}
	}
}
