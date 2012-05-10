package br.com.appestoque.restful.suprimento.enviar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import br.com.appestoque.TipoBusca;
import br.com.appestoque.dao.PMF;
import br.com.appestoque.dao.cadastro.BairroDAO;
import br.com.appestoque.dao.cadastro.RepresentanteDAO;
import br.com.appestoque.dao.suprimento.ProdutoDAO;
import br.com.appestoque.dominio.cadastro.Empresa;
import br.com.appestoque.dominio.cadastro.Representante;
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
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String data = bufferedReader.readLine();
        if(data!=null&&!data.equals("")){
        	PersistenceManager pm = null;
	        try {
	        	pm = PMF.get().getPersistenceManager();
				JSONObject objeto = new JSONObject(data);
				RepresentanteDAO representanteDAO = new RepresentanteDAO(pm);
				Representante representante = representanteDAO.pesquisar(objeto.getString("uuid"),TipoBusca.PREGUICOSA);
				if(representante!=null){
					Key key = KeyFactory.createKey(Empresa.class.getSimpleName(),representante.getIdEmpresa());
					Empresa empresa = pm.getObjectById(Empresa.class, key);
					ProdutoDAO produtoDAO  = new ProdutoDAO(pm);
					produtoDAO.excluir(empresa);
					JSONArray objetos = objeto.getJSONArray("objetos");
					for (int i = 0; i <= objetos.length() - 1; ++i) {
						produtoDAO.adicionar( new Produto(objetos.getJSONObject(i).getString("nome"),
								objetos.getJSONObject(i).getString("numero"),
								objetos.getJSONObject(i).getDouble("preco"),
								objetos.getJSONObject(i).getDouble("estoque"),empresa ));			
					}				
				}
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				pm.close();
			}			
        }
	}
	
}