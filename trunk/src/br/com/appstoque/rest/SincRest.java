package br.com.appstoque.rest;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.appestoque.dao.PMF;
import br.com.appestoque.dominio.seguranca.Usuario;
import br.com.appestoque.dominio.suprimento.Produto;

@SuppressWarnings("serial")
public class SincRest  extends HttpServlet{

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
			Query query = null; 
			query = pm.newQuery(Usuario.class);
			List<Usuario> usuarios = (List<Usuario>) query.execute();			
			for(Usuario usuario : usuarios){
				pm.currentTransaction().begin();
				usuario.setIdEmpresa(28L);
				pm.makePersistent(usuario);
				pm.currentTransaction().commit();
			}
			query = pm.newQuery(Produto.class);
			List<Produto> produtos = (List<Produto>) query.execute();			
			for(Produto produto : produtos){
				pm.currentTransaction().begin();
				produto.setIdEmpresa(28L);
				pm.makePersistent(produto);
				pm.currentTransaction().commit();
			}
			
			
			if (pm.currentTransaction().isActive()) {
				pm.currentTransaction().rollback();
			}
			
		
//		Usuario usuario = new Usuario("André Tricano","appestoque@gmail.com","1234",1L);
//		try {
//			pm.currentTransaction().begin();
//			pm.makePersistent(usuario);
//			pm.currentTransaction().commit();
//		} finally {
//			if (pm.currentTransaction().isActive()) {
//				pm.currentTransaction().rollback();
//			}
//		}
	}
	
}