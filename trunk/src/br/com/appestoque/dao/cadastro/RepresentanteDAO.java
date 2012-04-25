package br.com.appestoque.dao.cadastro;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import br.com.appestoque.TipoBusca;
import br.com.appestoque.dao.DAOException;
import br.com.appestoque.dao.DAOGenerico;
import br.com.appestoque.dao.faturamento.PedidoDAO;
import br.com.appestoque.dominio.cadastro.Bairro;
import br.com.appestoque.dominio.cadastro.Representante;

@SuppressWarnings("unchecked")
public class RepresentanteDAO extends DAOGenerico<Representante, Long>{

	public RepresentanteDAO(PersistenceManager pm) {
		this.setPm(pm);
	}

	public Representante pesquisar(String uuid, TipoBusca tipoBusca){
		Representante objeto = null;
		Query query = getPm().newQuery(Representante.class);
		query.setFilter("uuid == p_uuid");
		query.declareParameters("String p_uuid");
		List<Representante> objetos = (List<Representante>) query.execute(uuid);
		if(objetos.size()>0){
			objeto = objetos.get(0);
			if(tipoBusca.equals(TipoBusca.ANSIOSA)){
				BairroDAO bairroDAO = new BairroDAO(getPm());
				objeto.setBairro(bairroDAO.pesquisar(objeto.getIdBairro(),TipoBusca.ANSIOSA));
			}
		}
		return objeto;
	}
	
	public Representante pesquisar(Long id, TipoBusca tipoBusca){
		Representante objeto = null;
		Query query = getPm().newQuery(Representante.class);
		query.setFilter("id == p_id");
		query.declareParameters("Long p_id");
		objeto = (Representante) query.execute(id);
		if(tipoBusca.equals(TipoBusca.ANSIOSA)){
			BairroDAO bairroDAO = new BairroDAO(getPm());
			objeto.setBairro(bairroDAO.pesquisar(objeto.getIdBairro(),TipoBusca.ANSIOSA));
		}
		return objeto;
	}
	
	public List<Representante> pesquisar(String cpf, Long idEmpresa, long ini, long qtd, TipoBusca tipoBusca){
		Query query = getPm().newQuery(Representante.class);
		query.setRange(ini, qtd);
		List<Representante> objetos = null;
		if(cpf!=null){
			query.setFilter("cpf == p_cpf && idEmpresa == p_empresa ");
			query.declareParameters("String p_cpf , Long p_empresa");
		}else {
			query.setFilter("idEmpresa == p_empresa ");
			query.declareParameters("String p_empresa");
		}
		objetos = (List<Representante>)(cpf!=null?query.execute(cpf,idEmpresa):query.execute(idEmpresa));		
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
	
	public int contar(String cpf, Long idEmpresa ){
		Query query = getPm().newQuery(Representante.class);
		List<Representante> objetos = null;
		if(cpf!=null){
			query.setFilter("cpf == p_cpf && idEmpresa == p_empresa ");
			query.declareParameters("String p_cpf , Long p_empresa");
		}else {
			query.setFilter("idEmpresa == p_empresa ");
			query.declareParameters("String p_empresa");
		}
		objetos = (List<Representante>)(cpf!=null?query.execute(cpf,idEmpresa):query.execute(idEmpresa));
		return objetos.size();
	}
	
	public List<Representante> listar(Long idEmpresa){
		Query query = getPm().newQuery(Representante.class);
		query.setFilter("idEmpresa == p_empresa ");
		query.declareParameters("String p_empresa");
		return (List<Representante>) query.execute(idEmpresa);
	}
	
	public boolean pesquisar(Bairro bairro){
		Query query = getPm().newQuery(Representante.class);
		query.setFilter("idBairro == p_bairro ");
		query.declareParameters("Long p_bairro");
		List<Representante> representantes = (List<Representante>) query.execute(bairro.getId());
		return (representantes.size()>0);
	}
	
	public void excluir(Representante representante) throws DAOException {
		PedidoDAO pedidoDAO = new PedidoDAO(getPm());
		if (!pedidoDAO.pesquisar(representante)) {
			try {
				getPm().currentTransaction().begin();
				getPm().deletePersistent(representante);
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
	
}