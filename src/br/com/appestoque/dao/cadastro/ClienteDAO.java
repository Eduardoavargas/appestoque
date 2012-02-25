package br.com.appestoque.dao.cadastro;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import br.com.appestoque.TipoBusca;
import br.com.appestoque.dao.DAOGenerico;
import br.com.appestoque.dominio.cadastro.Cliente;

@SuppressWarnings("unchecked")
public class ClienteDAO extends DAOGenerico<Cliente, Long>{

	public ClienteDAO(PersistenceManager pm) {
		this.setPm(pm);
	}
	
	public Cliente pesquisar(Long id, TipoBusca tipoBusca){
		Cliente objeto = null;
		Query query = getPm().newQuery(Cliente.class);
		query.setFilter("id == p_id");
		query.declareParameters("Long p_id");
		objeto = (Cliente) query.execute(id);
		if(tipoBusca.equals(TipoBusca.ANSIOSA)){
			BairroDAO bairroDAO = new BairroDAO(getPm());
			objeto.setBairro(bairroDAO.pesquisar(objeto.getIdBairro(),TipoBusca.ANSIOSA));
		}
		return objeto;
	}
	
	public List<Cliente> pesquisar(String cnpj, Long idEmpresa, long ini, long qtd, TipoBusca tipoBusca){
		Query query = getPm().newQuery(Cliente.class);
		query.setRange(ini, qtd);
		List<Cliente> objetos = null;
		if(cnpj!=null){
			query.setFilter("cnpj == p_cnpj && idEmpresa == p_empresa ");
			query.declareParameters("String p_cnpj , Long p_empresa");
			objetos = (List<Cliente>) query.execute(cnpj,idEmpresa);
		}else {
			query.setFilter("idEmpresa == p_empresa ");
			query.declareParameters("String p_empresa");
			objetos = (List<Cliente>) query.execute(idEmpresa);
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
	
	public int contar(String cnpj, Long idEmpresa){
		Query query = getPm().newQuery(Cliente.class);
		List<Cliente> objetos = null;
		if(cnpj!=null){
			query.setFilter("cnpj == p_cnpj && idEmpresa == p_empresa ");
			query.declareParameters("String p_cnpj , Long p_empresa");
			objetos = (List<Cliente>) query.execute(cnpj,idEmpresa);
		}else {
			query.setFilter("idEmpresa == p_empresa ");
			query.declareParameters("String p_empresa");
			objetos = (List<Cliente>) query.execute(idEmpresa);
		}
		return objetos.size();
	}
	
	public List<Cliente> listar(Long idEmpresa, TipoBusca tipoBusca){
		Query query = getPm().newQuery(Cliente.class);
		query.setFilter("idEmpresa == p_empresa ");
		query.declareParameters("String p_empresa");
		List<Cliente> objetos = (List<Cliente>) query.execute(idEmpresa);
		if(tipoBusca.equals(TipoBusca.ANSIOSA)&&objetos.size()>0){
			BairroDAO bairroDAO = new BairroDAO(this.getPm()); 
			for (int i = 0; i < objetos.size(); i++) {
				bairroDAO.pesquisar(objetos.get(i).getBairro().getId(),tipoBusca);
			}
		}
		return objetos;
	}
	
}