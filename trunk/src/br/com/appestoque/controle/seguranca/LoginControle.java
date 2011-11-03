package br.com.appestoque.controle.seguranca;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class LoginControle extends HttpServlet {

	public void doGet(HttpServletRequest requisicao, HttpServletResponse resposta)throws IOException{
		doPost(requisicao, resposta);
	}
	
	public void doPost(HttpServletRequest requisicao, HttpServletResponse resposta)throws IOException{
		String email = requisicao.getParameter("email");
		String senha = requisicao.getParameter("senha");
		PersistenceManager pm = (PersistenceManager) requisicao.getAttribute("pm");
	}
	
}
