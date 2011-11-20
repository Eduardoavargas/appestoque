package br.com.appestoque.controle.seguranca;

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
import br.com.appestoque.dao.seguranca.UsuarioDAO;
import br.com.appestoque.dominio.seguranca.Usuario;

@SuppressWarnings("serial")
public class UsuarioControle extends BaseControle {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UsuarioDAO dao = null;
		
		if(request.getParameter("acao").equals("iniciar")) {
			request.setAttribute("primeiroRegistro",0);
			request.setAttribute("totalRegistros",0);
			request.setAttribute("registrosPorPagina",Constantes.REGISTROS_POR_PAGINA);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_USUARIO_PESQUISAR);
			dispatcher.forward(request, response);
		}else if(request.getParameter("acao").equals("pesquisar")) {
			request.setAttribute("primeiroRegistro",request.getParameter("primeiroRegistro"));
			request.setAttribute("totalRegistros",request.getParameter("totalRegistros"));
			request.setAttribute("registrosPorPagina",request.getParameter("registrosPorPagina"));
			dao = new UsuarioDAO((PersistenceManager) request.getAttribute("pm"));
			String email = request.getParameter("email")==null||request.getParameter("email").equals("")?null:request.getParameter("email");
			int primeiroRegistro = Integer.parseInt(request.getParameter("primeiroRegistro"));			
			List<Usuario> usuarios = null;
			if(request.getParameter("paginar")==null){
				totalRegistros = dao.contar(email);				
				usuarios = dao.pesquisar(email,primeiroRegistro,Constantes.REGISTROS_POR_PAGINA);
				request.setAttribute("totalRegistros",totalRegistros);
				request.setAttribute("primeiroRegistro",primeiroRegistro);
			}else if(request.getParameter("paginar").equals("proximo")){
				primeiroRegistro += Constantes.REGISTROS_POR_PAGINA;
				usuarios = dao.pesquisar(email,primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA);
				paginar(primeiroRegistro);
				request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
			}else if(request.getParameter("paginar").equals("anterior")){
				primeiroRegistro -= Constantes.REGISTROS_POR_PAGINA;
				usuarios = dao.pesquisar(email,primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA);
				paginar(primeiroRegistro);
				request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
			}else if(request.getParameter("paginar").equals("ultimo")){
				primeiroRegistro = totalRegistros - ((totalRegistros % Constantes.REGISTROS_POR_PAGINA != 0) ? totalRegistros % Constantes.REGISTROS_POR_PAGINA : Constantes.REGISTROS_POR_PAGINA);
				usuarios = dao.pesquisar(email,primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA);
				paginar(primeiroRegistro);
				request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
			}else if(request.getParameter("paginar").equals("primeiro")){
				primeiroRegistro = 0;
				usuarios = dao.pesquisar(email,primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA);
				paginar(primeiroRegistro);
				request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
			}
			request.setAttribute("objetos", usuarios);
			request.setAttribute("email", email);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_USUARIO_PESQUISAR);
			dispatcher.forward(request, response);
		} else if(request.getParameter("acao").equals("criar")) {
			request.setAttribute("objeto", new Usuario());
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_USUARIO_EDITAR);
			dispatcher.forward(request, response);
		} else if(request.getParameter("acao").equals("editar")) {
			dao = new UsuarioDAO((PersistenceManager) request.getAttribute("pm"));			
			Usuario usuario = dao.pesquisar(new Long(request.getParameter("id")));
			request.setAttribute("objeto",usuario);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_USUARIO_EDITAR);
			dispatcher.forward(request, response);
		} else if(request.getParameter("acao").equals("modificar")) {
			dao = new UsuarioDAO((PersistenceManager) request.getAttribute("pm"));
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");			
			Usuario objeto = new Usuario(nome,email,senha);
			objeto.setId(  request.getParameter("id")==null||request.getParameter("id").equals("")?null:new Long(request.getParameter("id")));
			dao.criar(objeto);
			ResourceBundle bundle = ResourceBundle.getBundle("i18n",request.getLocale());
			request.setAttribute("mensagem",bundle.getString("app.mensagem.sucesso"));
			request.setAttribute("primeiroRegistro",0);
			request.setAttribute("totalRegistros",0);
			request.setAttribute("registrosPorPagina",Constantes.REGISTROS_POR_PAGINA);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_USUARIO_PESQUISAR);
			dispatcher.forward(request, response);
		} else if(request.getParameter("acao").equals("remover")) {
			dao = new UsuarioDAO((PersistenceManager) request.getAttribute("pm"));			
			Usuario usuario = dao.pesquisar(new Long(request.getParameter("id")));
			dao.remover(usuario);
			List<Usuario> usuarios = dao.listar();
			request.setAttribute("objetos", usuarios);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_USUARIO_PESQUISAR);
			dispatcher.forward(request, response);
		}
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request, response);
	}
	
}