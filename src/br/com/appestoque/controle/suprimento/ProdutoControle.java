package br.com.appestoque.controle.suprimento;

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
import br.com.appestoque.dao.suprimento.ProdutoDAO;
import br.com.appestoque.dominio.suprimento.Produto;

@SuppressWarnings("serial")
public class ProdutoControle extends BaseControle{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProdutoDAO dao = null;
		if(request.getParameter("acao").equals("iniciar")) {
			request.setAttribute("primeiroRegistro",0);
			request.setAttribute("totalRegistros",0);
			request.setAttribute("registrosPorPagina",Constantes.REGISTROS_POR_PAGINA);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_PRODUTO_LISTAR);
			dispatcher.forward(request, response);
		}else if(request.getParameter("acao").equals("pesquisar")) {
			request.setAttribute("primeiroRegistro",request.getParameter("primeiroRegistro"));
			request.setAttribute("totalRegistros",request.getParameter("totalRegistros"));
			request.setAttribute("registrosPorPagina",request.getParameter("registrosPorPagina"));
			dao = new ProdutoDAO((PersistenceManager) request.getAttribute("pm"));
			String numero = request.getParameter("numero")==null||request.getParameter("numero").equals("")?null:request.getParameter("numero");
			int primeiroRegistro = Integer.parseInt(request.getParameter("primeiroRegistro"));			
			List<Produto> produtos = null;
			if(request.getParameter("paginar")==null){
				totalRegistros = dao.contar(numero,getId(request));				
				produtos = dao.pesquisar(numero,getId(request),primeiroRegistro,Constantes.REGISTROS_POR_PAGINA);
				request.setAttribute("totalRegistros",totalRegistros);
				request.setAttribute("primeiroRegistro",primeiroRegistro);
			}else if(request.getParameter("paginar").equals("proximo")){
				primeiroRegistro += Constantes.REGISTROS_POR_PAGINA;
				produtos = dao.pesquisar(numero,getId(request),primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA);
				paginar(primeiroRegistro);
				request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
			}else if(request.getParameter("paginar").equals("anterior")){
				primeiroRegistro -= Constantes.REGISTROS_POR_PAGINA;
				produtos = dao.pesquisar(numero,getId(request),primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA);
				paginar(primeiroRegistro);
				request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
			}else if(request.getParameter("paginar").equals("ultimo")){
				primeiroRegistro = totalRegistros - ((totalRegistros % Constantes.REGISTROS_POR_PAGINA != 0) ? totalRegistros % Constantes.REGISTROS_POR_PAGINA : Constantes.REGISTROS_POR_PAGINA);
				produtos = dao.pesquisar(numero,getId(request),primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA);
				paginar(primeiroRegistro);
				request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
			}else if(request.getParameter("paginar").equals("primeiro")){
				primeiroRegistro = 0;
				produtos = dao.pesquisar(numero,getId(request),primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA);
				paginar(primeiroRegistro);
				request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
			}
			request.setAttribute("objetos",produtos);
			request.setAttribute("numero",numero);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_PRODUTO_LISTAR);
			dispatcher.forward(request, response);
		} else if(request.getParameter("acao").equals("criar")) {
			request.setAttribute("objeto", new Produto());
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_PRODUTO_EDITAR);
			dispatcher.forward(request, response);
		} else if(request.getParameter("acao").equals("editar")) {
			dao = new ProdutoDAO((PersistenceManager) request.getAttribute("pm"));			
			Produto Produto = dao.pesquisar(new Long(request.getParameter("id")));
			request.setAttribute("objeto",Produto);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_PRODUTO_EDITAR);
			dispatcher.forward(request, response);
		} else if(request.getParameter("acao").equals("modificar")) {
			dao = new ProdutoDAO((PersistenceManager) request.getAttribute("pm"));
			String nome = request.getParameter("nome");
			String numero = request.getParameter("numero");
			Double preco = new Double(request.getParameter("preco").replace(".","").replace(",", "."));
			Double estoque = new Double(request.getParameter("estoque").replace(".","").replace(",", "."));
			String imagem = request.getParameter("imagem");
			Produto objeto = new Produto(nome,numero,preco,estoque,imagem,getId(request));
			objeto.setId(  request.getParameter("id")==null||request.getParameter("id").equals("")?null:new Long(request.getParameter("id")));
			dao.criar(objeto);
			ResourceBundle bundle = ResourceBundle.getBundle("i18n",request.getLocale());
			request.setAttribute("mensagem",bundle.getString("app.mensagem.sucesso"));
			request.setAttribute("primeiroRegistro",0);
			request.setAttribute("totalRegistros",0);
			request.setAttribute("registrosPorPagina",Constantes.REGISTROS_POR_PAGINA);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_PRODUTO_LISTAR);
			dispatcher.forward(request, response);
		} else if(request.getParameter("acao").equals("remover")) {
			dao = new ProdutoDAO((PersistenceManager) request.getAttribute("pm"));			
			Produto Produto = dao.pesquisar(new Long(request.getParameter("id")));
			dao.remover(Produto);
			List<Produto> Produtos = dao.listar();
			request.setAttribute("objetos", Produtos);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_PRODUTO_LISTAR);
			dispatcher.forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}