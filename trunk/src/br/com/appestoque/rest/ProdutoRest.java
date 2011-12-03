package br.com.appestoque.rest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.repackaged.org.json.JSONArray;
import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;

import br.com.appestoque.dao.suprimento.ProdutoDAO;
import br.com.appestoque.dominio.suprimento.Produto;

@SuppressWarnings("serial")
public class ProdutoRest extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ProdutoDAO dao = new ProdutoDAO((PersistenceManager) request.getAttribute("pm"));
		JSONArray objetos = new JSONArray();
		try{
			for(Produto produto : dao.listar()){
				JSONObject objeto = new JSONObject();
				objeto.put("id",produto.getId());
				objeto.put("nome",produto.getNome());
				objeto.put("numero",produto.getNumero());
				objeto.put("preco",produto.getPreco());
				objetos.put(objeto);
			}
		}catch(JSONException e) {
			e.printStackTrace();
		}
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(objetos);
		out.flush();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request, response);
	}

}