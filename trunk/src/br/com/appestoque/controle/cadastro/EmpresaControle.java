package br.com.appestoque.controle.cadastro;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.appestoque.comum.Constantes;
import br.com.appestoque.comum.Pagina;
import br.com.appestoque.controle.BaseControle;
import br.com.appestoque.dao.cadastro.EmpresaDAO;
import br.com.appestoque.dao.suprimento.ProdutoDAO;
import br.com.appestoque.dominio.cadastro.Empresa;
import br.com.appestoque.dominio.suprimento.Produto;

@SuppressWarnings("serial")
public class EmpresaControle extends BaseControle {

	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		EmpresaDAO dao = null;
		if(request.getParameter("acao").equals("iniciar")) {
			request.setAttribute("primeiroRegistro",0);
			request.setAttribute("totalRegistros",0);
			request.setAttribute("registrosPorPagina",Constantes.REGISTROS_POR_PAGINA);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_EMPRESA_LISTAR);
			dispatcher.forward(request, response);
		}else if(request.getParameter("acao").equals("pesquisar")) {
			request.setAttribute("primeiroRegistro",request.getParameter("primeiroRegistro"));
			request.setAttribute("totalRegistros",request.getParameter("totalRegistros"));
			request.setAttribute("registrosPorPagina",request.getParameter("registrosPorPagina"));
			dao = new EmpresaDAO((PersistenceManager) request.getAttribute("pm"));
			String cnpj = request.getParameter("cnpj")==null||request.getParameter("cnpj").equals("")?null:request.getParameter("cnpj");
			int primeiroRegistro = Integer.parseInt(request.getParameter("primeiroRegistro"));			
			List<Empresa> empresas = null;
			if(request.getParameter("paginar")==null){
				totalRegistros = dao.contar(cnpj);				
				empresas = dao.pesquisar(cnpj,primeiroRegistro,Constantes.REGISTROS_POR_PAGINA);
				request.setAttribute("totalRegistros",totalRegistros);
				request.setAttribute("primeiroRegistro",primeiroRegistro);
			}else if(request.getParameter("paginar").equals("proximo")){
				primeiroRegistro += Constantes.REGISTROS_POR_PAGINA;
				empresas = dao.pesquisar(cnpj,primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA);
				paginar(primeiroRegistro);
				request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
			}else if(request.getParameter("paginar").equals("anterior")){
				primeiroRegistro -= Constantes.REGISTROS_POR_PAGINA;
				empresas = dao.pesquisar(cnpj,primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA);
				paginar(primeiroRegistro);
				request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
			}else if(request.getParameter("paginar").equals("ultimo")){
				primeiroRegistro = totalRegistros - ((totalRegistros % Constantes.REGISTROS_POR_PAGINA != 0) ? totalRegistros % Constantes.REGISTROS_POR_PAGINA : Constantes.REGISTROS_POR_PAGINA);
				empresas = dao.pesquisar(cnpj,primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA);
				paginar(primeiroRegistro);
				request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
			}else if(request.getParameter("paginar").equals("primeiro")){
				primeiroRegistro = 0;
				empresas = dao.pesquisar(cnpj,primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA);
				paginar(primeiroRegistro);
				request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
			}
			request.setAttribute("objetos",empresas);
			request.setAttribute("cnpj",cnpj);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_EMPRESA_LISTAR);
			dispatcher.forward(request, response);
		} else if(request.getParameter("acao").equals("criar")) {
			request.setAttribute("objeto", new Produto());
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_EMPRESA_EDITAR);
			dispatcher.forward(request, response);
		} else if(request.getParameter("acao").equals("editar")) {
			dao = new EmpresaDAO((PersistenceManager) request.getAttribute("pm"));			
			Empresa empresa = dao.pesquisar(new Long(request.getParameter("id")));
			request.setAttribute("objeto",empresa);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_EMPRESA_EDITAR);
			dispatcher.forward(request, response);
		} else if(request.getParameter("acao").equals("modificar")) {
			dao = new EmpresaDAO((PersistenceManager) request.getAttribute("pm"));
			String nome = request.getParameter("nome");
			String numero = request.getParameter("numero");
			Double preco = new Double(request.getParameter("preco"));			
			Empresa objeto = new Empresa();
			objeto.setId(  request.getParameter("id")==null||request.getParameter("id").equals("")?null:new Long(request.getParameter("id")));
			dao.criar(objeto);
			ResourceBundle bundle = ResourceBundle.getBundle("i18n",request.getLocale());
			request.setAttribute("mensagem",bundle.getString("app.mensagem.sucesso"));
			request.setAttribute("primeiroRegistro",0);
			request.setAttribute("totalRegistros",0);
			request.setAttribute("registrosPorPagina",Constantes.REGISTROS_POR_PAGINA);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_EMPRESA_LISTAR);
			dispatcher.forward(request, response);
		} else if(request.getParameter("acao").equals("remover")) {
			dao = new EmpresaDAO((PersistenceManager) request.getAttribute("pm"));			
			Empresa empresa = dao.pesquisar(new Long(request.getParameter("id")));
			dao.remover(empresa);
			List<Empresa> empresas = dao.listar();
			request.setAttribute("objetos", empresas);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_EMPRESA_LISTAR);
			dispatcher.forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doGet(request, response);
	}

}
