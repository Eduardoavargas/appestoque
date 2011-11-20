package br.com.appestoque.controle.seguranca;

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
import br.com.appestoque.dao.seguranca.UsuarioDAO;
import br.com.appestoque.dominio.seguranca.Usuario;

@SuppressWarnings("serial")
public class UsuarioControle extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		UsuarioDAO dao = null;
		Long registroAnterior = 0L;
		Long registroPosterior = 0L;
		Long registroCorrente = 0L;
		if(request.getParameter("acao").equals("pesquisar")) {
			dao = new UsuarioDAO((PersistenceManager) request.getAttribute("pm"));
			String email = request.getParameter("email")==null||request.getParameter("email").equals("")?null:request.getParameter("email");
			registroCorrente = new Long(request.getParameter("registroCorrente"));
			List<Usuario> usuarios = null;
			if(request.getParameter("paginar")==null){
				registroCorrente = 0L;
				registroAnterior = registroCorrente;
				registroPosterior += Constantes.REGISTRO_POR_PAGINA;
				usuarios = dao.pesquisar(email,registroCorrente.longValue(),Constantes.REGISTRO_POR_PAGINA);
				request.setAttribute("registroCorrente",registroCorrente);
			}else if(request.getParameter("paginar").equals("proximo")){
				registroAnterior = registroCorrente; 
				registroPosterior += Constantes.REGISTRO_POR_PAGINA;
				usuarios = dao.pesquisar(email,registroAnterior.longValue(),registroPosterior.longValue());
				request.setAttribute("registroCorrente", registroPosterior);
			}else if(request.getParameter("paginar").equals("anterior")){
				registroAnterior = registroCorrente;
				registroPosterior -= Constantes.REGISTRO_POR_PAGINA;
				usuarios = dao.pesquisar(email,registroAnterior.longValue(),registroPosterior.longValue());
				request.setAttribute("rgc", registroAnterior);
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