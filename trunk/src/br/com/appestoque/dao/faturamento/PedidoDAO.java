package br.com.appestoque.dao.faturamento;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import br.com.appestoque.dao.DAOGenerico;
import br.com.appestoque.dominio.cadastro.Cliente;
import br.com.appestoque.dominio.cadastro.Representante;
import br.com.appestoque.dominio.faturamento.Pedido;

public class PedidoDAO extends DAOGenerico<Pedido, Long>{
	
	public PedidoDAO(PersistenceManager pm) {
		this.setPm(pm);
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
	
}