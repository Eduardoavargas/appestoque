package br.com.appestoque.restful.cadastro;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.util.Map;
import java.util.logging.Level;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

import br.com.appestoque.BaseServlet;
import br.com.appestoque.dominio.cadastro.Bairro;
import br.com.appestoque.dominio.cadastro.Cidade;
import br.com.appestoque.seguranca.Criptografia;
import br.com.appestoque.util.Constantes;
import br.com.appestoque.util.Conversor;

@SuppressWarnings("serial")
public class ClienteRestFul extends BaseServlet{
	
	public void processServer(HttpServletRequest request, HttpServletResponse response) throws IOException {
		super.processServer(request, response);
		if(request.getParameter("email")!=null&&request.getParameter("senha")!=null){
			DatastoreService datastore = null;
			Query query = null;
			datastore = DatastoreServiceFactory.getDatastoreService();
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
					
					query = new Query("Cliente");
					query.setFilter(new FilterPredicate("idEmpresa",FilterOperator.EQUAL,representante.getProperty("idEmpresa")));
					PreparedQuery preparedQuery = datastore.prepare(query);
					Iterable<Entity> clientes = preparedQuery.asIterable();
					
					JSONArray objetos = new JSONArray();
					try {
						for (Entity cliente : clientes) {
							Map<String,Object> properties = cliente.getProperties();						
							JSONObject objeto = new JSONObject();
							objeto.put("_id", cliente.getKey().getId());
							objeto.put("nome", properties.get("nome"));
							objeto.put("razao", properties.get("razao"));
							objeto.put("cnpj", properties.get("cnpj"));
							objeto.put("endereco", properties.get("endereco"));
							objeto.put("numero", properties.get("numero"));
							objeto.put("cep", properties.get("cep"));
							objeto.put("complemento", properties.get("complemento"));
							
							Long idBairro = (Long) properties.get("idBairro");
							
							Key key = null;
							
							try {
								key = KeyFactory.createKey(Bairro.class.getSimpleName(),idBairro.intValue());
							}catch(NullPointerException e){
							}
							
							Entity bairro = null;
							
							try {
								bairro = datastore.get(key);
							} catch (EntityNotFoundException e) {
							}catch(NullPointerException e){
							}
							
							if(bairro!=null){
								objeto.put("bairro",bairro.getProperty("nome"));
								Long idCidade = (Long) bairro.getProperty("idCidade");
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
			
		}else if(request.getParameter("email")==null){
			logger.log(Level.SEVERE,bundle.getString("app.mensagem.email.nao.enviado"));
			throw new IOException();
		}else if(request.getParameter("uuid")==null){
			logger.log(Level.SEVERE,bundle.getString("app.mensagem.uuid.nao.enviado"));
			throw new IOException();
		}
		
	}
		
}