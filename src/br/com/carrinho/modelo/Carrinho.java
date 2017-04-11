package br.com.carrinho.modelo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;

import br.com.carrinho.dao.CarrinhoDAO;
import br.com.carrinho.dao.ItemDAO;
import br.com.carrinho.dao.ProdutoDAO;

@Entity
public class Carrinho implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToMany(mappedBy = "carrinho")
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private Set<Item> itens;

	public void adicionaProduto(Produto produto, Integer quantidade) {
		Item item = new Item();
		item.setProduto(new ProdutoDAO().find(produto.getId()));
		item.setQuantidade(quantidade);
		item.setCarrinho(this);
		if (this.itens == null) {
			this.itens = new HashSet<Item>();
		}

		boolean produtoExiste = false;
		for (Iterator<Item> it = this.itens.iterator(); it.hasNext();) {
			Item next = it.next();
			if (next.getProduto().getId().equals(produto.getId())) {
				next.setQuantidade(item.getQuantidade());
				produtoExiste = true;
				break;
			}
		}
		if(!produtoExiste){
			this.itens.add(item);
		}
		
		if (this.id != null) {
			new CarrinhoDAO().merge(this);
		} else {
			new CarrinhoDAO().save(this);
		}
	}

	public void removeProduto(Produto produto) {
		produto = new ProdutoDAO().find(produto.getId());
		for (Iterator<Item> it = getItens().iterator(); it.hasNext();) {
			Item next = it.next();
			if (next.getProduto().getId().equals(produto.getId())) {
				next.setCarrinho(null);
				it.remove();
				break;
			}
		}
		if (getItens() != null && getItens().size() == 0) {
			this.itens = null;
		}
		new ItemDAO().removeItem(produto, this);
	}

	public double retornaTotal() {
		double total = 0.0;
		Set<Item> lista = getItens();
		if (lista != null) {
			for (Item item : lista) {
				total += (item.getProduto().getPreco() * item.getQuantidade());
			}
		}
		return total;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<Item> getItens() {
		if (itens == null) {
			itens = new ItemDAO().getItensByCarrinho(this);
		}
		return itens;
	}

	public void setItens(Set<Item> itens) {
		this.itens = itens;
	}
}
