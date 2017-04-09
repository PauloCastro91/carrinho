package br.com.carrinho.dao;

import br.com.carrinho.modelo.Carrinho;
import br.com.carrinho.modelo.Item;
import br.com.carrinho.modelo.Produto;

public class ItemDAO extends GenericDAO<Item> {
	public void removeItem(Produto produto, Carrinho carrinho) {
		try {
			beginTransaction();
			Item item = manager
					.createQuery("select i from Item i where i.produto.id  = :produtoId and i.carrinho.id = :carrinhoId", Item.class)
					.setParameter("produtoId", produto.getId())
					.setParameter("carrinhoId", carrinho.getId())
					.getSingleResult();
			if (item != null && item.getId() != null && item.getId() > 0) {
				manager.remove(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTransaction();
		}
	}
}
