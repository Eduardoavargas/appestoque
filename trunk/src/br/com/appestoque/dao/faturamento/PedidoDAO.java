package br.com.appestoque.dao.faturamento;

import javax.jdo.PersistenceManager;

import br.com.appestoque.dao.DAOGenerico;
import br.com.appestoque.dominio.faturamento.Pedido;

public class PedidoDAO extends DAOGenerico<Pedido, Long>{
	
	public PedidoDAO(PersistenceManager pm) {
		this.setPm(pm);
	}

}
