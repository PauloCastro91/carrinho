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

import br.com.carrinho.dao.ItemDAO;

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
		item.setProduto(produto);
		item.setQuantidade(quantidade);
		item.setCarrinho(this);

		if (this.itens == null) {
			this.itens = new HashSet<Item>();
		}

		this.itens.add(item);
	}
	
	public void removeProduto(Produto produto){
		if (this.itens != null) {
			for(Iterator<Item> it = this.itens.iterator(); it.hasNext();){
				Item next = it.next();
				if(next.getProduto().getId().equals(produto.getId())){
					next.setCarrinho(null);
					it.remove();
					break;
				}
			}
		}
		new ItemDAO().removeItem(produto, this);
	}

	public double retornaTotal() {
		double total = 0.0;
		if (this.itens != null) {
			for (Item item : this.itens) {
				total += (item.getProduto().getPreco() * item.getQuantidade());
			}
		}
		System.out.println("****************************************");
		System.out.println("Total: " + total);
		System.out.println("****************************************");
		return total;
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
}
