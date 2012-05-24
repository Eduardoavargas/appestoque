package br.com.appestoque.restful;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.appestoque.dao.PMF;
import br.com.appestoque.dao.cadastro.BairroDAO;
import br.com.appestoque.dao.cadastro.CidadeDAO;
import br.com.appestoque.dao.cadastro.ClienteDAO;
import br.com.appestoque.dao.cadastro.EmpresaDAO;
import br.com.appestoque.dao.suprimento.ProdutoDAO;
import br.com.appestoque.dominio.cadastro.Empresa;

@SuppressWarnings("serial")
public class LimparRESTful extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response)	throws IOException {
		processServer(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		processServer(request, response);
	}
	
	public void processServer(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if(request.getParameter("uuid")!=null&&request.getParameter("entidade")!=null){
			PersistenceManager pm = null;
	        try {
	        	pm = PMF.get().getPersistenceManager();
	        	EmpresaDAO empresaDAO = new EmpresaDAO(pm);
	        	String uuid = request.getParameter("uuid");
	        	Empresa empresa = empresaDAO.pesquisar(uuid);
	        	if(empresa!=null){
	        		int entidade = Integer.parseInt(request.getParameter("entidade"));
	        		switch (entidade) {
						case 0: CidadeDAO cidadeDAO  = new CidadeDAO(pm);
								cidadeDAO.excluir(empresa);
								break;
						case 1: BairroDAO bairroDAO  = new BairroDAO(pm);
								bairroDAO.excluir(empresa);
								break;		
						case 2: ClienteDAO clienteDAO  = new ClienteDAO(pm);
								clienteDAO.excluir(empresa);
								break;		
						case 3: ProdutoDAO produtoDAO  = new ProdutoDAO(pm);
								produtoDAO.excluir(empresa);
								break;		
						default:
							break;
					}
	        	}
	        }finally{
	        	pm.close();
	        }
		}
	}
	
}