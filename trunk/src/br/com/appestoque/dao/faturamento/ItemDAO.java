package br.com.appestoque.dao.faturamento;

import javax.jdo.PersistenceManager;

import br.com.appestoque.dao.DAOGenerico;
import br.com.appestoque.dominio.faturamento.Item;

public class ItemDAO extends DAOGenerico<Item, Long>{
	
	public ItemDAO(PersistenceManager pm) {
		this.setPm(pm);
	}
	
}