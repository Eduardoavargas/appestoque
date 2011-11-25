package br.com.appstoque.rest;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.appestoque.dao.PMF;
import br.com.appestoque.dominio.seguranca.Usuario;

@SuppressWarnings("serial")
public class SincRest  extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Usuario usuario = new Usuario("André Tricano","appestoque@gmail.com","1234",1L);
		try {
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