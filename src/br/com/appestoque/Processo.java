package br.com.appestoque;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.appestoque.comum.Pagina;

@SuppressWarnings("serial")
public class Processo extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("acao").equals("logoff")) {
			HttpSession session = request.getSession();
			session.setAttribute("autorizado", new Boolean("false"));
			HttpServletResponse servletResponse = (HttpServletResponse) response;
    		servletResponse.sendRedirect(Pagina.PAGINA_APRESENTACAO);
		}		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
