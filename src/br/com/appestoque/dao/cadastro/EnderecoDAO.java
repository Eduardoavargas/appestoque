package br.com.appestoque.dao.cadastro;

import javax.jdo.PersistenceManager;

import br.com.appestoque.dao.DAOGenerico;
import br.com.appestoque.dominio.cadastro.Endereco;

public class EnderecoDAO extends DAOGenerico<Endereco, Long> {
	
	public EnderecoDAO(PersistenceManager pm) {
		this.setPm(pm);
	}
	
}