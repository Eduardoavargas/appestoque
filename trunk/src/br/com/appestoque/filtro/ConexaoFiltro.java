package br.com.appestoque.filtro;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.*;

import br.com.appestoque.dao.PMF;

public class ConexaoFiltro implements Filter {

	public void doFilter(ServletRequest requisicao, ServletResponse resposta, FilterChain chain) throws IOException, ServletException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		requisicao.setAttribute("pm",pm);
		chain.doFilter(requisicao, resposta);
		pm.close();
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
	
}