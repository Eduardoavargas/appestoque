package br.com.appestoque.rest;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.appestoque.dao.suprimento.ProdutoDAO;

@SuppressWarnings("serial")
public class ProdutoRest extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ProdutoDAO dao = new ProdutoDAO((PersistenceManager) request.getAttribute("pm"));
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request, response);
	}

}
