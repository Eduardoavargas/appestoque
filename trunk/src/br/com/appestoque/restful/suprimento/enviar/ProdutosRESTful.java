package br.com.appestoque.restful.suprimento.enviar;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.stream.JsonReader;

import br.com.appestoque.dao.PMF;
import br.com.appestoque.dao.cadastro.EmpresaDAO;
import br.com.appestoque.dao.suprimento.ProdutoDAO;
import br.com.appestoque.dominio.cadastro.Empresa;
import br.com.appestoque.dominio.suprimento.Produto;

@SuppressWarnings("serial")
public class ProdutosRESTful extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response)	throws IOException {
		processServer(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		processServer(request, response);
	}
	
	public void processServer(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String uuid = null;
		ProdutoDAO produtoDAO = null; 
		Empresa empresa = null;
		JsonReader reader = new JsonReader(new InputStreamReader(request.getInputStream(),"UTF-8"));
		reader.beginObject();
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals("uuid")) {
				uuid = reader.nextString();
			} else if (name.equals("objetos")) {
				String nome = null;
				String numero = null;
				Double preco = null;
				Double estoque = null;
				String objetos = reader.nextString();
				JsonReader reader1 = new JsonReader(new InputStreamReader(new ByteArrayInputStream(objetos.getBytes("UTF-8"))));
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
						} else if (name1.equals("estoque")) {
							estoque = reader1.nextDouble();
						} else {
							reader1.skipValue();
						}
					}
					
					reader1.endObject();
					
					if (produtoDAO == null) {
						produtoDAO = new ProdutoDAO(PMF.get()
								.getPersistenceManager());
					}

					if (empresa == null) {
						EmpresaDAO empresaDAO = new EmpresaDAO(PMF.get()
								.getPersistenceManager());
						empresa = empresaDAO.pesquisar(uuid);
					}

					produtoDAO.adicionar(new Produto(nome, numero, preco, estoque,empresa));
					
				}
				reader1.endArray();

			} else {
				reader.skipValue();
			}
		}
		reader.endObject();
		
//		BufferedReader bufferedReader = new BufferedReader(	new InputStreamReader(request.getInputStream()));
//		String data = bufferedReader.readLine();
//		if (data!=null&&!data.equals("")){
//			PersistenceManager pm = null;
//			try {
//				pm = PMF.get().getPersistenceManager();
//				JSONObject objeto = new JSONObject(data);
//				EmpresaDAO empresaDAO = new EmpresaDAO(pm);
//				Empresa empresa = empresaDAO
//						.pesquisar(objeto.getString("uuid"));
//				if (empresa != null) {
//					ProdutoDAO produtoDAO = new ProdutoDAO(pm);
//					produtoDAO.excluir(empresa);
//					JSONArray objetos = objeto.getJSONArray("objetos");
//					for (int i = 0; i <= objetos.length() - 1; ++i) {
//						produtoDAO.adicionar(new Produto(objetos.getJSONObject(
//								i).getString("nome"), objetos.getJSONObject(i)
//								.getString("numero"), objetos.getJSONObject(i)
//								.getDouble("preco"), objetos.getJSONObject(i)
//								.getDouble("estoque"), empresa));
//					}
//				} else {
//					ResourceBundle bundle = ResourceBundle.getBundle("i18n",request.getLocale());
//					throw new IOException(bundle.getString("app.mensagem.uuid.invalido"));
//				}
//			} catch (JSONException e) {
//				throw new IOException(e);
//			} finally {
//				pm.close();
//			}
//		}
		
	}
	
}