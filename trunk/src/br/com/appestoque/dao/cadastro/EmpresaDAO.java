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
	
	public Empresa pesquisar(Long id, TipoBusca tipoBusca){
		Empresa objeto = null;
		Query query = getPm().newQuery(Empresa.class);
		query.setFilter("id == p_id");
		query.declareParameters("Long p_id");
		objeto = (Empresa) query.execute(id);
		if(tipoBusca.equals(TipoBusca.ANSIOSA)){
			BairroDAO bairroDAO = new BairroDAO(getPm());
			objeto.setBairro(bairroDAO.pesquisar(objeto.getIdBairro(),TipoBusca.ANSIOSA));
		}
		return objeto;
	}
	
	public List<Empresa> pesquisar(String cnpj, Long idEmpresa, long ini, long qtd, TipoBusca tipoBusca){
		Query query = getPm().newQuery(Empresa.class);
		query.setRange(ini, qtd);
		List<Empresa> objetos = null;
		if(cnpj!=null){
			query.setFilter("cnpj == p_cnpj && idEmpresa == p_empresa ");
			query.declareParameters("String p_cnpj , Long p_empresa");
			objetos = (List<Empresa>) query.execute(cnpj,idEmpresa);
		}else {
			query.setFilter("idEmpresa == p_empresa ");
			query.declareParameters("String p_empresa");
			objetos = (List<Empresa>) query.execute(idEmpresa);
		}
		
		if (tipoBusca.equals(TipoBusca.ANSIOSA)) {
			BairroDAO bairroDAO = new BairroDAO(this.getPm());
			for (int i = 0; i < objetos.size(); i++) {
				if(objetos.get(i).getIdBairro()!=null){
					objetos.get(i).setBairro(bairroDAO.pesquisar(objetos.get(i).getIdBairro()));
				}
			}
		}
		
		return objetos;
	}
	
	public int contar(String cnpj, Long idEmpresa ){
		Query query = getPm().newQuery(Empresa.class);
		List<Empresa> objetos = null;
		if(cnpj!=null){
			query.setFilter("cnpj == p_cnpj && idEmpresa == p_empresa ");
			query.declareParameters("String p_cnpj , Long p_empresa");
			objetos = (List<Empresa>) query.execute(cnpj,idEmpresa);
		}else {
			query.setFilter("idEmpresa == p_empresa ");
			query.declareParameters("String p_empresa");
			objetos = (List<Empresa>) query.execute(idEmpresa);
		}
		return objetos.size();
	}
	
	public List<Empresa> listar(Long idEmpresa){
		Query query = getPm().newQuery(Empresa.class);
		query.setFilter("idEmpresa == p_empresa ");
		query.declareParameters("String p_empresa");
		return (List<Empresa>) query.execute(idEmpresa);
	}
	
}