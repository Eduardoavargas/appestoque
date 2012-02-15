package br.com.appestoque.dao.cadastro;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import br.com.appestoque.dao.DAOGenerico;
import br.com.appestoque.dominio.cadastro.Cidade;

public class CidadeDAO extends DAOGenerico<Cidade, Long>{

	public CidadeDAO(PersistenceManager pm) {
		this.setPm(pm);
	}
	
	@SuppressWarnings("unchecked")
	public List<Cidade> pesquisar(String nome, Long idEmpresa, long ini, long qtd){
		Query query = getPm().newQuery(Cidade.class);
		query.setRange(ini, qtd);
		List<Cidade> objetos = null;
		if(nome!=null){
			query.setFilter("nome == p_nome && idEmpresa == p_empresa ");
			query.declareParameters("String p_nome , Long p_empresa");
			objetos = (List<Cidade>) query.execute(nome,idEmpresa);
		}else {
			query.setFilter("idEmpresa == p_empresa ");
			query.declareParameters("String p_empresa");
			objetos = (List<Cidade>) query.execute(idEmpresa);
		}	
		return objetos;
	}
	
	@SuppressWarnings("unchecked")
	public int contar(String nome, Long idEmpresa ){
		Query query = getPm().newQuery(Cidade.class);
		List<Cidade> objetos = null;
		if(nome!=null){
			query.setFilter("cnpj == p_nome && idEmpresa == p_empresa ");
			query.declareParameters("String p_nome , Long p_empresa");
			objetos = (List<Cidade>) query.execute(nome,idEmpresa);
		}else {
			query.setFilter("idEmpresa == p_empresa ");
			query.declareParameters("String p_empresa");
			objetos = (List<Cidade>) query.execute(idEmpresa);
		}
		return objetos.size();
	}
	
}