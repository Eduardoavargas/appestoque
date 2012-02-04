package br.com.appestoque.rest;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.appestoque.dao.PMF;
import br.com.appestoque.dominio.cadastro.Empresa;
import br.com.appestoque.dominio.cadastro.Endereco;
import br.com.appestoque.dominio.seguranca.Usuario;

@SuppressWarnings("serial")
public class CargaInicial extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			/*empresa*/
			Endereco endereco = new Endereco("Califórnia", "Mountain View", 1600, "94043", "");
			Empresa empresa = new Empresa("Google Inc.", "", endereco);
			pm.currentTransaction().begin();
			pm.makePersistent(empresa);
			pm.currentTransaction().commit();
			/*usuário*/
			Usuario usuario = new Usuario("Sergey Brin","sergeybrin@google.com","sg1","200141a5bb0c7345",empresa.getId());			
			pm.currentTransaction().begin();
			pm.makePersistent(usuario);
			pm.currentTransaction().commit();
		} finally {
			if (pm.currentTransaction().isActive()) {
				pm.currentTransaction().rollback();
			}
		}
		
		

	}
	
}
