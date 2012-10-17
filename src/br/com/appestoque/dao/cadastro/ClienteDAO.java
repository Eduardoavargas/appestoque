package br.com.appestoque.dao.cadastro;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import br.com.appestoque.TipoBusca;
import br.com.appestoque.dao.DAOException;
import br.com.appestoque.dao.DAOGenerico;
import br.com.appestoque.dao.faturamento.PedidoDAO;
import br.com.appestoque.dominio.cadastro.Bairro;
import br.com.appestoque.dominio.cadastro.Cliente;
import br.com.appestoque.dominio.cadastro.Empresa;

@SuppressWarnings("unchecked")
public class ClienteDAO extends DAOGenerico<Cliente, Long>{

	public ClienteDAO(PersistenceManager pm) {
		this.setPm(pm);
	}
	
	public Cliente pesquisar(Long id, TipoBusca tipoBusca){
		Cliente objeto = this.pesquisar(id);
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
				objetos.get(i).setBairro(bairroDAO.pesquisar(objetos.get(i).getIdBairro(),tipoBusca));
			}
		}
		return objetos;
	}
	
	public boolean pesquisar(Bairro bairro){
		Query query = getPm().newQuery(Cliente.class);
		query.setFilter("idBairro == p_bairro ");
		query.declareParameters("Long p_bairro");
		List<Cliente> clientes = (List<Cliente>) query.execute(bairro.getId());
		return (clientes.size()>0);
	}
	
	public void excluir(Cliente cliente) throws DAOException {
		PedidoDAO pedidoDAO = new PedidoDAO(getPm());
		if (!pedidoDAO.pesquisar(cliente)) {
			try {
				getPm().currentTransaction().begin();
				getPm().deletePersistent(cliente);
				getPm().currentTransaction().commit();
			} finally {
				if (getPm().currentTransaction().isActive()) {
					getPm().currentTransaction().rollback();
				}
			}
		} else {
			throw new DAOException(
					bundle.getString("representante.mensagem.pedido.vinculado"));
		}
	}

	public void excluir(Empresa empresa) {
		Query query = getPm().newQuery(Cliente.class);
		List<Cliente> objetos = null;
		query.setFilter("idEmpresa == p_empresa ");
		query.declareParameters("Long p_empresa");
		objetos = (List<Cliente>) query.execute(empresa.getId());
		getPm().deletePersistentAll(objetos);
	}
	
}