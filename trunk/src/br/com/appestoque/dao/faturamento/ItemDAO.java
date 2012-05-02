package br.com.appestoque.dao.faturamento;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import br.com.appestoque.dao.DAOGenerico;
import br.com.appestoque.dao.suprimento.ProdutoDAO;
import br.com.appestoque.dominio.faturamento.Item;
import br.com.appestoque.dominio.faturamento.Pedido;
import br.com.appestoque.dominio.suprimento.Produto;

public class ItemDAO extends DAOGenerico<Item, Long>{
	
	public ItemDAO(PersistenceManager pm) {
		this.setPm(pm);
	}
	
	@SuppressWarnings("unchecked")
	public boolean pesquisar(Produto produto){
		Query query = getPm().newQuery(Item.class);
		query.setFilter("idProduto == p_produto ");
		query.declareParameters("Long p_produto");
		List<Produto> produtos = (List<Produto>) query.execute(produto.getId());
		return (produtos.size()>0);
	}

	@SuppressWarnings("unchecked")
	public List<Item> pesquisar(Pedido pedido) {
		Query query = getPm().newQuery(Item.class);
		List<Item> objetos = null;
		query.setFilter(" idPedido == p_pedido ");
		query.declareParameters("Long p_pedido");
		objetos = (List<Item>) query.execute(pedido.getId());
		if(objetos!=null){
			ProdutoDAO produtoDAO = new ProdutoDAO(getPm()); 
			for(Item item : objetos){
				if(item.getIdProduto()!=null){
					item.setProduto(produtoDAO.pesquisar(item.getIdProduto()));
				}
			}
		}
		return objetos;
	}
	
}