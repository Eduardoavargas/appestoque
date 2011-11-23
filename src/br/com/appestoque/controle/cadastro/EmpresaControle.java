package br.com.appestoque.controle.cadastro;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.appestoque.comum.Pagina;
import br.com.appestoque.dominio.cadastro.Empresa;

@SuppressWarnings("serial")
public class EmpresaControle extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		if (request.getParameter("acao").equals("criar")) {
			request.setAttribute("objeto", new Empresa());
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_EMPRESA_EDITAR);
			dispatcher.forward(request, response);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doGet(request, response);
	}

}
