package br.com.appestoque.restful.cadastro.enviar;

import java.io.BufferedReader;
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

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import br.com.appestoque.TipoBusca;
import br.com.appestoque.dao.PMF;
import br.com.appestoque.dao.cadastro.BairroDAO;
import br.com.appestoque.dao.cadastro.ClienteDAO;
import br.com.appestoque.dao.cadastro.RepresentanteDAO;
import br.com.appestoque.dominio.cadastro.Bairro;
import br.com.appestoque.dominio.cadastro.Cliente;
import br.com.appestoque.dominio.cadastro.Empresa;
import br.com.appestoque.dominio.cadastro.Representante;

@SuppressWarnings("serial")
public class ClientesRESTful extends HttpServlet{

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
					ClienteDAO clienteDAO  = new ClienteDAO(pm);
					BairroDAO bairroDAO  = new BairroDAO(pm);
					clienteDAO.excluir(empresa);
					JSONArray objetos = objeto.getJSONArray("objetos");
					for (int i = 0; i <= objetos.length() - 1; ++i) {
						Bairro bairro = bairroDAO.pesquisar(objetos.getJSONObject(i).getString("bairro"), empresa);
						if(bairro!=null){
							clienteDAO.adicionar( new Cliente( objetos.getJSONObject(i).getString("nome"), 
											objetos.getJSONObject(i).getString("cnpj"), 
											objetos.getJSONObject(i).getString("endereco"),
											objetos.getJSONObject(i).getString("complemento"), 
											new Integer(objetos.getJSONObject(i).getInt("numero")), 
											objetos.getJSONObject(i).getString("cep"), 
											bairro,
											empresa));
						}
					}				
				}else{
					ResourceBundle bundle = ResourceBundle.getBundle("i18n",request.getLocale());
					throw new IOException(bundle.getString("app.mensagem.uuid.invalido"));
				}
			} catch (JSONException e) {
				throw new IOException(e);
			} finally{
				pm.close();
			}			
        }
	}
	
}