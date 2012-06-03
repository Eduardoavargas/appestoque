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
				Entity empresa = datastore.prepare(query).asSingleEntity();
				if(empresa!=null){					
					id = empresa.getKey().getId();
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
        
	}
	
}