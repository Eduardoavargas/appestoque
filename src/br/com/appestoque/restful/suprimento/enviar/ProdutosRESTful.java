package br.com.appestoque.restful.suprimento.enviar;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.util.logging.Level;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.appestoque.BaseServlet;
import br.com.appestoque.seguranca.Criptografia;
import br.com.appestoque.seguranca.HashCode;
import br.com.appestoque.util.Constantes;
import br.com.appestoque.util.Conversor;

import com.google.appengine.api.datastore.AsyncDatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.PreparedQuery.TooManyResultsException;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.gson.stream.JsonReader;

@SuppressWarnings("serial")
public class ProdutosRESTful extends BaseServlet{
	
	public void processServer(HttpServletRequest request, HttpServletResponse response) throws IOException {
		super.processServer(request, response);
		String uuid = null;
		Entity empresa = null;
		Query query = null;
		JsonReader reader = new JsonReader(new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF8")));
		AsyncDatastoreService datastore = DatastoreServiceFactory.getAsyncDatastoreService();
		reader.beginObject();
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals("uuid")) {
				Criptografia criptografia = new Criptografia();
				try {
					uuid = criptografia.decifrar(Conversor.stringToByte(reader.nextString(),Constantes.DELIMITADOR));
				} catch (InvalidKeyException e) {
					e.printStackTrace();
				} catch (BadPaddingException e) {
					e.printStackTrace();
				} catch (IllegalBlockSizeException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (name.equals("cnpj")) {
				HashCode hashCode = new HashCode();
				query = new Query("Empresa");
				query.setFilter(new FilterPredicate("uuid",FilterOperator.EQUAL,uuid));
				empresa = datastore.prepare(query).asSingleEntity();
				if(!reader.nextString().equals(hashCode.processar(empresa.getProperty("cnpj").toString()))){
					logger.log(Level.SEVERE,bundle.getString("app.mensagem.hash.invalido"));
					throw new IOException();
				}
			} else if (name.equals("objetos")) {
				String nome = null;
				String numero = null;
				Double preco = null;
				Double minimo = null;
				Double quantidade = null;
				String objetos = reader.nextString();
				JsonReader reader1 = new JsonReader(new InputStreamReader(new ByteArrayInputStream(objetos.getBytes("UTF8")),"UTF-8"));
				reader1.beginArray();
				while (reader1.hasNext()) {
					reader1.beginObject();
					while (reader1.hasNext()) {
						String name1 = reader1.nextName();
						if (name1.equals("nome")) {
							nome = reader1.nextString();
						} else if (name1.equals("numero")) {
							numero = reader1.nextString();
						} else if (name1.equals("preco")) {
							preco = reader1.nextDouble();
						} else if (name1.equals("minimo")) {
							minimo = reader1.nextDouble();
						} else if (name1.equals("quantidade")) {
							quantidade = reader1.nextDouble();
						} else {
							reader1.skipValue();
						}
					}
					reader1.endObject();
					try{
						query = new Query("Produto");
						query.setFilter(new FilterPredicate("idEmpresa",FilterOperator.EQUAL,empresa.getKey().getId()));
						query.setFilter(new FilterPredicate("numero",FilterOperator.EQUAL,numero));
						PreparedQuery preparedQuery = datastore.prepare(query);
						Entity produto = preparedQuery.asSingleEntity();
						if(produto==null){
							produto = new Entity("Produto");
							produto.setProperty("nome",nome);
							produto.setProperty("numero",numero);
							produto.setProperty("preco", preco);
							produto.setProperty("minimo", minimo);
							produto.setProperty("quantidade", quantidade);
							produto.setProperty("idEmpresa",empresa.getKey().getId());
							datastore.put(produto);
						}else{ 
							Double min = (produto.getProperty("minimo")!=null?Double.parseDouble(produto.getProperty("minimo").toString().replace(".", "").replace(",", ".")):0d);
							Double quant = (produto.getProperty("quantidade")!=null?Double.parseDouble(produto.getProperty("quantidade").toString().replace(".", "").replace(",", ".")):0d);
							if(!produto.getProperty("nome").equals(nome)
								||!produto.getProperty("preco").equals(preco)
								||min!=minimo
								||quant!=quantidade){
								produto.setProperty("nome",nome);
								produto.setProperty("preco", preco);
								produto.setProperty("minimo", minimo);
								produto.setProperty("quantidade", quantidade);
								produto.setProperty("idEmpresa",empresa.getKey().getId());
								datastore.put(produto);
							}
						}
					}catch(TooManyResultsException e){
						logger.log(Level.SEVERE, "Número do produto: " + numero );
						throw e;
					}
					
				}
				reader1.endArray();

			} else {
				reader.skipValue();
			}
		}
		reader.endObject();
		
	}
	
}