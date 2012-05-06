package br.com.appestoque.restful.faturamento;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.appestoque.dao.PMF;
import br.com.appestoque.dao.faturamento.ItemDAO;
import br.com.appestoque.dao.faturamento.PedidoDAO;
import br.com.appestoque.dominio.cadastro.Empresa;
import br.com.appestoque.dominio.cadastro.Representante;
import br.com.appestoque.dominio.faturamento.Item;
import br.com.appestoque.dominio.faturamento.Pedido;

@SuppressWarnings("serial")
public class PedidoRestFul extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = request.getParameter("json");
		if(data!=null&&!data.equals("")){
			HttpSession httpSession = request.getSession();
			Empresa empresa = (Empresa) httpSession.getAttribute("empresa");
			Representante representante = (Representante) httpSession.getAttribute("representante");
			PersistenceManager pm = PMF.get().getPersistenceManager();		
			try {
				JSONObject json = new JSONObject(data);
				Pedido pedido = new Pedido( json.getString("numero"),
											new Date(json.getLong("data")),										
											representante.getId(),
											json.getLong("idCliente"),
											empresa.getId(),
											json.getString("obs"));
				PedidoDAO pedidoDAO = new PedidoDAO(pm);
				pedidoDAO.criar(pedido);
				
				ItemDAO itemDAO = new ItemDAO(pm);
				JSONArray itens = json.getJSONArray("itens");
				for (int i = 0; i <= itens.length() - 1; ++i) {
					Item item = new Item(pedido.getId(), 
							itens.getJSONObject(i).getLong("idProduto"), 
							itens.getJSONObject(i).getDouble("quantidade"), 
							itens.getJSONObject(i).getDouble("valor"));
					itemDAO.criar(item);
				}
				response.setContentType("application/json;charset=UTF-8");
				PrintWriter out = response.getWriter();
				json = new JSONObject();
				json.put("id",pedido.getId());
				out.print(json);
				out.flush();
			} catch (JSONException e) {
				response.setContentType("application/json;charset=UTF-8");
				PrintWriter out = response.getWriter();
				String json = "{'erro':'"+e.getMessage()+"'}";
				out.print(json);
				out.flush();
			}
		}
	}	

}