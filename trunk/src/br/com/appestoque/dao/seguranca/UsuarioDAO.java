package br.com.appestoque.dao.seguranca;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import br.com.appestoque.dao.DAOGenerico;
import br.com.appestoque.dominio.seguranca.Usuario;

public class UsuarioDAO extends DAOGenerico<Usuario, Long> {
	
	public UsuarioDAO(PersistenceManager pm) {
		this.setPm(pm);
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> pesquisar(String email){
		Query query = getPm().newQuery(Usuario.class);
		List<Usuario> usuarios = null;
		if(email!=null){
			query.setFilter("email == p_email");
			query.declareParameters("String p_email");
			usuarios = (List<Usuario>) query.execute(email);
		}else {
			usuarios = (List<Usuario>) query.execute();
		}	
		return usuarios;
	}
	
	@SuppressWarnings("rawtypes")
	public Usuario pesquisar(String email, String senha){
		Query query = getPm().newQuery(Usuario.class);
		query.setFilter("email == p_email && senha == p_senha");
		query.declareParameters("String p_email , String p_senha");
		List usuarios = (List) query.execute(email,senha);
		Usuario usuario = (Usuario) usuarios.get(0);
		return usuario;
	}
	
}