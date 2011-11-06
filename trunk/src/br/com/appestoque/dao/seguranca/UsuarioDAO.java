package br.com.appestoque.dao.seguranca;

import javax.jdo.PersistenceManager;

import br.com.appestoque.dao.DAOGenerico;
import br.com.appestoque.dominio.seguranca.Usuario;

public class UsuarioDAO extends DAOGenerico<Usuario, Long> {
	
	public UsuarioDAO(PersistenceManager pm) {
		this.setPm(pm);
	}
	
}