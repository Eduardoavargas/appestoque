package br.com.appstoque.rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.appestoque.dao.seguranca.UsuarioDAO;
import br.com.appestoque.dominio.seguranca.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("serial")
public class UsuarioRest extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONArray jObjs = new JSONArray();
		try{
			UsuarioDAO dao = new UsuarioDAO((PersistenceManager) request.getAttribute("pm"));
			List<Usuario> usuarios = (List<Usuario>) dao.listar();
			for(Usuario usuario : usuarios){
				JSONObject jObj = new JSONObject();
				jObj.put("nome", usuario.getNome());
				jObj.put("email", usuario.getEmail());
				jObj.put("senha", usuario.getSenha());
				jObjs.put(jObj);
			}
		}catch(JSONException e) {
			e.printStackTrace();
		}
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jObjs);
		out.flush();
	}
	
}
