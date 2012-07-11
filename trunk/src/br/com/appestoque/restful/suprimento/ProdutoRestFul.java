package br.com.appestoque.restful.suprimento;

import java.io.IOException;
import java.io.PrintWriter;

import java.security.InvalidKeyException;
import java.util.logging.Level;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.appestoque.BaseServlet;
import br.com.appestoque.seguranca.Criptografia;
import br.com.appestoque.util.Constantes;
import br.com.appestoque.util.Conversor;

import com.google.appengine.api.datastore.AsyncDatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

@SuppressWarnings("serial")
public class ProdutoRestFul extends BaseServlet{
	
	public void processServer(HttpServletRequest request, HttpServletResponse response) throws IOException {
		super.processServer(request, response);	
		if(request.getParameter("email")!=null&&request.getParameter("senha")!=null){
			AsyncDatastoreService datastore = null;
			Query query = null;
			datastore = DatastoreServiceFactory.getAsyncDatastoreService();
			String senha = null;
			try{
				Criptografia criptografia = new Criptografia();
				senha = criptografia.decifrar(Conversor.stringToByte(request.getParameter("senha"),Constantes.DELIMITADOR));
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (BadPaddingException e) {
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			query = new Query("Usuario");
			query.setFilter(new FilterPredicate("email",FilterOperator.EQUAL,request.getParameter("email")));
			Entity usuario = datastore.prepare(query).asSingleEntity();
			
			if(usuario!=null&&usuario.getProperty("senha").equals(senha)){
				
				query = new Query("Representante");
				query.setFilter(new FilterPredicate("idUsuario",FilterOperator.EQUAL,usuario.getKey().getId()));
				Entity representante = datastore.prepare(query).asSingleEntity();
				
				if(representante!=null){
					
					query = new Query("Produto");
					query.setFilter(new FilterPredicate("idEmpresa",FilterOperator.EQUAL,representante.getProperty("idEmpresa")));
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
					logger.log(Level.SEVERE,bundle.getString("app.mensagem.representante.nao.vinculado.usuario"));
					throw new IOException();
				}
				
			}else if(!usuario.getProperty("senha").equals(senha)){
				logger.log(Level.SEVERE,bundle.getString("app.mensagem.senha.nao.confere"));
				throw new IOException();
			}else{
				logger.log(Level.SEVERE,bundle.getString("app.mensagem.email.nao.localizado"));
				throw new IOException();
			}
			
//			query = new Query("Representante");
//			query.setFilter(new FilterPredicate("uuid",FilterOperator.EQUAL,uuid));
//			query.addFilter("uuid",FilterOperator.EQUAL,request.getParameter("uuid"));
//			Entity representante = datastore.prepare(query).asSingleEntity();
//			
//			if(representante!=null){
//				Long idEmpresa = (Long) representante.getProperty("idEmpresa");
//				
//				query = new Query("Produto");
//				query.addFilter("idEmpresa",FilterOperator.EQUAL,idEmpresa);
//				PreparedQuery preparedQuery = datastore.prepare(query);
//				Iterable<Entity> produtos = preparedQuery.asIterable();
//				
//				JSONArray objetos = new JSONArray();
//				try {
//					for (Entity produto : produtos) {
//						Map<String,Object> properties = produto.getProperties();						
//						JSONObject objeto = new JSONObject();
//						objeto.put("_id", produto.getKey().getId());
//						objeto.put("nome", properties.get("nome"));
//						objeto.put("numero", properties.get("numero"));
//						objeto.put("preco", properties.get("preco"));
//						objetos.put(objeto);
//					}
//					response.setContentType("application/json;charset=UTF-8");
//					response.setHeader("Content-Encoding", "gzip");
//					PrintWriter out = response.getWriter();
//					out.print(objetos);
//					out.flush();
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//			}else{
//				logger.log(Level.SEVERE,bundle.getString("app.mensagem.uuid.invalido"));
//				throw new IOException();
//			}
			
		}else{
			logger.log(Level.SEVERE,bundle.getString("app.mensagem.email.nao.enviado"));
			throw new IOException();
		}
		
	}

}