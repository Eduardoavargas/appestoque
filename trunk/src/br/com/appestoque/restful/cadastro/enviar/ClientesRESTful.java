package br.com.appestoque.restful.cadastro.enviar;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.appengine.api.datastore.AsyncDatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.PreparedQuery.TooManyResultsException;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.gson.stream.JsonReader;

import br.com.appestoque.dao.PMF;
import br.com.appestoque.dao.cadastro.BairroDAO;
import br.com.appestoque.dao.cadastro.ClienteDAO;
import br.com.appestoque.dao.cadastro.EmpresaDAO;
import br.com.appestoque.dominio.cadastro.Bairro;
import br.com.appestoque.dominio.cadastro.Cliente;
import br.com.appestoque.dominio.cadastro.Empresa;

@SuppressWarnings("serial")
public class ClientesRESTful extends HttpServlet{

	private static final Logger logger = Logger.getLogger(ClientesRESTful.class.getCanonicalName());
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)	throws IOException {
		processServer(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		processServer(request, response);
	}
	
	public void processServer(HttpServletRequest request, HttpServletResponse response) throws IOException {	
		
		Long id = null;
		String uuid = null;
		JsonReader reader = new JsonReader(new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF8")));
		AsyncDatastoreService datastore = DatastoreServiceFactory.getAsyncDatastoreService();
		reader.beginObject();
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals("uuid")) {
				uuid = reader.nextString();
				Query query = new Query("Empresa");
				query.addFilter("uuid",FilterOperator.EQUAL,uuid);
				Entity empresa = datastore.prepare(query).asSingleEntity();
				if(empresa!=null){					
					id = empresa.getKey().getId();
				}else{
					logger.log(Level.SEVERE,"UUID da empresa não pode ser identificado no envio de clientes");
					throw new IOException();
				}
			} else if (name.equals("objetos")) {
				String objetos = reader.nextString();
				JsonReader reader1 = new JsonReader(new InputStreamReader(new ByteArrayInputStream(objetos.getBytes("UTF8")),"UTF-8"));
				reader1.beginArray();
				while (reader1.hasNext()) {
					
					String nome = null;
					String cnpj = null;
					String endereco = null;
					String complemento = null;
					Integer numero = null;
					String cep = null;
					String nomeBairro = null;
					
					reader1.beginObject();
					while (reader1.hasNext()) {
						String name1 = reader1.nextName();
						if (name1.equals("nome")) {
							nome = reader1.nextString();
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
							
						}else {
							reader1.skipValue();
						}
					}
					reader1.endObject();
					
					Entity bairro = null;
					System.out.println(nome);
					try{
						Query query = new Query("Bairro");
						query.addFilter("nome",FilterOperator.EQUAL,nomeBairro);
						query.addFilter("idEmpresa",FilterOperator.EQUAL,id);
						bairro = datastore.prepare(query).asSingleEntity();
					}catch(TooManyResultsException e){
						Iterable<Entity> bairros = null;
						Query query = new Query("Bairro");
						query.addFilter("nome",FilterOperator.EQUAL,nomeBairro);
						query.addFilter("idEmpresa",FilterOperator.EQUAL,id);
						System.out.println(nomeBairro);
						bairros = datastore.prepare(query).asIterable();
						for (Entity entity : bairros) {
							bairro = entity;
							break;
						}
					}
					
					Entity cliente = new Entity("Cliente");
					cliente.setProperty("nome",nome);
					cliente.setProperty("cnpj",cnpj);
					cliente.setProperty("endereco",endereco);
					cliente.setProperty("complemento",complemento);
					cliente.setProperty("numero",numero);
					cliente.setProperty("cep",cep);
					cliente.setProperty("idBairro",bairro.getKey().getId());
					cliente.setProperty("idEmpresa", id);
				    datastore.put(cliente);
					
				}
				reader1.endArray();

			} else {
				reader.skipValue();
			}
		}
		reader.endObject();
		
		
		
//		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
//        String data = bufferedReader.readLine();
//        if(data!=null&&!data.equals("")){
//        	PersistenceManager pm = null;
//	        try {
//	        	pm = PMF.get().getPersistenceManager();
//				JSONObject objeto = new JSONObject(data);
//				EmpresaDAO empresaDAO = new EmpresaDAO(pm);
//				Empresa empresa = empresaDAO.pesquisar(objeto.getString("uuid"));
//				if(empresa!=null){
//					ClienteDAO clienteDAO  = new ClienteDAO(pm);
//					BairroDAO bairroDAO  = new BairroDAO(pm);
//					clienteDAO.excluir(empresa);
//					JSONArray objetos = objeto.getJSONArray("objetos");
//					for (int i = 0; i <= objetos.length() - 1; ++i) {
//						Bairro bairro = bairroDAO.pesquisar(objetos.getJSONObject(i).getString("bairro"), empresa);
//						if(bairro!=null){
//							clienteDAO.adicionar( new Cliente( objetos.getJSONObject(i).getString("nome"), 
//											objetos.getJSONObject(i).getString("cnpj"), 
//											objetos.getJSONObject(i).getString("endereco"),
//											objetos.getJSONObject(i).getString("complemento"), 
//											new Integer(objetos.getJSONObject(i).getInt("numero")), 
//											objetos.getJSONObject(i).getString("cep"), 
//											bairro,
//											empresa));
//						}
//					}				
//				}else{
//					ResourceBundle bundle = ResourceBundle.getBundle("i18n",request.getLocale());
//					throw new IOException(bundle.getString("app.mensagem.uuid.invalido"));
//				}
//			} catch (JSONException e) {
//				throw new IOException(e);
//			} finally{
//				pm.close();
//			}			
//        }
		
	}
	
}