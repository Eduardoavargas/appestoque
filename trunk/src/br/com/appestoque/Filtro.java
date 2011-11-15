/*
 * direitos (c) 2011 AST Tecnologia Virtual Ltda
 * 
 * As informa��es contidas neste documento s�o de propriedade da 
 * AST Tecnologia Virtual Ltda. Este documento � confidencial e n�o 
 * pode ser usado para fins produtivos, e n�o pode ser reproduzido 
 * sem autoriza��o
 * 
 */

package br.com.appestoque;

/**
 * 
 * Filtro utilizado para conex�o e autentica��o
 * 
 * @version 1.0 5 de novembro de 2011
 * @author andr� tricano
 * 
 */
import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.appestoque.comum.Pagina;
import br.com.appestoque.dao.PMF;
import br.com.appestoque.dao.seguranca.UsuarioDAO;
import br.com.appestoque.dominio.seguranca.Usuario;

public class Filtro implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain filterChain) throws IOException, ServletException {
		/*
		 * buscando informa��o sobre a requisi��o
		 */
		HttpServletRequest req = (HttpServletRequest) request;  
        HttpSession session = req.getSession();
        PersistenceManager pm = null;
        
        Boolean autorizado = (Boolean) session.getAttribute("autorizado");   
        if ( autorizado == null || !autorizado ) {
        	String email = request.getParameter("email");
    		String senha = request.getParameter("senha");
    		UsuarioDAO dao = new UsuarioDAO(PMF.get().getPersistenceManager());
    		Usuario usuario = dao.pesquisar(email, senha);
        	if(usuario!=null){
        		pm = PMF.get().getPersistenceManager();
        		request.setAttribute("pm",pm);
        		session.setAttribute("autorizado", new Boolean("true"));
        		filterChain.doFilter(request, response);
        	}else{
        		HttpServletResponse servletResponse = (HttpServletResponse) response;
        		servletResponse.sendRedirect(Pagina.PAGINA_INICIAL);
        	}
        }else{
        	pm = PMF.get().getPersistenceManager();
    		request.setAttribute("pm",pm);
        	filterChain.doFilter(request, response);
        	pm.close();
        }
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	
	@Override
	public void destroy() {
	}

}