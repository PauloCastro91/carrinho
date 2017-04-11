package br.com.carrinho.teste;

import java.util.List;
import java.util.Set;

import br.com.carrinho.dao.CarrinhoDAO;
import br.com.carrinho.dao.ProdutoDAO;
import br.com.carrinho.modelo.Carrinho;
import br.com.carrinho.modelo.Item;
import br.com.carrinho.modelo.Produto;
import br.com.carrinho.util.PopulaBanco;
import junit.framework.TestCase;

public class CompraTest extends TestCase {

	private CarrinhoDAO carrinhoDAO = new CarrinhoDAO();
	private ProdutoDAO produtoDao = new ProdutoDAO();

	@Override
	public void setUp() {
		System.out.println("Executadando setUp()");

		List<Produto> all = produtoDao.findAll();
		if (all == null || all.isEmpty()) {
			PopulaBanco.populaBanco();
		}
	}

	public void testAdicionaCarrinho() throws Exception {
		System.out.println("Executadando testAdicionaCarrinho()");

		Carrinho carrinho = new Carrinho();
		carrinhoDAO.save(carrinho);
		assertNotNull(carrinho.getId());
	}

	public void testAdionaProdutoCarrinho() throws Exception {
		System.out.println("Executadando testAdionaProdutoCarrinho()");

		Carrinho carrinho = getCarrinho();
		if (carrinho != null && carrinho.getId() != null) {
			carrinho.adicionaProduto(getProduto(), 1);
			Set<Item> itens = carrinho.getItens();
			assertTrue(itens != null && itens.size() == 1);
		}
	}

	public void testRemoveProdutoCarrinho() throws Exception {
		System.out.println("Executadando testRemoveProdutoCarrinho()");

		Carrinho carrinho = getCarrinho();
		if (carrinho != null && carrinho.getId() != null) {
			carrinho.adicionaProduto(getProduto(), 1);
			carrinho.removeProduto(getProduto());
			Set<Item> itens = carrinho.getItens();
			assertTrue(itens == null || itens.size() == 0);
		} 
	}

	public void testRemoverCarrinho() {
		System.out.println("Executadando testRemoveProdutoCarrinho()");

		List<Carrinho> all = carrinhoDAO.findAll();

		if (all != null && !all.isEmpty()) {
			Carrinho c = all.get(0);
			Integer id = c.getId();
			carrinhoDAO.delete(c);

			c = carrinhoDAO.find(id);

			assertTrue(c == null || c.getId() == null);
		}
	}

	public void testeAtualizaValor() {
		System.out.println("Executadando testeAtualizaValor()");

		Carrinho carrinho = getCarrinho();
		Produto produto = getProduto();
		Integer id = produto.getId();
		
		carrinho.adicionaProduto(produto, 1);
		
		Set<Item> itens = carrinho.getItens();
		
		if (itens != null && !itens.isEmpty()) {
			for (Item i : itens) {
				if (i.getProduto().getId().equals(id)) {
					assertTrue(i.getQuantidade().equals(1));
					break;
				}
			}
		} 
		
		carrinho.adicionaProduto(produto, 2);
		
		itens = carrinho.getItens();
		
		if (itens != null && !itens.isEmpty()) {
			for (Item i : itens) {
				if (i.getProduto().getId().equals(id)) {
					assertTrue(i.getQuantidade().equals(2));
					break;
				}
			}
		}
	}

	public Carrinho getCarrinho() {
		Carrinho carrinho = new Carrinho();
		carrinhoDAO.save(carrinho);
		return carrinho;
	}

	public Produto getProduto() {
		Produto produto = new Produto();
		List<Produto> lista = produtoDao.findAll();
		if (lista != null && !lista.isEmpty()) {
			produto = lista.get(0);
		}
		return produto;
	}

}
