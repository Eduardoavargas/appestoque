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
import br.com.appestoque.dao.cadastro.EmpresaDAO;
import br.com.appestoque.dominio.cadastro.Empresa;

@SuppressWarnings("serial")
public class EmpresaControle extends BaseControle {

	private EmpresaDAO dao = null;
	private String cnpj;
	private int primeiroRegistro;
	private List<Empresa> objetos = null;
	
	private String nome;
	private String bairro;
	private String cidade;
	private String cep;
	private Integer numero;
	private String complemento;
	private Empresa objeto;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dao = new EmpresaDAO((PersistenceManager) request.getAttribute("pm"));
		if(request.getParameter("acao").equals("iniciar")) {
			primeiroRegistro = 0;
			objetos = dao.pesquisar(cnpj,primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA);
			paginar(primeiroRegistro);			
			request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
			request.setAttribute("totalRegistros", objetos.size());
			request.setAttribute("registrosPorPagina",Constantes.REGISTROS_POR_PAGINA);
			request.setAttribute("objetos",objetos);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_EMPRESA_LISTAR);
			dispatcher.forward(request, response);
		}else if(request.getParameter("acao").equals("pesquisar")) {
			request.setAttribute("primeiroRegistro",request.getParameter("primeiroRegistro"));
			request.setAttribute("totalRegistros",request.getParameter("totalRegistros"));
			request.setAttribute("registrosPorPagina",request.getParameter("registrosPorPagina"));
			dao = new EmpresaDAO((PersistenceManager) request.getAttribute("pm"));
			cnpj = request.getParameter("cnpj")==null||request.getParameter("cnpj").equals("")?null:request.getParameter("cnpj");
			primeiroRegistro = Integer.parseInt(request.getParameter("primeiroRegistro"));			
			
			if(request.getParameter("paginar")==null){
				totalRegistros = dao.contar(cnpj);				
				objetos = dao.pesquisar(cnpj,primeiroRegistro,Constantes.REGISTROS_POR_PAGINA);
				request.setAttribute("totalRegistros",totalRegistros);
				request.setAttribute("primeiroRegistro",primeiroRegistro);
			}else if(request.getParameter("paginar").equals("proximo")){
				primeiroRegistro += Constantes.REGISTROS_POR_PAGINA;
				objetos = dao.pesquisar(cnpj,primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA);
				paginar(primeiroRegistro);
				request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
			}else if(request.getParameter("paginar").equals("anterior")){
				primeiroRegistro -= Constantes.REGISTROS_POR_PAGINA;
				objetos = dao.pesquisar(cnpj,primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA);
				paginar(primeiroRegistro);
				request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
			}else if(request.getParameter("paginar").equals("ultimo")){
				primeiroRegistro = totalRegistros - ((totalRegistros % Constantes.REGISTROS_POR_PAGINA != 0) ? totalRegistros % Constantes.REGISTROS_POR_PAGINA : Constantes.REGISTROS_POR_PAGINA);
				objetos = dao.pesquisar(cnpj,primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA);
				paginar(primeiroRegistro);
				request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
			}else if(request.getParameter("paginar").equals("primeiro")){
				primeiroRegistro = 0;
				objetos = dao.pesquisar(cnpj,primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA);
				paginar(primeiroRegistro);
				request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
			}
			request.setAttribute("objetos",objetos);
			request.setAttribute("cnpj",cnpj);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_EMPRESA_LISTAR);
			dispatcher.forward(request, response);
		} else if(request.getParameter("acao").equals("criar")) {
			request.setAttribute("objeto", new Empresa());
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
			nome = request.getParameter("nome");
			cnpj = request.getParameter("cnpj");
			bairro = request.getParameter("bairro");
			cidade = request.getParameter("cidade");
			cep = request.getParameter("cep");
			numero = new Integer(request.getParameter("numero"));
			complemento = request.getParameter("complemento");
			
			//objeto = new Empresa(nome,cnpj,endereco);
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
			objeto = dao.pesquisar(new Long(request.getParameter("id")));
			dao.remover(objeto);
			objetos = dao.listar();
			request.setAttribute("objetos", objetos);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_EMPRESA_LISTAR);
			dispatcher.forward(request, response);
		}
	}

}
