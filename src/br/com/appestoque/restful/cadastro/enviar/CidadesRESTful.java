package br.com.appestoque.restful.cadastro.enviar;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.logging.Level;
import java.util.logging.Logger;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.AsyncDatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.gson.stream.JsonReader;

//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import javax.jdo.PersistenceManager;
//import java.util.ResourceBundle;
//import br.com.appestoque.dao.PMF;
//import br.com.appestoque.dao.cadastro.CidadeDAO;
//import br.com.appestoque.dao.cadastro.EmpresaDAO;
//import br.com.appestoque.dominio.cadastro.Cidade;
//import br.com.appestoque.dominio.cadastro.Empresa;
//import br.com.appestoque.restful.suprimento.enviar.ProdutosRESTful;

@SuppressWarnings("serial")
public class CidadesRESTful extends HttpServlet{
	
	private static final Logger logger = Logger.getLogger(CidadesRESTful.class.getCanonicalName());

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
				Entity produto = datastore.prepare(query).asSingleEntity();
				if(produto!=null){					
					id = produto.getKey().getId();
				}else{
					logger.log(Level.SEVERE,"UUID da empresa não pode ser identificado no envio de cidades");
					throw new IOException();
				}
			} else if (name.equals("objetos")) {
				String nome = null;
				String objetos = reader.nextString();
				JsonReader reader1 = new JsonReader(new InputStreamReader(new ByteArrayInputStream(objetos.getBytes("UTF8")),"UTF-8"));
				reader1.beginArray();
				while (reader1.hasNext()) {
					reader1.beginObject();
					while (reader1.hasNext()) {
						String name1 = reader1.nextName();
						if (name1.equals("nome")) {
							nome = reader1.nextString();
						}else {
							reader1.skipValue();
						}
					}
					
					reader1.endObject();
					
					Entity cidade = new Entity("Cidade");
					cidade.setProperty("nome",nome);
					cidade.setProperty("idEmpresa", id);
				    datastore.put(cidade);
					
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
//					CidadeDAO cidadeDAO  = new CidadeDAO(pm);
//					cidadeDAO.excluir(empresa);
//					JSONArray objetos = objeto.getJSONArray("objetos");
//					for (int i = 0; i <= objetos.length() - 1; ++i) {
//						cidadeDAO.adicionar(new Cidade(objetos.getJSONObject(i).getString("nome"),empresa));
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