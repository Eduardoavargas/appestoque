package br.com.appestoque.controle.suprimento;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.appestoque.Constantes;
import br.com.appestoque.comum.Pagina;
import br.com.appestoque.controle.BaseControle;
import br.com.appestoque.dao.DAOException;
import br.com.appestoque.dao.suprimento.ProdutoDAO;
import br.com.appestoque.dominio.suprimento.Produto;

@SuppressWarnings("serial")
public class ProdutoControle extends BaseControle{
	
	private int primeiroRegistro = 0;
	private String numero = null;
	private ProdutoDAO dao = null;	
	private String nome = null;
	private Double preco = null;
	private Double estoque = null;
	private Produto objeto = null;
	private List<Produto> objetos = null; 

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dao = new ProdutoDAO((PersistenceManager) request.getAttribute("pm"));
		if(request.getParameter("acao").equals("iniciar")) {
			numero = request.getParameter("numero");
			primeiroRegistro = 0;
			objetos = dao.pesquisar(numero,getId(request),primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA);
			paginar(primeiroRegistro);			
			request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
			request.setAttribute("totalRegistros", objetos.size());
			request.setAttribute("registrosPorPagina",Constantes.REGISTROS_POR_PAGINA);
			request.setAttribute("objetos",objetos);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_PRODUTO_LISTAR);
			dispatcher.forward(request, response);
		}else if(request.getParameter("acao").equals("pesquisar")) {
			request.setAttribute("primeiroRegistro",request.getParameter("primeiroRegistro"));
			request.setAttribute("totalRegistros",request.getParameter("totalRegistros"));
			request.setAttribute("registrosPorPagina",request.getParameter("registrosPorPagina"));
			numero = request.getParameter("numero")==null||request.getParameter("numero").equals("")?null:request.getParameter("numero");
			primeiroRegistro = Integer.parseInt(request.getParameter("primeiroRegistro"));			
			objetos = null;
			if(request.getParameter("paginar")==null){
				totalRegistros = dao.contar(numero,getId(request));				
				objetos = dao.pesquisar(numero,getId(request),primeiroRegistro,Constantes.REGISTROS_POR_PAGINA);
				request.setAttribute("totalRegistros",totalRegistros);
				request.setAttribute("primeiroRegistro",primeiroRegistro);
			}else if(request.getParameter("paginar").equals("proximo")){
				primeiroRegistro += Constantes.REGISTROS_POR_PAGINA;
				objetos = dao.pesquisar(numero,getId(request),primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA);
				paginar(primeiroRegistro);
				request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
			}else if(request.getParameter("paginar").equals("anterior")){
				primeiroRegistro -= Constantes.REGISTROS_POR_PAGINA;
				objetos = dao.pesquisar(numero,getId(request),primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA);
				paginar(primeiroRegistro);
				request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
			}else if(request.getParameter("paginar").equals("ultimo")){
				primeiroRegistro = totalRegistros - ((totalRegistros % Constantes.REGISTROS_POR_PAGINA != 0) ? totalRegistros % Constantes.REGISTROS_POR_PAGINA : Constantes.REGISTROS_POR_PAGINA);
				objetos = dao.pesquisar(numero,getId(request),primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA);
				paginar(primeiroRegistro);
				request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
			}else if(request.getParameter("paginar").equals("primeiro")){
				primeiroRegistro = 0;
				objetos = dao.pesquisar(numero,getId(request),primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA);
				paginar(primeiroRegistro);
				request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
			}
			request.setAttribute("objetos",objetos);
			request.setAttribute("numero",numero);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_PRODUTO_LISTAR);
			dispatcher.forward(request, response);
		} else if(request.getParameter("acao").equals("criar")) {
			request.setAttribute("objeto", new Produto());
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_PRODUTO_EDITAR);
			dispatcher.forward(request, response);
		} else if(request.getParameter("acao").equals("editar")) {
			dao = new ProdutoDAO((PersistenceManager) request.getAttribute("pm"));			
			objeto = dao.pesquisar(new Long(request.getParameter("id")));
			request.setAttribute("objeto",objeto);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_PRODUTO_EDITAR);
			dispatcher.forward(request, response);
		} else if(request.getParameter("acao").equals("modificar")) {
			dao = new ProdutoDAO((PersistenceManager) request.getAttribute("pm"));
			nome = request.getParameter("nome");
			numero = request.getParameter("numero");
			preco = Double.parseDouble(request.getParameter("preco").replace(".", "").replace(",", "."));
			estoque = Double.parseDouble(request.getParameter("estoque").replace(".", "").replace(",", "."));
			objeto = new Produto(nome,numero,preco,estoque,getId(request));
			objeto.setId(  request.getParameter("id")==null||request.getParameter("id").equals("")?null:new Long(request.getParameter("id")));
			dao.criar(objeto);
			ResourceBundle bundle = ResourceBundle.getBundle("i18n",request.getLocale());
			request.setAttribute("mensagem",bundle.getString("app.mensagem.sucesso"));
			request.setAttribute("primeiroRegistro",0);
			request.setAttribute("totalRegistros",0);
			request.setAttribute("registrosPorPagina",Constantes.REGISTROS_POR_PAGINA);
			
			numero = null;
			
			request.setAttribute("numero",numero);
			
			objetos = dao.pesquisar(numero,getId(request),primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA);
			request.setAttribute("objetos",objetos);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_PRODUTO_LISTAR);
			dispatcher.forward(request, response);
		} else if(request.getParameter("acao").equals("remover")) {
			objeto = dao.pesquisar(new Long(request.getParameter("id")));
			try{
				if(objeto!=null){
					dao.excluir(objeto);
				}
			}catch(DAOException e){
				request.setAttribute("mensagem", e.getMessage());
			}
			request.setAttribute("primeiroRegistro",0);
			request.setAttribute("totalRegistros",0);
			request.setAttribute("registrosPorPagina",Constantes.REGISTROS_POR_PAGINA);
			objetos = dao.pesquisar(null,getId(request),0,Constantes.REGISTROS_POR_PAGINA);
			request.setAttribute("objetos", objetos);			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_PRODUTO_LISTAR);
			dispatcher.forward(request, response);
		}
	}
	
}