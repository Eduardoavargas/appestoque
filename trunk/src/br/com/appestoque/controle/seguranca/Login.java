package br.com.appestoque.controle.seguranca;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.appestoque.dao.PMF;
import br.com.appestoque.dao.seguranca.UsuarioDAO;

@SuppressWarnings("serial")
public class Login  extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		PersistenceManager pm = PMF.get().getPersistenceManager();
		UsuarioDAO dao = new UsuarioDAO(pm);
		if(dao.autenticar(email, senha)){
			out.println("true");
		}else{
			out.println("false");
		}
	}	
	
}