package br.com.carrinho.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.carrinho.util.JPAUtil;

public abstract class GenericDAO<T extends Serializable> {
	protected EntityManager manager;
	protected EntityTransaction t;
	protected Class classe;

	public GenericDAO(Class classe){
		this.classe = classe;
	}
	
	public void save(T object) {
		try {
			beginTransaction();
			manager.persist(object);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTransaction();
		}
	}

	public void merge(T object) {
		try {
			beginTransaction();
			manager.merge(object);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTransaction();
		}
	}

	public void delete(T object) {
		try {
			beginTransaction();
			manager.remove(manager.merge(object));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTransaction();
		}
	}

	public T find(Integer id) {
		T obj = null;
		try {
			beginTransaction();
			//obj = (T) manager.find(classe.getClass(), id);
			obj = (T) manager.find(classe, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTransaction();
		}
		return obj;
	}

	public List<T> findAll() {
		List<T> objects = null;
		try {
			beginTransaction();
			Query query = manager.createQuery("from " + classe.getName());
			objects = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTransaction();
		}
		return objects;
	}

	protected void beginTransaction() {
		manager = new JPAUtil().getEntityManager();
		t = manager.getTransaction();
		t.begin();
	}

	protected void closeTransaction() {
		if (t != null) {
			t.commit();
		}
		if (manager != null) {
			manager.close();
		}
	}

}
