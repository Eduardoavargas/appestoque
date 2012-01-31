package br.com.appestoque.dao.cadastro;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import br.com.appestoque.dao.DAOGenerico;
import br.com.appestoque.dominio.cadastro.Cliente;

public class ClienteDAO extends DAOGenerico<Cliente, Long>{

	public ClienteDAO(PersistenceManager pm) {
		this.setPm(pm);
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> pesquisar(String numero, Long idEmpresa, long ini, long qtd){
		Query query = getPm().newQuery(Cliente.class);
		query.setRange(ini, qtd);
		List<Cliente> objetos = null;
		if(numero!=null){
			query.setFilter("numero == p_numero && idEmpresa == p_empresa ");
			query.declareParameters("String p_numero , Long p_empresa");
			objetos = (List<Cliente>) query.execute(numero,idEmpresa);
		}else {
			query.setFilter("idEmpresa == p_empresa ");
			query.declareParameters("String p_empresa");
			objetos = (List<Cliente>) query.execute(idEmpresa);
		}	
		return objetos;
	}
	
	@SuppressWarnings("unchecked")
	public int contar(String numero, Long idEmpresa ){
		Query query = getPm().newQuery(Cliente.class);
		List<Cliente> objetos = null;
		if(numero!=null){
			query.setFilter("numero == p_numero && idEmpresa == p_empresa ");
			query.declareParameters("String p_numero , Long p_empresa");
			objetos = (List<Cliente>) query.execute(numero,idEmpresa);
		}else {
			query.setFilter("idEmpresa == p_empresa ");
			query.declareParameters("String p_empresa");
			objetos = (List<Cliente>) query.execute(idEmpresa);
		}
		return objetos.size();
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> listar(Long idEmpresa){
		Query query = getPm().newQuery(Cliente.class);
		query.setFilter("idEmpresa == p_empresa ");
		query.declareParameters("String p_empresa");
		return (List<Cliente>) query.execute(idEmpresa);
	}
	
}