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

import com.google.appengine.api.datastore.AsyncDatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.gson.stream.JsonReader;

public class CidadesRESTful extends BaseServlet{
	
	public void processServer(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String uuid = null;
		Entity empresa = null;
		JsonReader reader = new JsonReader(new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF8")));
		AsyncDatastoreService datastore = DatastoreServiceFactory.getAsyncDatastoreService();
		reader.beginObject();
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals("cripto")) {
				Criptografia criptografia = new Criptografia();
				try {
					String temp = new String(reader.nextString());
					uuid = criptografia.descriptografar(temp.getBytes());
				} catch (InvalidKeyException e) {
					e.printStackTrace();
				} catch (BadPaddingException e) {
					e.printStackTrace();
				} catch (IllegalBlockSizeException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (name.equals("hash")) {
				HashCode hashCode = new HashCode();
				Query query = new Query("Empresa");
				query.addFilter("uuid",FilterOperator.EQUAL,uuid);
				empresa = datastore.prepare(query).asSingleEntity();
				if(!reader.nextString().equals(hashCode.processar(empresa.getProperty("cnpj").toString()))){
					logger.log(Level.SEVERE,bundle.getString("app.mensagem.hash.invalido"));
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
					cidade.setProperty("idEmpresa",empresa.getKey().getId());
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