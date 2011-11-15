package br.com.appestoque.controle.seguranca;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class UsuarioControle extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	
		if(request.getParameter("acao").equals("criar")) {
			
		} else if(request.getParameter("acao").equals("pesquisar")) {
			
		} else if(request.getParameter("acao").equals("editar")) {
			
		} else if(request.getParameter("acao").equals("remover")) {
			
		}
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request, response);
	}
	
}