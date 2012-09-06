package br.com.appestoque.restful.pagseguro;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NotificacaoRESTful extends HttpServlet {

	private static final long serialVersionUID = 5454264911441546708L;
	
	protected Logger logger = Logger.getLogger(getClass().getCanonicalName());

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			br.com.appestoque.Util.enviarEmail("andre.tricano@gmail.com","assunto","corpo");
		}catch(Exception e){
			logger.log(Level.SEVERE,"ERRO NOTIFICAÇÃO PAGSEGURO");
			e.printStackTrace();
		}
	}

}