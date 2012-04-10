package br.com.appestoque.controle.cadastro;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.appestoque.dao.PMF;
import br.com.appestoque.dao.cadastro.EmpresaDAO;

@SuppressWarnings("serial")
public class VerificarEmail extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String email = request.getParameter("email");
		PersistenceManager pm = PMF.get().getPersistenceManager();
		EmpresaDAO dao = new EmpresaDAO(pm);
		if(dao.pesquisarEmail(email)){
			out.println("true");
		}else{
			out.println("false");
		}
	}	
	
}
