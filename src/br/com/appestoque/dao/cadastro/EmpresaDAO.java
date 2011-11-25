package br.com.appestoque.dao.cadastro;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import br.com.appestoque.dao.DAOGenerico;
import br.com.appestoque.dominio.cadastro.Empresa;

public class EmpresaDAO extends DAOGenerico<Empresa, Long> {
	
	public EmpresaDAO(PersistenceManager pm) {
		this.setPm(pm);
	}
	
	@SuppressWarnings("unchecked")
	public List<Empresa> pesquisar(String cnpj, long ini, long qtd){
		Query query = getPm().newQuery(Empresa.class);
		query.setRange(ini, qtd);
		List<Empresa> empresas = null;
		if(cnpj!=null){
			query.setFilter("cnpj == p_cnpj");
			query.declareParameters("String p_cnpj");
			empresas = (List<Empresa>) query.execute(cnpj);
		}else {
			empresas = (List<Empresa>) query.execute();
		}	
		return empresas;
	}
	
	@SuppressWarnings("unchecked")
	public int contar(String cnpj){
		Query query = getPm().newQuery(Empresa.class);
		List<Empresa> empresas = null;
		if(cnpj!=null){
			query.setFilter("cnpj == p_cnpj");
			query.declareParameters("String p_cnpj");
			empresas = (List<Empresa>) query.execute(cnpj);
		}else{
			empresas = (List<Empresa>) query.execute();
		}
		return empresas.size();
	}
	
}