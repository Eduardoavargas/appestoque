package br.com.appestoque.restful.faturamento;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.util.Date;
import java.util.UUID;
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
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

import br.com.appestoque.BaseServlet;
import br.com.appestoque.dominio.cadastro.Empresa;
import br.com.appestoque.seguranca.Criptografia;
import br.com.appestoque.util.Constantes;
import br.com.appestoque.util.Conversor;

@SuppressWarnings("serial")
public class PedidoRestFul extends BaseServlet{
	
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

					
					
					try{
						
						String uuid = UUID.randomUUID().toString();
						JSONObject json = new JSONObject(request.getParameter("json"));
						
						Entity pedido = new Entity("Pedido");
						pedido.setProperty("numero", json.getString("numero"));
						
						pedido.setProperty("data", new Date(json.getLong("data")));
						pedido.setProperty("idRepresentante", representante.getKey().getId());
						pedido.setProperty("idCliente", json.getLong("idCliente"));
						pedido.setProperty("idEmpresa", representante.getProperty("idEmpresa"));
						pedido.setProperty("obs", json.getString("obs"));
						pedido.setProperty("uuid", uuid);
						pedido.setProperty("latitude", json.getDouble("latitude"));
						pedido.setProperty("longitude", json.getDouble("longitude"));
						
						datastore.put(pedido);
						
						JSONArray itens = json.getJSONArray("itens");
						for (int i = 0; i <= itens.length() - 1; ++i) {
							Entity item = new Entity("Item");
							item.setProperty("idProduto", itens.getJSONObject(i).getLong("idProduto"));
							item.setProperty("idPedido", pedido.getKey().getId());
							item.setProperty("quantidade", itens.getJSONObject(i).getDouble("quantidade"));
							item.setProperty("valor", itens.getJSONObject(i).getDouble("valor"));
							datastore.put(item);
						}
						
						
						Key key = null;
						key = KeyFactory.createKey(Empresa.class.getSimpleName(),(Long)representante.getProperty("idEmpresa"));
						Entity empresa = null;
						try {
							empresa = datastore.get(key);
						} catch (EntityNotFoundException e) {
							e.printStackTrace();
						}
						
						if(empresa.getProperty("emailPedido")!=null&&!empresa.getProperty("emailPedido").equals("")){
						
							StringBuffer corpo = new StringBuffer();
							
							corpo.append("<html>");
							corpo.append("<head>");
							corpo.append("</head>");
							corpo.append("<body>");
							corpo.append("<body>");
							corpo.append("<div style='font-family: 'Helvetica Neue', Arial, Helvetica, sans-serif; font-size: 13px; margin: 14px';>");
							corpo.append("<img src='http://appestoque.appspot.com/img/logo.jpg'/>");
							corpo.append("<h2 style='font-family: 'Helvetica Neue', Arial, Helvetica, sans-serif; margin: 0 0 16px; font-size: 18px; font-weight: normal'>Ol�, "+empresa.getProperty("nome")+".</h2>");
							
							corpo.append("<p>Por favor, clique no link abaixo para imprimir o pedido de venda:<br>");
							corpo.append("<a href='"+Constantes.URL+Constantes.URI_PEDIDO_VENDA+"?uuid="+uuid+"'");
							corpo.append("target='_blank'>"+Constantes.URL+Constantes.URI_PEDIDO_VENDA+"?uuid="+uuid+"</a></p>");
							
							corpo.append("<p style='font-family: 'Helvetica Neue', Arial, Helvetica, sans-serif; font-size: 13px; line-height: 18px; border-bottom: 1px solid rgb(238, 238, 238); padding-bottom: 10px; margin: 0 0 10px'>");
							corpo.append("<span style='font: italic 13px Georgia, serif; color: rgb(102, 102, 102)'>Equipe do Appestoque</span></p>");
							
							corpo.append("<p style='font-family: 'Helvetica Neue', Arial, Helvetica, sans-serif; margin-top: 5px; font-size: 10px; color: #888888'>");
							corpo.append("Se voc� recebeu essa mensagem por engano e n�o criou uma conta do Appestoque, clique"); 
							corpo.append(" <a href='mailto:suporte@appestoque.com.br?subject=[Cadastro]Mensagem por engano&&body='target='_blank'>n�o � minha conta</a>.</p>");
							
							corpo.append("<p style='font-family: 'Helvetica Neue', Arial, Helvetica, sans-serif; margin-top: 5px; font-size: 10px; color: #888888'>");
							corpo.append("Por favor n�o responda esta mensagem; ela foi enviada por um endere�o ");
							corpo.append("de e-mail n�o monitorado. Esta mensagem � relacionada ao seu uso do ");
							corpo.append("Appestoque. Para mais informa��es sobre a sua conta, por ");
							corpo.append("favor encaminhe um e-mail para o");
							corpo.append(" <a href='mailto:suporte@appestoque.com.br' target='_blank'>Suporte do Appestoque</a>.</p>");
							
							corpo.append("</div>");
							corpo.append("</body>");
							corpo.append("</html>");
							
							String assunto = Constantes.ASSUNTO_PEDIDO_VENDA + " - " + pedido.getProperty("numero");
							br.com.appestoque.Util.enviarEmail(empresa.getProperty("email").toString(),assunto,corpo);
							
						}
						
						response.setContentType("application/json;charset=UTF-8");
						PrintWriter out = response.getWriter();
						json = new JSONObject();
						json.put("id",pedido.getKey().getId());
						out.print(json);
						out.flush();
					
					} catch (JSONException e) {
						e.printStackTrace();
						response.setContentType("application/json;charset=UTF-8");
						PrintWriter out = response.getWriter();
						String json = "{'erro':'"+e.getMessage()+"'}";
						out.print(json);
						out.flush();
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