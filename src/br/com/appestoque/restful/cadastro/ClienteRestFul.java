package br.com.appestoque.restful.cadastro;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Future;
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

import com.google.appengine.api.datastore.AsyncDatastoreService;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;

import br.com.appestoque.TipoBusca;
import br.com.appestoque.dao.cadastro.ClienteDAO;
import br.com.appestoque.dominio.cadastro.Bairro;
import br.com.appestoque.dominio.cadastro.Cidade;
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
		
		ResourceBundle bundle = null;
		bundle = ResourceBundle.getBundle("i18n",request.getLocale());
		if(request.getParameter("uuid")!=null){
			DatastoreService datastore = null;
			Query query = null;
			PreparedQuery preparedQuery = null;
			
			datastore = DatastoreServiceFactory.getDatastoreService();
			
			query = new Query("Representante");
			query.addFilter("uuid",FilterOperator.EQUAL,request.getParameter("uuid"));
			Entity representante = datastore.prepare(query).asSingleEntity();
			
			if(representante!=null){
				Long idEmpresa = (Long) representante.getProperty("idEmpresa");
				Long idBairro = null, idCidade = null;
				query = new Query("Cliente");
				query.addFilter("idEmpresa",FilterOperator.EQUAL,idEmpresa);
				preparedQuery = datastore.prepare(query);
				Iterable<Entity> clientes = preparedQuery.asIterable();
				JSONArray objetos = new JSONArray();
				 
				try {
					for (Entity cliente : clientes) {
						Map<String,Object> properties = cliente.getProperties();						
						JSONObject objeto = new JSONObject();
						objeto.put("_id", cliente.getKey().getId());
						objeto.put("nome",properties.get("nome"));
						objeto.put("cnpj",properties.get("cnpj"));
						objeto.put("endereco",properties.get("endereco"));
						objeto.put("numero",properties.get("numero"));
						objeto.put("cep",properties.get("cep"));
						objeto.put("complemento",properties.get("complemento"));
						
						idBairro = (Long) properties.get("idBairro");
						
						Key key = null;
						key = KeyFactory.createKey(Bairro.class.getSimpleName(),idBairro.intValue());
						Entity bairro = null;
						
						try {
							bairro = datastore.get(key);
						} catch (EntityNotFoundException e) {
						}
						
						if(bairro!=null){
							objeto.put("bairro",bairro.getProperty("nome"));
							idCidade = (Long) bairro.getProperty("idCidade");
							key = KeyFactory.createKey(Cidade.class.getSimpleName(),idCidade.intValue());
							Entity cidade = null;
							try {
								cidade = datastore.get(key);
								objeto.put("cidade",cidade.getProperty("nome"));
							} catch (EntityNotFoundException e) {
							}
						}						
						objetos.put(objeto);
					}
					response.setContentType("application/json;charset=UTF-8");
					response.setHeader("Content-Encoding", "gzip");
					PrintWriter out = response.getWriter();
					out.print(objetos);
					out.flush();
				} catch (JSONException e) {
					e.printStackTrace();
				}	
				
			}else{
				logger.log(Level.SEVERE,bundle.getString("app.mensagem.uuid.invalido"));
				throw new IOException();
			}
			
		}else{
			logger.log(Level.SEVERE,bundle.getString("app.mensagem.uuid.invalido"));
			throw new IOException();
		}
		
//		if(request.getParameter("sincronismo")!=null){
//			PersistenceManager pm = (PersistenceManager) request.getAttribute("pm");
//			ClienteDAO clienteDAO = new ClienteDAO(pm);
//			HttpSession httpSession = request.getSession();
//			Empresa empresa = (Empresa) httpSession.getAttribute("empresa");
//			JSONArray objetos = new JSONArray();
//			try {
//				for (Cliente cliente : clienteDAO.listar(empresa.getId(),TipoBusca.ANSIOSA)) {
//					JSONObject objeto = new JSONObject();
//					objeto.put("_id",cliente.getId());			
//					objeto.put("nome",cliente.getNome());
//					objeto.put("cnpj",cliente.getCnpj());
//					objeto.put("endereco",cliente.getEndereco());
//					objeto.put("numero",cliente.getNumero());
//					objeto.put("cep",cliente.getCep());
//					objeto.put("complemento",cliente.getComplemento());
//					objeto.put("bairro",cliente.getBairro().getNome());
//					objeto.put("cidade",cliente.getBairro().getCidade().getNome());
//					objetos.put(objeto);
//				}
//			} catch (JSONException e) {
//				e.printStackTrace();
//			}
//			response.setContentType("application/json;charset=UTF-8");
//			response.setHeader("Content-Encoding", "gzip");
//			PrintWriter out = response.getWriter();
//			out.print(objetos);
//			out.flush();
//		}
		
	}
		
}