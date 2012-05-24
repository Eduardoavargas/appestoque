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

import br.com.appestoque.dao.PMF;
import br.com.appestoque.dao.cadastro.CidadeDAO;
import br.com.appestoque.dao.cadastro.EmpresaDAO;
import br.com.appestoque.dominio.cadastro.Cidade;
import br.com.appestoque.dominio.cadastro.Empresa;

@SuppressWarnings("serial")
public class CidadesRESTful extends HttpServlet{

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
				EmpresaDAO empresaDAO = new EmpresaDAO(pm);
				Empresa empresa = empresaDAO.pesquisar(objeto.getString("uuid"));
				if(empresa!=null){
					CidadeDAO cidadeDAO  = new CidadeDAO(pm);
					cidadeDAO.excluir(empresa);
					JSONArray objetos = objeto.getJSONArray("objetos");
					for (int i = 0; i <= objetos.length() - 1; ++i) {
						cidadeDAO.adicionar(new Cidade(objetos.getJSONObject(i).getString("nome"),empresa));
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