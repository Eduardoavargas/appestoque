package br.com.appestoque.dao.cadastro;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import br.com.appestoque.dao.DAOException;
import br.com.appestoque.dao.DAOGenerico;
import br.com.appestoque.dominio.cadastro.Cidade;
import br.com.appestoque.dominio.cadastro.Empresa;

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
			query.setFilter("nome == p_nome && idEmpresa == p_empresa ");
			query.declareParameters("String p_nome , Long p_empresa");
			objetos = (List<Cidade>) query.execute(nome,idEmpresa);
		}else {
			query.setFilter("idEmpresa == p_empresa ");
			query.declareParameters("String p_empresa");
			objetos = (List<Cidade>) query.execute(idEmpresa);
		}
		return objetos.size();
	}
	
	public void excluir(Cidade cidade) throws DAOException {
		BairroDAO bairroDAO = new BairroDAO(getPm());
		if(!bairroDAO.pesquisar(cidade)){
			try {
				getPm().currentTransaction().begin();
				getPm().deletePersistent(cidade);
				getPm().currentTransaction().commit();
			} finally {
				if (getPm().currentTransaction().isActive()) {
					getPm().currentTransaction().rollback();
				}
			}
		}else{
			throw new DAOException(bundle.getString("bairro.mensagem.cidade.vinculado"));
		}
	}
	
	@SuppressWarnings("unchecked")
	public void excluir(Empresa empresa) {
		Query query = getPm().newQuery(Cidade.class);
		List<Cidade> objetos = null;
		query.setFilter("idEmpresa == p_empresa ");
		query.declareParameters("Long p_empresa");
		objetos = (List<Cidade>) query.execute(empresa.getId());
		getPm().deletePersistentAll(objetos);
	}
	
	@SuppressWarnings("unchecked")
	public Cidade pesquisar(String nome, Empresa empresa){
		Query query = getPm().newQuery(Cidade.class);
		List<Cidade> objetos = null;
		if(nome!=null&&empresa!=null){
			query.setFilter("nome == p_nome && idEmpresa == p_empresa ");
			query.declareParameters("String p_nome , Long p_empresa");
			objetos = (List<Cidade>) query.execute(nome,empresa.getId());
		}	
		return objetos.size()>0?objetos.get(0):null;
	}
	
}