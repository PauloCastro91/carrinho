package br.com.carrinho.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.carrinho.modelo.Carrinho;
import br.com.carrinho.modelo.Item;
import br.com.carrinho.modelo.Produto;

public class ItemDAO extends GenericDAO<Item> {

	public ItemDAO(){
		super(Item.class);
	}
	
	public void removeItem(Produto produto, Carrinho carrinho) {
		try {
			beginTransaction();
			Item item = manager
					.createQuery(
							"select i from Item i where i.produto.id  = :produtoId and i.carrinho.id = :carrinhoId",
							Item.class)
					.setParameter("produtoId", produto.getId()).setParameter("carrinhoId", carrinho.getId())
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

	public Set<Item> getItensByCarrinho(Carrinho carrinho) {
		Set<Item> itens = null;
		try {
			beginTransaction();
			List<Item> resultado = manager
					.createQuery("select i from Item i where i.carrinho.id = :carrinhoId", Item.class)
					.setParameter("carrinhoId", carrinho.getId()).getResultList();

			if (resultado != null && !resultado.isEmpty()) {
				itens = new HashSet<>(resultado);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTransaction();
		}
		return itens;
	}
}
