package br.com.appestoque.rest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.appestoque.dao.suprimento.ProdutoDAO;
import br.com.appestoque.dominio.seguranca.Usuario;
import br.com.appestoque.dominio.suprimento.Produto;

@SuppressWarnings("serial")
public class ProdutoRest extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PersistenceManager persistenceManager = (PersistenceManager) request.getAttribute("pm");
		ProdutoDAO produtoDAO = new ProdutoDAO(persistenceManager);
		HttpSession httpSession = request.getSession();
		Usuario usuario = (Usuario) httpSession.getAttribute("usuario");		
		JSONArray objetos = new JSONArray();
		try{
			for(Produto produto : produtoDAO.listar(usuario.getIdEmpresa())){
				JSONObject objeto = new JSONObject();
				objeto.put("_id",produto.getId());
				objeto.put("nome",produto.getNome());
				objeto.put("numero",produto.getNumero());
				objeto.put("preco",produto.getPreco());
				objeto.put("estoque",produto.getEstoque());
//				objeto.put("imagem1",produto.getImagem()!=null?produto.getImagem():"");
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