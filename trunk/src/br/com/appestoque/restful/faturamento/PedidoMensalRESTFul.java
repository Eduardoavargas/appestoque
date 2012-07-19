package br.com.appestoque.restful.faturamento;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;

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
import br.com.appestoque.dominio.cadastro.Bairro;
import br.com.appestoque.dominio.cadastro.Cidade;
import br.com.appestoque.dominio.cadastro.Cliente;
import br.com.appestoque.util.Tempo;

@SuppressWarnings("serial")
public class PedidoMensalRESTFul extends BaseServlet{
	
	public void processServer(HttpServletRequest request, HttpServletResponse response) throws IOException {
		super.processServer(request, response);
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("Pedido");
		query.setFilter(new FilterPredicate("data",FilterOperator.GREATER_THAN,Tempo.primeiroDiaMes(new Date())));
		query.setFilter(new FilterPredicate("data",FilterOperator.LESS_THAN,Tempo.ultimoDiaMes(new Date())));
		Iterable<Entity> pedidos = datastore.prepare(query).asIterable();
		JSONArray objetos = new JSONArray();
		for (Entity pedido : pedidos) {
			Map<String,Object> properties = pedido.getProperties();
			
			Key key = KeyFactory.createKey(Cliente.class.getSimpleName(),Integer.parseInt(properties.get("idCliente").toString()));
			Entity cliente = null;
			
			try {
				
				JSONObject objeto = new JSONObject();
				cliente = datastore.get(key);
				objeto.put("cliente",cliente.getProperty("nome"));
				objeto.put("endereco",cliente.getProperty("endereco"));
				
				key = KeyFactory.createKey(Bairro.class.getSimpleName(),Integer.parseInt(properties.get("idBairro").toString()));
				Entity bairro = datastore.get(key);
				
				objeto.put("bairro",bairro.getProperty("nome"));
				
				key = KeyFactory.createKey(Cidade.class.getSimpleName(),Integer.parseInt(properties.get("idCidade").toString()));
				Entity cidade = datastore.get(key);
				
				objeto.put("cidade",cidade.getProperty("nome"));
				
				objeto.put("latitude", pedido.getProperty("latitude"));		
				objeto.put("longitude", pedido.getProperty("longitude"));
				objeto.put("uuid",pedido.getProperty("uuid"));
				objeto.put("data", Tempo.stringParaData(pedido.getProperty("data").toString()) );
				
				query = new Query("Item");
				query.setFilter(new FilterPredicate("idPedido",FilterOperator.EQUAL,pedido.getKey().getId()));
				Iterable<Entity> itens = datastore.prepare(query).asIterable();
				Double valor = new Double(0);
				for (Entity item : itens) {
					valor += ((Double)item.getProperty("quantidade"))*((Double)item.getProperty("valor"));
				}
				
				objeto.put("valor",valor);
				
				objetos.put(objeto);
				
			} catch (JSONException e){
				logger.log(Level.SEVERE,e.getMessage());
			} catch (EntityNotFoundException e) {
				logger.log(Level.SEVERE,e.getMessage());
			}
			
		}
		
		JSONObject objs = new JSONObject();
		try {
			objs.put("pedidos", objetos);
		} catch (JSONException e) {
			logger.log(Level.SEVERE,e.getMessage());
		}
		
		response.setContentType("application/json;charset=UTF-8");
        PrintWriter printWriter;
		printWriter = response.getWriter();
		printWriter.print(pedidos);
		printWriter.flush();
		
	}

}