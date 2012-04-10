package br.com.appestoque.dao.cadastro;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import br.com.appestoque.TipoBusca;
import br.com.appestoque.dao.DAOGenerico;
import br.com.appestoque.dominio.cadastro.Empresa;

@SuppressWarnings("unchecked")
public class EmpresaDAO extends DAOGenerico<Empresa, Long> {
	
	public EmpresaDAO(PersistenceManager pm) {
		this.setPm(pm);
	}
	
	public Empresa pesquisar(String uuid){
		Query query = getPm().newQuery(Empresa.class);
		query.setFilter("uuid == p_uuid");
		query.declareParameters("String p_uuid");
		List<Empresa> objetos = (List<Empresa>) query.execute(uuid);
		return objetos.get(0);
	}
	
	public Empresa pesquisar(Long id, TipoBusca tipoBusca){
		Empresa objeto = null;
		Query query = getPm().newQuery(Empresa.class);
		query.setFilter("id == p_id");
		query.declareParameters("Long p_id");
		objeto = (Empresa) query.execute(id);
		return objeto;
	}
	
	public List<Empresa> pesquisar(String cnpj, long ini, long qtd, TipoBusca tipoBusca){
		Query query = getPm().newQuery(Empresa.class);
		query.setRange(ini, qtd);
		List<Empresa> objetos = null;
		if(cnpj!=null){
			query.setFilter("cnpj == p_cnpj ");
			query.declareParameters("String p_cnpj");
		}
		objetos = cnpj!=null?(List<Empresa>) query.execute(cnpj):(List<Empresa>) query.execute();
		return objetos;
	}
	
	public int contar(String cnpj){
		Query query = getPm().newQuery(Empresa.class);
		List<Empresa> objetos = null;
		if(cnpj!=null){
			query.setFilter("cnpj == p_cnpj");
			query.declareParameters("String p_cnpj");
		}
		objetos = cnpj!=null?(List<Empresa>) query.execute(cnpj):(List<Empresa>) query.execute();
		return objetos.size();
	}
	
	public List<Empresa> listar(){
		Query query = getPm().newQuery(Empresa.class);
		return (List<Empresa>) query.execute();
	}
	
	public boolean pesquisarCNPJ(String cnpj){
		Query query = getPm().newQuery(Empresa.class);
		query.setFilter("cnpj == p_cnpj");
		query.declareParameters("String p_cnpj");
		List<Empresa> objetos = (List<Empresa>) query.execute(cnpj);
		return (objetos!=null&&objetos.size()>0);
	}

	public boolean pesquisarEmail(String email){
		Query query = getPm().newQuery(Empresa.class);
		query.setFilter("email == p_email");
		query.declareParameters("String p_email");
		List<Empresa> objetos = (List<Empresa>) query.execute(email);
		return (objetos!=null&&objetos.size()>0);
	}
	
}