package br.com.appestoque.dao.seguranca;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import br.com.appestoque.dao.DAOGenerico;
import br.com.appestoque.dominio.cadastro.Empresa;
import br.com.appestoque.dominio.seguranca.Usuario;

public class UsuarioDAO extends DAOGenerico<Usuario, Long> {
	
	public UsuarioDAO(PersistenceManager pm) {
		this.setPm(pm);
	}
	
	@SuppressWarnings("rawtypes")
	public boolean autenticar(String email, String senha){
		Query query = getPm().newQuery(Usuario.class);
		query.setFilter("email == p_email && senha == p_senha");
		query.declareParameters("String p_email , String p_senha");
		List usuarios = (List) query.execute(email,senha);
		Empresa empresa = null;
		Usuario usuario = null;
		if(usuarios!=null&&usuarios.size()>0){
			usuario = (Usuario) usuarios.get(0);
			Key key = KeyFactory.createKey(Empresa.class.getSimpleName(),usuario.getIdEmpresa());
			empresa = getPm().getObjectById(Empresa.class, key);
		}
		return (empresa!=null&&empresa.getAtivo());
	}
	
	@SuppressWarnings("rawtypes")
	public Usuario pesquisar(String email, String senha){
		Query query = getPm().newQuery(Usuario.class);
		query.setFilter("email == p_email && senha == p_senha");
		query.declareParameters("String p_email , String p_senha");
		List usuarios = (List) query.execute(email,senha);
		Usuario usuario = usuarios.size()>0?(Usuario) usuarios.get(0):null;
		return usuario;
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> pesquisar(String email, Long idEmpresa, long ini, long qtd){
		Query query = getPm().newQuery(Usuario.class);
		query.setRange(ini, qtd);
		List<Usuario> usuarios = null;
		if(email!=null){
			query.setFilter("email == p_email && idEmpresa == p_empresa");
			query.declareParameters("String p_email, Long p_empresa");
			usuarios = (List<Usuario>) query.execute(email,idEmpresa);
		}else {
			query.setFilter("idEmpresa == p_empresa");
			query.declareParameters("String p_empresa");
			usuarios = (List<Usuario>) query.execute(idEmpresa);
		}	
		return usuarios;
	}
	
	@SuppressWarnings("rawtypes")
	public Usuario pesquisar(String serial){
		Query query = getPm().newQuery(Usuario.class);
		query.setFilter("serial == p_serial ");
		query.declareParameters("String p_serial");
		List usuarios = (List) query.execute(serial);
		Usuario usuario = (Usuario) usuarios.get(0);
		return usuario;
	}
	
	@SuppressWarnings("unchecked")
	public int contar(String email, Long idEmpresa ){
		Query query = getPm().newQuery(Usuario.class);
		List<Usuario> usuarios = null;
		if(email!=null){
			query.setFilter("email == p_email && idEmpresa == p_empresa");
			query.declareParameters("String p_email, Long p_empresa");
			usuarios = (List<Usuario>) query.execute(email,idEmpresa);
		}else {
			query.setFilter("idEmpresa == p_empresa");
			query.declareParameters("String p_empresa");
			usuarios = (List<Usuario>) query.execute(idEmpresa);
		}
		return usuarios.size();
	}
	
}