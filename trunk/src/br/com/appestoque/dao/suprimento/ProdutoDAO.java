package br.com.appestoque.dao.suprimento;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import br.com.appestoque.dao.DAOGenerico;
import br.com.appestoque.dominio.suprimento.Produto;

public class ProdutoDAO extends DAOGenerico<Produto, Long> {

	public ProdutoDAO(PersistenceManager pm) {
		this.setPm(pm);
	}

	@SuppressWarnings("unchecked")
	public List<Produto> pesquisar(String numero, long ini, long qtd){
		Query query = getPm().newQuery(Produto.class);
		query.setRange(ini, qtd);
		List<Produto> produtos = null;
		if(numero!=null){
			query.setFilter("numero == p_numero");
			query.declareParameters("String p_numero");
			produtos = (List<Produto>) query.execute(numero);
		}else {
			produtos = (List<Produto>) query.execute();
		}	
		return produtos;
	}
	
	@SuppressWarnings("unchecked")
	public int contar(String numero){
		Query query = getPm().newQuery(Produto.class);
		List<Produto> produtos = null;
		if(numero!=null){
			query.setFilter("email == p_email");
			query.declareParameters("String p_email");
			produtos = (List<Produto>) query.execute(numero);
		}else{
			produtos = (List<Produto>) query.execute();
		}
		return produtos.size();
	}
	
}