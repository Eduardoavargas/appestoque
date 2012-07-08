package br.com.appestoque.controle.cadastro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.appestoque.TipoBusca;
import br.com.appestoque.Constantes;
import br.com.appestoque.comum.Pagina;
import br.com.appestoque.controle.BaseControle;
import br.com.appestoque.dao.DAOException;
import br.com.appestoque.dao.cadastro.BairroDAO;
import br.com.appestoque.dao.cadastro.CidadeDAO;
import br.com.appestoque.dao.cadastro.RepresentanteDAO;
import br.com.appestoque.dao.seguranca.UsuarioDAO;
import br.com.appestoque.dominio.cadastro.Bairro;
import br.com.appestoque.dominio.cadastro.Representante;

@SuppressWarnings("serial")
public class RepresentanteControle extends BaseControle{

	private CidadeDAO cidadeDAO = null;
	private UsuarioDAO usuarioDAO = null;
	private RepresentanteDAO dao = null;
	private int primeiroRegistro;
	private List<Representante> objetos = null;
	
	private String cpf;
	private String nome;
	private Long idBairro;
	private Long idUsuario;
	private String cep;
	private Integer numero;
	private String complemento;
	private String endereco;
	private String uuid;
	private Representante objeto;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dao = new RepresentanteDAO((PersistenceManager) request.getAttribute("pm"));
		if(request.getParameter("acao").equals("iniciar")) {
			primeiroRegistro = 0;
			objetos = dao.pesquisar(null,getId(request),primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA,TipoBusca.ANSIOSA);
			paginar(primeiroRegistro);			
			request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
			request.setAttribute("totalRegistros", objetos.size());
			request.setAttribute("registrosPorPagina",Constantes.REGISTROS_POR_PAGINA);
			request.setAttribute("objetos",objetos);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_REPRESENTANTE_LISTAR);
			dispatcher.forward(request, response);
		}else if(request.getParameter("acao").equals("pesquisar")) {
			request.setAttribute("primeiroRegistro",request.getParameter("primeiroRegistro"));
			request.setAttribute("totalRegistros",request.getParameter("totalRegistros"));
			request.setAttribute("registrosPorPagina",request.getParameter("registrosPorPagina"));
			cpf = request.getParameter("cpf")==null||request.getParameter("cpf").equals("")?null:request.getParameter("cpf");
			primeiroRegistro = Integer.parseInt(request.getParameter("primeiroRegistro"));			
			objetos = null;
			if(request.getParameter("paginar")==null){
				totalRegistros = dao.contar(cpf,getId(request));				
				objetos = dao.pesquisar(cpf,getId(request),primeiroRegistro,Constantes.REGISTROS_POR_PAGINA,TipoBusca.ANSIOSA);
				request.setAttribute("totalRegistros",totalRegistros);
				request.setAttribute("primeiroRegistro",primeiroRegistro);
			}else if(request.getParameter("paginar").equals("proximo")){
				primeiroRegistro += Constantes.REGISTROS_POR_PAGINA;
				objetos = dao.pesquisar(cpf,getId(request),primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA,TipoBusca.ANSIOSA);
				paginar(primeiroRegistro);
				request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
			}else if(request.getParameter("paginar").equals("anterior")){
				primeiroRegistro -= Constantes.REGISTROS_POR_PAGINA;
				objetos = dao.pesquisar(cpf,getId(request),primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA,TipoBusca.ANSIOSA);
				paginar(primeiroRegistro);
				request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
			}else if(request.getParameter("paginar").equals("ultimo")){
				primeiroRegistro = totalRegistros - ((totalRegistros % Constantes.REGISTROS_POR_PAGINA != 0) ? totalRegistros % Constantes.REGISTROS_POR_PAGINA : Constantes.REGISTROS_POR_PAGINA);
				objetos = dao.pesquisar(cpf,getId(request),primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA,TipoBusca.ANSIOSA);
				paginar(primeiroRegistro);
				request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
			}else if(request.getParameter("paginar").equals("primeiro")){
				primeiroRegistro = 0;
				objetos = dao.pesquisar(cpf,getId(request),primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA,TipoBusca.ANSIOSA);
				paginar(primeiroRegistro);
				request.setAttribute("primeiroRegistro",getPrimeiroRegistro());
			}
			request.setAttribute("objetos",objetos);
			request.setAttribute("numero",numero);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_REPRESENTANTE_LISTAR);
			dispatcher.forward(request, response);
		} else if(request.getParameter("acao").equals("criar")) {
			
			usuarioDAO = new UsuarioDAO((PersistenceManager) request.getAttribute("pm"));
			request.setAttribute("usuarios", usuarioDAO.listar(getId(request)));
			
			cidadeDAO = new CidadeDAO((PersistenceManager) request.getAttribute("pm"));
			request.setAttribute("cidades", cidadeDAO.listar(getId(request)));
			
			request.setAttribute("bairros", null);
			
			objeto = new Representante();
			request.setAttribute("objeto", objeto);			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_REPRESENTANTE_EDITAR);
			dispatcher.forward(request, response);
			
		} else if(request.getParameter("acao").equals("editar")) {
			
			usuarioDAO = new UsuarioDAO((PersistenceManager) request.getAttribute("pm"));
			request.setAttribute("usuarios", usuarioDAO.listar(getId(request)));
			
			cidadeDAO = new CidadeDAO((PersistenceManager) request.getAttribute("pm"));
			request.setAttribute("cidades", cidadeDAO.listar(getId(request)));
			
			objeto = dao.pesquisar(new Long(request.getParameter("id")));
			
			BairroDAO bairroDAO = new BairroDAO((PersistenceManager) request.getAttribute("pm"));
			objeto.setBairro(bairroDAO.pesquisar(objeto.getIdBairro(), TipoBusca.ANSIOSA));
			request.setAttribute("idBairro",objeto.getIdBairro());
			List<Bairro> bairros = new ArrayList<Bairro>();
			bairros.add(objeto.getBairro());
			request.setAttribute("bairros", bairros);
			
			request.setAttribute("idCidade",objeto.getIdBairro()!=null?objeto.getBairro().getIdCidade():null);
			
			request.setAttribute("idUsuario",objeto.getIdUsuario()!=null?objeto.getIdUsuario():null);
			
			request.setAttribute("objeto",objeto);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_REPRESENTANTE_EDITAR);
			dispatcher.forward(request, response);
			
		} else if(request.getParameter("acao").equals("modificar")) {
			nome = request.getParameter("nome");
			cpf = request.getParameter("cpf");
			idBairro = new Long(request.getParameter("idBairro"));
			cep = request.getParameter("cep");
			numero = new Integer(request.getParameter("numero"));
			complemento = request.getParameter("complemento");
			endereco = request.getParameter("endereco");
			uuid = request.getParameter("uuid");
			idBairro = new Long(request.getParameter("idBairro"));
			idUsuario = new Long(request.getParameter("idUsuario"));
			objeto = new Representante(nome,cpf,endereco,complemento,numero,cep,idBairro,getId(request),idUsuario,uuid);
			objeto.setId(  request.getParameter("id")==null||request.getParameter("id").equals("")?null:new Long(request.getParameter("id")));
			dao.criar(objeto);
			objetos = dao.pesquisar(null,getId(request),primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA,TipoBusca.ANSIOSA);
			ResourceBundle bundle = ResourceBundle.getBundle("i18n",request.getLocale());
			request.setAttribute("mensagem",bundle.getString("app.mensagem.sucesso"));
			request.setAttribute("primeiroRegistro",0);
			request.setAttribute("totalRegistros",0);
			request.setAttribute("registrosPorPagina",Constantes.REGISTROS_POR_PAGINA);
			request.setAttribute("objetos",objetos);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_REPRESENTANTE_LISTAR);
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
			objetos = dao.pesquisar(null,getId(request),primeiroRegistro,primeiroRegistro+Constantes.REGISTROS_POR_PAGINA,TipoBusca.ANSIOSA);
			request.setAttribute("objetos", objetos);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_REPRESENTANTE_LISTAR);
			dispatcher.forward(request, response);
		} 
		
	}
	
}