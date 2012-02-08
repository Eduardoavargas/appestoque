package br.com.appestoque.dao.cadastro;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import br.com.appestoque.dao.DAOGenerico;
import br.com.appestoque.dominio.cadastro.Bairro;

public class BairroDAO extends DAOGenerico<Bairro, Long>{

	public BairroDAO(PersistenceManager pm) {
		this.setPm(pm);
	}
	
	@SuppressWarnings("unchecked")
	public List<Bairro> pesquisar(String nome, Long idEmpresa, long ini, long qtd){
		Query query = getPm().newQuery(Bairro.class);
		query.setRange(ini, qtd);
		List<Bairro> objetos = null;
		if(nome!=null){
			query.setFilter("nome == p_nome && idEmpresa == p_empresa ");
			query.declareParameters("String p_nome , Long p_empresa");
			objetos = (List<Bairro>) query.execute(nome,idEmpresa);
		}else {
			query.setFilter("idEmpresa == p_empresa ");
			query.declareParameters("String p_empresa");
			objetos = (List<Bairro>) query.execute(idEmpresa);
		}
		
		CidadeDAO cidadeDAO = new CidadeDAO(this.getPm());		
		for (int i = 0; i < objetos.size(); i++) {
			objetos.get(i).setCidade(cidadeDAO.pesquisar(objetos.get(i).getIdCidade()));
		}
		
		return objetos;
	}
	
	@SuppressWarnings("unchecked")
	public int contar(String nome, Long idEmpresa ){
		Query query = getPm().newQuery(Bairro.class);
		List<Bairro> objetos = null;
		if(nome!=null){
			query.setFilter("cnpj == p_nome && idEmpresa == p_empresa ");
			query.declareParameters("String p_nome , Long p_empresa");
			objetos = (List<Bairro>) query.execute(nome,idEmpresa);
		}else {
			query.setFilter("idEmpresa == p_empresa ");
			query.declareParameters("String p_empresa");
			objetos = (List<Bairro>) query.execute(idEmpresa);
		}
		return objetos.size();
	}
	
}