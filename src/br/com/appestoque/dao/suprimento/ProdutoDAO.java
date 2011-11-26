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
	public List<Produto> pesquisar(String numero, Long idEmpresa, long ini, long qtd){
		Query query = getPm().newQuery(Produto.class);
		query.setRange(ini, qtd);
		List<Produto> produtos = null;
		if(numero!=null){
			query.setFilter("numero == p_numero && idEmpresa == p_empresa ");
			query.declareParameters("String p_numero");
			query.declareParameters("String p_empresa");
			produtos = (List<Produto>) query.execute(numero,idEmpresa);
		}else {
			query.setFilter("idEmpresa == p_empresa ");
			query.declareParameters("String p_empresa");
			produtos = (List<Produto>) query.execute(idEmpresa);
		}	
		return produtos;
	}
	
	@SuppressWarnings("unchecked")
	public int contar(String numero, Long idEmpresa ){
		Query query = getPm().newQuery(Produto.class);
		List<Produto> produtos = null;
		if(numero!=null){
			query.setFilter("numero == p_numero && idEmpresa == p_empresa ");
			query.declareParameters("String p_numero");
			query.declareParameters("String p_empresa");
			produtos = (List<Produto>) query.execute(numero,idEmpresa);
		}else {
			query.setFilter("idEmpresa == p_empresa ");
			query.declareParameters("String p_empresa");
			produtos = (List<Produto>) query.execute(idEmpresa);
		}
		return produtos.size();
	}
	
}