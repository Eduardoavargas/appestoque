package br.com.appestoque.controle.cadastro;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.appestoque.TipoBusca;
import br.com.appestoque.Constantes;
import br.com.appestoque.comum.Pagina;
import br.com.appestoque.controle.BaseControle;
import br.com.appestoque.dao.cadastro.BairroDAO;
import br.com.appestoque.dao.cadastro.CidadeDAO;
import br.com.appestoque.dao.cadastro.EmpresaDAO;
import br.com.appestoque.dominio.cadastro.Bairro;
import br.com.appestoque.dominio.cadastro.Empresa;

@SuppressWarnings("serial")
public class EmpresaControle extends BaseControle {

	private CidadeDAO cidadeDAO = null;
	private EmpresaDAO dao = null;
	private int primeiroRegistro;
	private List<Empresa> objetos = null;
	private String cnpj;
	private Integer numero;
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
			HttpServletRequest req = (HttpServletRequest) request;
	        HttpSession session = req.getSession();
	        Empresa objeto = (Empresa) session.getAttribute("empresa");
			request.setAttribute("objeto",objeto);
			RequestDispatcher dispatcher = null;
			if(request.getParameter("tipo").equals("empresa")){
				dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_EMPRESA_EDITAR);
			}else if(request.getParameter("tipo").equals("configuracao")){
				dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_CONFIGURACAO_EDITAR);
			}
			dispatcher.forward(request, response);
		}else if(request.getParameter("acao").equals("pesquisar")) {
			request.setAttribute("primeiroRegistro",request.getParameter("primeiroRegistro"));
			request.setAttribute("totalRegistros",request.getParameter("totalRegistros"));
			request.setAttribute("registrosPorPagina",request.getParameter("registrosPorPagina"));
			cnpj = request.getParameter("cnpj")==null||request.getParameter("cnpj").equals("")?null:request.getParameter("cnpj");
			primeiroRegistro = Integer.parseInt(request.getParameter("primeiroRegistro"));			
			objetos = null;
			if(request.getParameter("paginar")==null){
				totalRegistros = dao.contar(cnpj);				
				objetos = dao.pesquisar(cnpj,primeiroRegistro,Constantes.REGISTROS_POR_PAGINA,TipoBusca.ANSIOSA);
				request.setAttribute("totalRegistros",totalRegistros);
				request.setAttribute("primeiroRegistro",primeiroRegistro);
			}else if(request.getParameter("paginar").equals("proximo")){
				primeiroRegistro += Constantes.REGISTROS_POR_PAGINA;
				objetos = dao.pesquisar(cnpj,primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA,TipoBusca.ANSIOSA);
				paginar(primeiroRegistro);
				request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
			}else if(request.getParameter("paginar").equals("anterior")){
				primeiroRegistro -= Constantes.REGISTROS_POR_PAGINA;
				objetos = dao.pesquisar(cnpj,primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA,TipoBusca.ANSIOSA);
				paginar(primeiroRegistro);
				request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
			}else if(request.getParameter("paginar").equals("ultimo")){
				primeiroRegistro = totalRegistros - ((totalRegistros % Constantes.REGISTROS_POR_PAGINA != 0) ? totalRegistros % Constantes.REGISTROS_POR_PAGINA : Constantes.REGISTROS_POR_PAGINA);
				objetos = dao.pesquisar(cnpj,primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA,TipoBusca.ANSIOSA);
				paginar(primeiroRegistro);
				request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
			}else if(request.getParameter("paginar").equals("primeiro")){
				primeiroRegistro = 0;
				objetos = dao.pesquisar(cnpj,primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA,TipoBusca.ANSIOSA);
				paginar(primeiroRegistro);
				request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
			}
			request.setAttribute("objetos",objetos);
			request.setAttribute("numero",numero);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_EMPRESA_LISTAR);
			dispatcher.forward(request, response);
		} else if(request.getParameter("acao").equals("criar")) {
			
			cidadeDAO = new CidadeDAO((PersistenceManager) request.getAttribute("pm"));
			request.setAttribute("cidades", cidadeDAO.listar());
			
			request.setAttribute("bairros", null);
			
			request.setAttribute("objeto", objeto);			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_EMPRESA_EDITAR);
			dispatcher.forward(request, response);
		} else if(request.getParameter("acao").equals("editar")) {
			
			cidadeDAO = new CidadeDAO((PersistenceManager) request.getAttribute("pm"));
			request.setAttribute("cidades", cidadeDAO.listar());
			
			objeto = dao.pesquisar(new Long(request.getParameter("id")));
			
			BairroDAO bairroDAO = new BairroDAO((PersistenceManager) request.getAttribute("pm"));
			List<Bairro> bairros = bairroDAO.listar();
			request.setAttribute("bairros", bairros);
			
			request.setAttribute("objeto",objeto);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_EMPRESA_EDITAR);
			dispatcher.forward(request, response);
		} else if(request.getParameter("acao").equals("modificar")) {
			
			Empresa empresa = dao.pesquisar(new Long(request.getParameter("id")));
			boolean modificar = false;
			Integer numero = new Integer(request.getParameter("numero"));

			modificar = (!empresa.getEndereco().equals(request.getParameter("endereco")))||
						(!empresa.getNumero().equals(numero))||
						(!empresa.getCep().equals(request.getParameter("cep")))||
						(!empresa.getComplemento().equals(request.getParameter("complemento")))||
						(!empresa.getBairro().equals(request.getParameter("bairro")))||
						(!empresa.getCidade().equals(request.getParameter("cidade")));
			
			if (modificar) {

				if (!empresa.getEndereco().equals(
						request.getParameter("endereco"))) {
					empresa.setEndereco(request.getParameter("endereco"));
				}

				if (!empresa.getNumero().equals(numero)) {
					empresa.setNumero(numero);
				}

				if (!empresa.getCep().equals(request.getParameter("cep"))) {
					empresa.setCep(request.getParameter("cep"));
				}

				if (!empresa.getComplemento().equals(
						request.getParameter("complemento"))) {
					empresa.setComplemento(request.getParameter("complemento"));
				}

				if (!empresa.getBairro().equals(request.getParameter("bairro"))) {
					empresa.setBairro(request.getParameter("bairro"));
				}

				if (!empresa.getCidade().equals(request.getParameter("cidade"))) {
					empresa.setCidade(request.getParameter("cidade"));
				}

				dao.adicionar(empresa);
				
				HttpServletRequest req = (HttpServletRequest) request;
		        HttpSession session = req.getSession();
		        session.setAttribute("empresa", empresa);
				
			}
			
			ResourceBundle bundle = ResourceBundle.getBundle("i18n",request.getLocale());
			request.setAttribute("mensagem",bundle.getString("app.mensagem.sucesso"));
			request.setAttribute("primeiroRegistro",0);
			request.setAttribute("totalRegistros",0);
			request.setAttribute("registrosPorPagina",Constantes.REGISTROS_POR_PAGINA);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_MENU);
			dispatcher.forward(request, response);
		} else if(request.getParameter("acao").equals("remover")) {
			objeto = dao.pesquisar(new Long(request.getParameter("id")));
			dao.remover(objeto);
			objetos = dao.listar();
			request.setAttribute("objetos", objetos);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_EMPRESA_LISTAR);
			dispatcher.forward(request, response);
		} 
		
	}

}