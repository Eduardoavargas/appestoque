package br.com.appestoque.controle.seguranca;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.appestoque.comum.Pagina;
import br.com.appestoque.dao.seguranca.UsuarioDAO;
import br.com.appestoque.dominio.seguranca.Usuario;

@SuppressWarnings("serial")
public class UsuarioControle extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		UsuarioDAO dao = null;
		if(request.getParameter("acao").equals("pesquisar")) {
			dao = new UsuarioDAO((PersistenceManager) request.getAttribute("pm"));
			String email = request.getParameter("email")==null||request.getParameter("email").equals("")?null:request.getParameter("email");
			List<Usuario> usuarios = dao.pesquisar(email);
			request.setAttribute("objetos", usuarios);
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
			response.sendRedirect(Pagina.PAGINA_USUARIO_PESQUISAR);
		} else if(request.getParameter("acao").equals("remover")) {
			
		}
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request, response);
	}
	
}