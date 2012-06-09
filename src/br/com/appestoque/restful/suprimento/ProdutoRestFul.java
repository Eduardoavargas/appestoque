package br.com.appestoque.restful.suprimento;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.appestoque.restful.suprimento.enviar.ProdutosRESTful;

import com.google.appengine.api.datastore.AsyncDatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;

@SuppressWarnings("serial")
public class ProdutoRestFul extends HttpServlet{
	
	private static final Logger logger = Logger.getLogger(ProdutosRESTful.class.getCanonicalName());
	
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
			
			AsyncDatastoreService datastore = null;
			Query query = null;
			
			datastore = DatastoreServiceFactory.getAsyncDatastoreService();
			
			query = new Query("Representante");
			query.addFilter("uuid",FilterOperator.EQUAL,request.getParameter("uuid"));
			Entity representante = datastore.prepare(query).asSingleEntity();
			
			if(representante!=null){
				Long idEmpresa = (Long) representante.getProperty("idEmpresa");
				
				query = new Query("Produto");
				query.addFilter("idEmpresa",FilterOperator.EQUAL,idEmpresa);
				PreparedQuery preparedQuery = datastore.prepare(query);
				Iterable<Entity> produtos = preparedQuery.asIterable();
				
				JSONArray objetos = new JSONArray();
				try {
					for (Entity produto : produtos) {
						Map<String,Object> properties = produto.getProperties();						
						JSONObject objeto = new JSONObject();
						objeto.put("_id", produto.getKey().getId());
						objeto.put("nome", properties.get("nome"));
						objeto.put("numero", properties.get("numero"));
						objeto.put("preco", properties.get("preco"));
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
		
	}

}