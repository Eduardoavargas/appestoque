/*
 * direitos (c) 2011 AST Tecnologia Virtual Ltda
 * 
 * As informações contidas neste documento são de propriedade da 
 * AST Tecnologia Virtual Ltda. Este documento é confidencial e não 
 * pode ser usado para fins produtivos, e não pode ser reproduzido 
 * sem autorização
 * 
 */

package br.com.appestoque;

/**
 * 
 * Filtro utilizado para conexão e autenticação
 * 
 * @version 1.0 5 de novembro de 2011
 * @author andré tricano
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

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import br.com.appestoque.comum.Pagina;
import br.com.appestoque.dao.PMF;
import br.com.appestoque.dao.seguranca.UsuarioDAO;
import br.com.appestoque.dominio.cadastro.Empresa;
import br.com.appestoque.dominio.seguranca.Usuario;

public class Filtro implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;  
        HttpSession session = req.getSession();
        PersistenceManager pm = null;
        Boolean autorizado = (Boolean) session.getAttribute("autorizado");   
        if ( autorizado == null || !autorizado ) {
        	String email = request.getParameter("email");
    		String senha = request.getParameter("senha");
			String serial = request.getParameter("serial");

			UsuarioDAO dao = new UsuarioDAO(PMF.get().getPersistenceManager());
			Usuario usuario = null;
			if (email != null && senha != null) {
				usuario = dao.pesquisar(email, senha);
			} else if (serial != null) {
				usuario = dao.pesquisar(serial);
			}

			if (usuario != null) {
				pm = PMF.get().getPersistenceManager();
				request.setAttribute("pm", pm);
				Key key = KeyFactory.createKey(Empresa.class.getSimpleName(),
						usuario.getIdEmpresa());
				Empresa empresa = pm.getObjectById(Empresa.class, key);
				session.setAttribute("empresa", empresa);
				session.setAttribute("usuario", usuario);
				session.setAttribute("autorizado", new Boolean("true"));
				filterChain.doFilter(request, response);
			} else {
				HttpServletResponse servletResponse = (HttpServletResponse) response;
				servletResponse.sendRedirect(Pagina.PAGINA_APRESENTACAO);
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