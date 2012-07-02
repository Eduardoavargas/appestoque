package br.com.appestoque.restful;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public abstract class BaseRESTFul extends HttpServlet{
	
	protected Logger logger = Logger.getLogger(getClass().getCanonicalName());
	protected ResourceBundle bundle = null;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)	throws IOException {
		processServer(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		processServer(request, response);
	}
	
	public void processServer(HttpServletRequest request, HttpServletResponse response) throws IOException{
		bundle = ResourceBundle.getBundle("i18n",request.getLocale());
	}

}