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
			PopulaBanco.populaBancoDeDados();
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

		Carrinho carrinho = carrinhoDAO.find(1);
		carrinho.adicionaProduto(new Produto(2), 1);
		Set<Item> itens = carrinho.getItens();
		assertTrue(itens != null && itens.size() == 1);
	}

	public void testRemoveProdutoCarrinho() throws Exception {
		System.out.println("Executadando testRemoveProdutoCarrinho()");

		Carrinho carrinho = carrinhoDAO.find(1);
		carrinho.removeProduto(new Produto(2));
		Set<Item> itens = carrinho.getItens();
		assertTrue(itens == null || itens.size() == 0);
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
}
