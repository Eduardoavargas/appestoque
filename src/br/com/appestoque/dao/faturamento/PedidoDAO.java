package br.com.appestoque.dao.faturamento;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.json.JSONArray;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

import br.com.appestoque.TipoBusca;
import br.com.appestoque.dao.DAOGenerico;
import br.com.appestoque.dao.cadastro.ClienteDAO;
import br.com.appestoque.dao.cadastro.RepresentanteDAO;
import br.com.appestoque.dominio.cadastro.Cliente;
import br.com.appestoque.dominio.cadastro.Representante;
import br.com.appestoque.dominio.faturamento.Pedido;

public class PedidoDAO extends DAOGenerico<Pedido, Long>{
	
	public PedidoDAO(PersistenceManager pm) {
		this.setPm(pm);
	}
	
	public List<Pedido> pesquisar(Date inicio, Date fim){
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Pedido> pesquisar(String numero, Long idEmpresa, long ini, long qtd){
		Query query = getPm().newQuery(Pedido.class);
		query.setRange(ini, qtd);
		List<Pedido> objetos = null;
		if(numero!=null){
			query.setFilter("numero == p_numero && idEmpresa == p_empresa ");
			query.declareParameters("String p_numero , Long p_empresa");
			objetos = (List<Pedido>) query.execute(numero,idEmpresa);
		}else {
			query.setFilter("idEmpresa == p_empresa ");
			query.declareParameters("Long p_empresa");
			objetos = (List<Pedido>) query.execute(idEmpresa);
		}	
		return objetos;
	}
	
	@SuppressWarnings("unchecked")
	public int contar(String numero, Long idEmpresa ){
		Query query = getPm().newQuery(Pedido.class);
		List<Pedido> objetos = null;
		if(numero!=null){
			query.setFilter("numero == p_numero && idEmpresa == p_empresa ");
			query.declareParameters("String p_numero , Long p_empresa");
			objetos = (List<Pedido>) query.execute(numero,idEmpresa);
		}else {
			query.setFilter("idEmpresa == p_empresa ");
			query.declareParameters("Long p_empresa");
			objetos = (List<Pedido>) query.execute(idEmpresa);
		}
		return objetos.size();
	}
	
	@SuppressWarnings("unchecked")
	public boolean pesquisar(Cliente cliente){
		Query query = getPm().newQuery(Pedido.class);
		query.setFilter("idCliente == p_cliente ");
		query.declareParameters("Long p_cliente");
		List<Pedido> pedidos = (List<Pedido>) query.execute(cliente.getId());
		return (pedidos.size()>0);
	}

	@SuppressWarnings("unchecked")
	public boolean pesquisar(Representante representante){
		Query query = getPm().newQuery(Pedido.class);
		query.setFilter("idRepresentante == p_representante ");
		query.declareParameters("Long p_representante");
		List<Pedido> pedidos = (List<Pedido>) query.execute(representante.getId());
		return (pedidos.size()>0);
	}
	
	@SuppressWarnings("unchecked")
	public Pedido pesquisar(String uuid) {
		Query query = getPm().newQuery(Pedido.class);
		List<Pedido> objetos = null;
		query.setFilter("uuid == p_uuid ");
		query.declareParameters("String p_uuid");
		objetos = (List<Pedido>) query.execute(uuid);
		
		Pedido objeto = objetos.get(0);
		
		/*localizando cliente*/
		if(objeto.getIdCliente()!=null){
			ClienteDAO clientedao = new ClienteDAO(getPm());
			objeto.setCliente(clientedao.pesquisar(objeto.getIdCliente()));
		}
		
		/*localizando representante*/
		if(objeto.getIdRepresentante()!=null){
			RepresentanteDAO representanteDAO = new RepresentanteDAO(getPm());
			objeto.setRepresentante(representanteDAO.pesquisar(objeto.getIdRepresentante()));
		}
		
		/*localizando itens*/
		ItemDAO itemDAO = new ItemDAO(getPm());
		objeto.setItens(itemDAO.pesquisar(objeto)); 
		
		return objeto;
	}

	@SuppressWarnings("unchecked")
	public Pedido pesquisar(Long id , TipoBusca tipoBusca) {
		Query query = getPm().newQuery(Pedido.class);
		List<Pedido> objetos = null;
		query.setFilter("id == p_id ");
		query.declareParameters("Long p_id");
		objetos = (List<Pedido>) query.execute(id);
		
		Pedido objeto = objetos.get(0);
		
		/*localizando cliente*/
		if(objeto.getIdCliente()!=null&&tipoBusca.equals(TipoBusca.ANSIOSA)){
			ClienteDAO clientedao = new ClienteDAO(getPm());
			objeto.setCliente(clientedao.pesquisar(objeto.getIdCliente()));
		}
		
		/*localizando representante*/
		if(objeto.getIdRepresentante()!=null&&tipoBusca.equals(TipoBusca.ANSIOSA)){
			RepresentanteDAO representanteDAO = new RepresentanteDAO(getPm());
			objeto.setRepresentante(representanteDAO.pesquisar(objeto.getIdRepresentante()));
		}
		
		/*localizando itens*/
		ItemDAO itemDAO = new ItemDAO(getPm());
		objeto.setItens(itemDAO.pesquisar(objeto)); 
		
		return objeto;
	}

	
	
}