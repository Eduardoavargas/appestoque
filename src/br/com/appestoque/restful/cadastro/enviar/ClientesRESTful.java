package br.com.appestoque.restful.cadastro.enviar;

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
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.PreparedQuery.TooManyResultsException;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.gson.stream.JsonReader;

@SuppressWarnings("serial")
public class ClientesRESTful extends BaseServlet{
	
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
				String objetos = reader.nextString();
				JsonReader reader1 = new JsonReader(new InputStreamReader(new ByteArrayInputStream(objetos.getBytes("UTF8")),"UTF-8"));
				reader1.beginArray();
				while (reader1.hasNext()) {
					
					String nome = null;
					String razao = null;
					String cnpj = null;
					String endereco = null;
					String complemento = null;
					Integer numero = null;
					String cep = null;
					String nomeBairro = null;
					String nomeCidade = null;
					
					reader1.beginObject();
					while (reader1.hasNext()) {
						String name1 = reader1.nextName();
						if (name1.equals("nome")) {
							nome = reader1.nextString();
						}else if (name1.equals("razao")) {
							razao = reader1.nextString();
						}else if (name1.equals("cnpj")) {
							cnpj = reader1.nextString();
						}else if (name1.equals("endereco")) {
							endereco = reader1.nextString();
						}else if (name1.equals("complemento")) {
							complemento = reader1.nextString();
						}else if (name1.equals("numero")) {
							numero = new Integer(reader1.nextInt());
						}else if (name1.equals("cep")) {
							cep = reader1.nextString();
						}else if (name1.equals("bairro")) {
							nomeBairro = reader1.nextString();
						}else if (name1.equals("cidade")) {
							nomeCidade = reader1.nextString();
						}else {
							reader1.skipValue();
						}
					}
					reader1.endObject();
					
					Entity cidade = null;
					try{
						query = new Query("Cidade");
						query.setFilter(CompositeFilterOperator.and(new FilterPredicate("nome",FilterOperator.EQUAL,nomeCidade),
								new FilterPredicate("idEmpresa",FilterOperator.EQUAL,empresa.getKey().getId())));
						cidade = datastore.prepare(query).asSingleEntity();
					}catch(TooManyResultsException e){
						Iterable<Entity> cidades = null;
						query = new Query("Cidade");
						query.setFilter(CompositeFilterOperator.and(new FilterPredicate("nome",FilterOperator.EQUAL,nomeCidade),
								new FilterPredicate("idEmpresa",FilterOperator.EQUAL,empresa.getKey().getId())));
						cidades = datastore.prepare(query).asIterable();
						for (Entity entity : cidades) {
							cidade = entity;
							break;
						}
					}
					
					Entity bairro = null;
					if(cidade!=null){
						try{
							query = new Query("Bairro");
							query.setFilter(
									CompositeFilterOperator.and(
										new FilterPredicate("nome",FilterOperator.EQUAL,nomeBairro),
										new FilterPredicate("idCidade",FilterOperator.EQUAL,cidade.getKey().getId()),
										new FilterPredicate("idEmpresa",FilterOperator.EQUAL,empresa.getKey().getId())
									) );
							bairro = datastore.prepare(query).asSingleEntity();
						}catch(TooManyResultsException e){
							Iterable<Entity> bairros = null;
							query = new Query("Bairro");
							query.setFilter(
									CompositeFilterOperator.and(
											new FilterPredicate("nome",FilterOperator.EQUAL,nomeBairro),
											new FilterPredicate("idCidade",FilterOperator.EQUAL,cidade.getKey().getId()),
											new FilterPredicate("idEmpresa",FilterOperator.EQUAL,empresa.getKey().getId())
									) );
							bairros = datastore.prepare(query).asIterable();
							for (Entity entity : bairros) {
								bairro = entity;
								break;
							}
						}
					}else{
						logger.log(Level.SEVERE,bundle.getString("app.mensagem.cliente.cidade.nao.localizada"));
						logger.log(Level.INFO,"CLIENTE NOME........: " + nome);
						logger.log(Level.INFO,"CLIENTE RAZÃO SOCIAL: " + razao);
						logger.log(Level.INFO,"CLIENTE CNPJ........: " + cnpj);
						logger.log(Level.INFO,"CLIENTE BAIRRO......: " + nomeBairro);
						logger.log(Level.INFO,"CLIENTE CIDADE......: " + nomeCidade);
					}
					
					if(bairro!=null){
						Entity cliente = null;
						try{
							query = new Query("Cliente");
							query.setFilter(
									CompositeFilterOperator.and(
											new FilterPredicate("cnpj",FilterOperator.EQUAL,cnpj),
											new FilterPredicate("idEmpresa",FilterOperator.EQUAL,empresa.getKey().getId())
									) );						
							cliente = datastore.prepare(query).asSingleEntity();
							if(cliente==null){
								cliente = new Entity("Cliente");
								cliente.setProperty("nome",nome);
								cliente.setProperty("razao",razao);
								cliente.setProperty("cnpj",cnpj);
								cliente.setProperty("endereco",endereco);
								cliente.setProperty("complemento",complemento);
								cliente.setProperty("numero",numero);
								cliente.setProperty("cep",cep);
								cliente.setProperty("idBairro",bairro.getKey().getId());
								cliente.setProperty("idEmpresa", empresa.getKey().getId());
							    datastore.put(cliente);
							}else if(!cliente.getProperty("nome").equals(nome)
									||(cliente.getProperty("razao")!=null&&!cliente.getProperty("razao").equals(razao))
									||!cliente.getProperty("endereco").equals(endereco)
									||!cliente.getProperty("complemento").equals(complemento)
									||!cliente.getProperty("numero").equals(numero)
									||!cliente.getProperty("cep").equals(cep)
									||!cliente.getProperty("idBairro").equals(bairro.getKey().getId())){
								cliente.setProperty("nome",nome);
								cliente.setProperty("razao",razao);
								cliente.setProperty("cnpj",cnpj);
								cliente.setProperty("endereco",endereco);
								cliente.setProperty("complemento",complemento);
								cliente.setProperty("numero",numero);
								cliente.setProperty("cep",cep);
								cliente.setProperty("idBairro",bairro.getKey().getId());
								cliente.setProperty("idEmpresa", empresa.getKey().getId());
								datastore.put(cliente);
							}
						}catch(TooManyResultsException e){
							logger.log(Level.SEVERE,bundle.getString("app.mensagem.cliente.cnpj.duplicado"));
							logger.log(Level.INFO,"CLIENTE NOME........: " + nome);
							logger.log(Level.INFO,"CLIENTE RAZÃO SOCIAL: " + razao);
							logger.log(Level.INFO,"CLIENTE CNPJ........: " + cnpj);
							logger.log(Level.INFO,"CLIENTE BAIRRO......: " + nomeBairro);
							logger.log(Level.INFO,"CLIENTE CIDADE......: " + nomeCidade);
						}
						
						
					}else{
						logger.log(Level.SEVERE,bundle.getString("app.mensagem.cliente.bairro.nao.localizada"));
						logger.log(Level.INFO,"CLIENTE NOME........: " + nome);
						logger.log(Level.INFO,"CLIENTE RAZÃO SOCIAL: " + razao);
						logger.log(Level.INFO,"CLIENTE CNPJ........: " + cnpj);
						logger.log(Level.INFO,"CLIENTE BAIRRO......: " + nomeBairro);
						logger.log(Level.INFO,"CLIENTE CIDADE......: " + nomeCidade);
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