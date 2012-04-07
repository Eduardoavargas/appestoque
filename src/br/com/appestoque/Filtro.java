package br.com.appestoque;

import java.io.IOException;
import java.util.ResourceBundle;

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
import br.com.appestoque.dao.cadastro.RepresentanteDAO;
import br.com.appestoque.dao.seguranca.UsuarioDAO;
import br.com.appestoque.dominio.cadastro.Empresa;
import br.com.appestoque.dominio.cadastro.Representante;
import br.com.appestoque.dominio.seguranca.Usuario;
import br.com.appestoque.util.Constantes;

public class Filtro implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain filterChain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        PersistenceManager pm = null;
        Boolean autorizado = (Boolean) session.getAttribute("autorizado");
        String uuid = request.getParameter("uuid");
        
        String uri = req.getRequestURI().toString();
        boolean RESTFul = false;
        if(uuid!=null){
	        for(int i=0;i<Constantes.uris.length;++i){
	        	if(Constantes.uris[i].equals(uri)){
	        		RESTFul = !RESTFul;
	        		break;
	        	}
	        }
        }
        
		if (uuid!=null&&RESTFul) {
			RepresentanteDAO dao = new RepresentanteDAO(PMF.get().getPersistenceManager());
			Representante representante = dao.pesquisar(uuid,TipoBusca.PREGUICOSA);
			if (representante != null) {
				pm = PMF.get().getPersistenceManager();
				request.setAttribute("pm", pm);
				Key key = KeyFactory.createKey(Empresa.class.getSimpleName(),representante.getIdEmpresa());
				Empresa empresa = pm.getObjectById(Empresa.class, key);
				session.setAttribute("empresa", empresa);
				session.setAttribute("representante", representante);
				filterChain.doFilter(request, response);
			}
		} else if (autorizado == null || !autorizado) {
        	String email = request.getParameter("email");
    		String senha = request.getParameter("senha");

			UsuarioDAO dao = new UsuarioDAO(PMF.get().getPersistenceManager());
			Usuario usuario = null;
			if (email != null && senha != null) {
				usuario = dao.pesquisar(email, senha);
			} 

			if (usuario != null) {
				pm = PMF.get().getPersistenceManager();
				request.setAttribute("pm", pm);
				Key key = KeyFactory.createKey(Empresa.class.getSimpleName(),
						usuario.getIdEmpresa());
				Empresa empresa = pm.getObjectById(Empresa.class, key);
				if(empresa.getAtivo()){
					session.setAttribute("empresa", empresa);
					session.setAttribute("usuario", usuario);
					session.setAttribute("autorizado", new Boolean("true"));
					filterChain.doFilter(request, response);
				}else{
					ResourceBundle bundle = ResourceBundle.getBundle("i18n",request.getLocale());
					request.setAttribute("mensagem",bundle.getString("app.mensagem.empresa.inativa"));
					HttpServletResponse servletResponse = (HttpServletResponse) response;
					servletResponse.sendRedirect(Pagina.PAGINA_APRESENTACAO);					
				}
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