package br.com.appestoque.rest;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.appestoque.dao.PMF;
import br.com.appestoque.dominio.cadastro.Empresa;
import br.com.appestoque.dominio.seguranca.Usuario;

@SuppressWarnings("serial")
public class EmpresaSinc extends HttpServlet{
	
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Empresa empresa = new Empresa("Appestoque", "", null);
		pm.currentTransaction().begin();
		pm.makePersistent(empresa);
		pm.currentTransaction().commit();
		
		Query query = null; 
		query = pm.newQuery(Usuario.class);
		List<Usuario> usuarios = (List<Usuario>) query.execute();			
		for(Usuario usuario : usuarios){
			pm.currentTransaction().begin();
			usuario.setIdEmpresa(empresa.getId());
			pm.makePersistent(usuario);
			pm.currentTransaction().commit();
		}
		
	}

}
