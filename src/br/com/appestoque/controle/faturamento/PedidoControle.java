package br.com.appestoque.controle.faturamento;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.appestoque.Constantes;
import br.com.appestoque.TipoBusca;
import br.com.appestoque.comum.Pagina;
import br.com.appestoque.controle.BaseControle;
import br.com.appestoque.dao.cadastro.ClienteDAO;
import br.com.appestoque.dao.cadastro.RepresentanteDAO;
import br.com.appestoque.dao.faturamento.PedidoDAO;
import br.com.appestoque.dominio.faturamento.Pedido;

@SuppressWarnings("serial")
public class PedidoControle extends BaseControle{

	private PedidoDAO dao = null;
	private int primeiroRegistro;
	private List<Pedido> objetos = null;
	private String numero;
	private Pedido objeto;	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dao = new PedidoDAO((PersistenceManager) request.getAttribute("pm"));
		if(request.getParameter("acao").equals("iniciar")) {
			primeiroRegistro = 0;
			objetos = dao.pesquisar(numero,getId(request),primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA,TipoBusca.ANSIOSA);
			paginar(primeiroRegistro);			
			request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
			request.setAttribute("totalRegistros", objetos.size());
			request.setAttribute("registrosPorPagina",Constantes.REGISTROS_POR_PAGINA);
			request.setAttribute("objetos",objetos);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_PEDIDO_LISTAR);
			dispatcher.forward(request, response);
		}else if(request.getParameter("acao").equals("pesquisar")) {
			request.setAttribute("primeiroRegistro",request.getParameter("primeiroRegistro"));
			request.setAttribute("totalRegistros",request.getParameter("totalRegistros"));
			request.setAttribute("registrosPorPagina",request.getParameter("registrosPorPagina"));
			numero = request.getParameter("numero")==null||request.getParameter("numero").equals("")?null:request.getParameter("numero");
			primeiroRegistro = Integer.parseInt(request.getParameter("primeiroRegistro"));			
			objetos = null;
			if(request.getParameter("paginar")==null){
				objetos = dao.pesquisar(numero,getId(request),primeiroRegistro,Constantes.REGISTROS_POR_PAGINA,TipoBusca.ANSIOSA);
				request.setAttribute("totalRegistros",objetos.size());
				request.setAttribute("primeiroRegistro",primeiroRegistro);
			}else if(request.getParameter("paginar").equals("proximo")){
				primeiroRegistro += Constantes.REGISTROS_POR_PAGINA;
				objetos = dao.pesquisar(numero,getId(request),primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA,TipoBusca.ANSIOSA);
				paginar(primeiroRegistro);
				request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
				request.setAttribute("totalRegistros",objetos.size());
			}else if(request.getParameter("paginar").equals("anterior")){
				primeiroRegistro -= Constantes.REGISTROS_POR_PAGINA;
				objetos = dao.pesquisar(numero,getId(request),primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA,TipoBusca.ANSIOSA);
				paginar(primeiroRegistro);
				request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
				request.setAttribute("totalRegistros",objetos.size());
			}else if(request.getParameter("paginar").equals("ultimo")){
//				primeiroRegistro = totalRegistros - ((totalRegistros % Constantes.REGISTROS_POR_PAGINA != 0) ? totalRegistros % Constantes.REGISTROS_POR_PAGINA : Constantes.REGISTROS_POR_PAGINA);
//				objetos = dao.pesquisar(nome,getId(request),primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA);
//				paginar(primeiroRegistro);
//				request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
			}else if(request.getParameter("paginar").equals("primeiro")){
				primeiroRegistro = 0;
				objetos = dao.pesquisar(numero,getId(request),primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA,TipoBusca.ANSIOSA);
				paginar(primeiroRegistro);
				request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
				request.setAttribute("totalRegistros",objetos.size());
			}
			request.setAttribute("objetos",objetos);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_PEDIDO_LISTAR);
			dispatcher.forward(request, response);
		} else if(request.getParameter("acao").equals("criar")) {
			
			ClienteDAO clienteDAO = new ClienteDAO((PersistenceManager) request.getAttribute("pm"));
			request.setAttribute("clientes", clienteDAO.listar());
			
			RepresentanteDAO representanteDAO = new RepresentanteDAO((PersistenceManager) request.getAttribute("pm"));
			request.setAttribute("representantes", representanteDAO.listar());
			
			objeto = new Pedido();
			request.setAttribute("objeto", objeto);			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_PEDIDO_EDITAR);
			dispatcher.forward(request, response);
		} else if(request.getParameter("acao").equals("editar")) {
			objeto = dao.pesquisar(new Long(request.getParameter("id")),TipoBusca.ANSIOSA);
			request.setAttribute("idCliente",objeto.getIdCliente());
			request.setAttribute("idRepresentante",objeto.getIdRepresentante());
			request.setAttribute("objeto",objeto);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_PEDIDO_EDITAR);
			dispatcher.forward(request, response);
		} else if(request.getParameter("acao").equals("modificar")) {
			objeto = new Pedido();
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