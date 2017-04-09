package br.com.carrinho.modelo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Carrinho {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "carrinho")
	private Set<Item> itens;

	private Double total;

	public void adicionaProduto(Produto produto, Integer quantidade) {
		Item item = new Item();
		item.setProduto(produto);
		item.setQuantidade(quantidade);
		item.setCarrinho(this);

		if (itens == null) {
			itens = new HashSet<Item>();
		}

		itens.add(item);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<Item> getItens() {
		return itens;
	}

	public void setItens(Set<Item> itens) {
		this.itens = itens;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
}
