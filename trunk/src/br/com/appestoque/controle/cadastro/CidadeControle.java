package br.com.appestoque.controle.cadastro;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.appestoque.comum.Constantes;
import br.com.appestoque.comum.Pagina;
import br.com.appestoque.controle.BaseControle;
import br.com.appestoque.dao.cadastro.CidadeDAO;
import br.com.appestoque.dominio.cadastro.Cidade;

@SuppressWarnings("serial")
public class CidadeControle extends BaseControle{

	private CidadeDAO dao = null;
	private int primeiroRegistro;
	private List<Cidade> objetos = null;
	private String nome;
	private Cidade objeto;	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dao = new CidadeDAO((PersistenceManager) request.getAttribute("pm"));
		if(request.getParameter("acao").equals("iniciar")) {
			primeiroRegistro = 0;
			objetos = dao.pesquisar(nome,getId(request),primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA);
			paginar(primeiroRegistro);			
			request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
			request.setAttribute("totalRegistros", objetos.size());
			request.setAttribute("registrosPorPagina",Constantes.REGISTROS_POR_PAGINA);
			request.setAttribute("objetos",objetos);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_CIDADE_LISTAR);
			dispatcher.forward(request, response);
		}else if(request.getParameter("acao").equals("pesquisar")) {
			request.setAttribute("primeiroRegistro",request.getParameter("primeiroRegistro"));
			request.setAttribute("totalRegistros",request.getParameter("totalRegistros"));
			request.setAttribute("registrosPorPagina",request.getParameter("registrosPorPagina"));
			nome = request.getParameter("nome")==null||request.getParameter("nome").equals("")?null:request.getParameter("nome");
			primeiroRegistro = Integer.parseInt(request.getParameter("primeiroRegistro"));			
			objetos = null;
			if(request.getParameter("paginar")==null){
				totalRegistros = dao.contar(nome,getId(request));				
				objetos = dao.pesquisar(nome,getId(request),primeiroRegistro,Constantes.REGISTROS_POR_PAGINA);
				request.setAttribute("totalRegistros",totalRegistros);
				request.setAttribute("primeiroRegistro",primeiroRegistro);
			}else if(request.getParameter("paginar").equals("proximo")){
				primeiroRegistro += Constantes.REGISTROS_POR_PAGINA;
				objetos = dao.pesquisar(nome,getId(request),primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA);
				paginar(primeiroRegistro);
				request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
			}else if(request.getParameter("paginar").equals("anterior")){
				primeiroRegistro -= Constantes.REGISTROS_POR_PAGINA;
				objetos = dao.pesquisar(nome,getId(request),primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA);
				paginar(primeiroRegistro);
				request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
			}else if(request.getParameter("paginar").equals("ultimo")){
				primeiroRegistro = totalRegistros - ((totalRegistros % Constantes.REGISTROS_POR_PAGINA != 0) ? totalRegistros % Constantes.REGISTROS_POR_PAGINA : Constantes.REGISTROS_POR_PAGINA);
				objetos = dao.pesquisar(nome,getId(request),primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA);
				paginar(primeiroRegistro);
				request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
			}else if(request.getParameter("paginar").equals("primeiro")){
				primeiroRegistro = 0;
				objetos = dao.pesquisar(nome,getId(request),primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA);
				paginar(primeiroRegistro);
				request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
			}
			request.setAttribute("objetos",objetos);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_CIDADE_LISTAR);
			dispatcher.forward(request, response);
		} else if(request.getParameter("acao").equals("criar")) {
			objeto = new Cidade();
			request.setAttribute("objeto", objeto);			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_CIDADE_EDITAR);
			dispatcher.forward(request, response);
		} else if(request.getParameter("acao").equals("editar")) {
			objeto = dao.pesquisar(new Long(request.getParameter("id")));
			request.setAttribute("objeto",objeto);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_CIDADE_EDITAR);
			dispatcher.forward(request, response);
		} else if(request.getParameter("acao").equals("modificar")) {
			nome = request.getParameter("nome");
			objeto = new Cidade(nome,getId(request));
			objeto.setId(  request.getParameter("id")==null||request.getParameter("id").equals("")?null:new Long(request.getParameter("id")));
			dao.criar(objeto);
			ResourceBundle bundle = ResourceBundle.getBundle("i18n",request.getLocale());
			request.setAttribute("mensagem",bundle.getString("app.mensagem.sucesso"));
			request.setAttribute("primeiroRegistro",0);
			request.setAttribute("totalRegistros",0);
			request.setAttribute("registrosPorPagina",Constantes.REGISTROS_POR_PAGINA);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_CIDADE_LISTAR);
			dispatcher.forward(request, response);
		} else if(request.getParameter("acao").equals("remover")) {
			objeto = dao.pesquisar(new Long(request.getParameter("id")));
			dao.remover(objeto);
			objetos = dao.listar();
			request.setAttribute("objetos", objetos);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_CIDADE_LISTAR);
			dispatcher.forward(request, response);
		}
	}
	
}
