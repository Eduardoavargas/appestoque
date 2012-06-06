package br.com.appestoque.restful.cadastro;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.appestoque.TipoBusca;
import br.com.appestoque.dao.cadastro.ClienteDAO;
import br.com.appestoque.dominio.cadastro.Cliente;
import br.com.appestoque.dominio.cadastro.Empresa;
import br.com.appestoque.restful.cadastro.enviar.ClientesRESTful;

@SuppressWarnings("serial")
public class ClienteRestFul extends HttpServlet{
	
	private static final Logger logger = Logger.getLogger(ClienteRestFul.class.getCanonicalName());
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("sincronismo")!=null){
			PersistenceManager pm = (PersistenceManager) request.getAttribute("pm");
			ClienteDAO clienteDAO = new ClienteDAO(pm);
			HttpSession httpSession = request.getSession();
			Empresa empresa = (Empresa) httpSession.getAttribute("empresa");
			JSONArray objetos = new JSONArray();
			try {
				for (Cliente cliente : clienteDAO.listar(empresa.getId(),TipoBusca.ANSIOSA)) {
					logger.log(Level.SEVERE,"Cliente: " + cliente.getNome());
					JSONObject objeto = new JSONObject();
					objeto.put("_id",cliente.getId());			
					objeto.put("nome",cliente.getNome());
					objeto.put("cnpj",cliente.getCnpj());
					objeto.put("endereco",cliente.getEndereco());
					objeto.put("numero",cliente.getNumero());
					objeto.put("cep",cliente.getCep());
					objeto.put("complemento",cliente.getComplemento());
					objeto.put("bairro",cliente.getBairro().getNome());
					objeto.put("cidade",cliente.getBairro().getCidade().getNome());
					objetos.put(objeto);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			response.setContentType("application/json;charset=UTF-8");
			response.setHeader("Content-Encoding", "gzip");
			PrintWriter out = response.getWriter();
			out.print(objetos);
			out.flush();
		}
	}
		
}