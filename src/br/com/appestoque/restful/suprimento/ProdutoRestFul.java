package br.com.appestoque.restful.suprimento;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.appestoque.dao.suprimento.ProdutoDAO;
import br.com.appestoque.dominio.cadastro.Empresa;
import br.com.appestoque.dominio.suprimento.Produto;

@SuppressWarnings("serial")
public class ProdutoRestFul extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("sincronismo")!=null){
			PersistenceManager pm = (PersistenceManager) request.getAttribute("pm");
			ProdutoDAO produtoDAO = new ProdutoDAO(pm);
			HttpSession httpSession = request.getSession();
			Empresa empresa = (Empresa) httpSession.getAttribute("empresa");
			JSONArray objetos = new JSONArray();
			try {
				for (Produto produto : produtoDAO.listar(empresa.getId())) {
					JSONObject objeto = new JSONObject();
					objeto.put("_id", produto.getId());
					objeto.put("nome", produto.getNome());
					objeto.put("numero", produto.getNumero());
					objeto.put("valor", produto.getPreco());
					objeto.put("estoque", produto.getEstoque());
					objetos.put(objeto);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			response.setContentType("application/json;charset=UTF-8");
			response.setHeader("Content-Encoding", "gzip");
			PrintWriter out = response.getWriter();
			out.print(objetos);
			out.flush();
		}
	}

}