package br.com.appestoque.restfull.cadastro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

@SuppressWarnings("serial")
public class ClienteRestFull extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     
	     try {
	    	 
	    	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String data = bufferedReader.readLine();
			System.out.println(data);
		
			JSONObject pedido = new JSONObject(data);
			System.out.println(pedido.getString("numero"));
			System.out.println(pedido.getString("data"));
			System.out.println(pedido.getLong("idRepresentante"));
			System.out.println(pedido.getLong("idCliente"));
			
			JSONArray itens = pedido.getJSONArray("itens");
			for (int i = 0; i <= itens.length() - 1; ++i) {
				System.out.println(itens.getJSONObject(i).getDouble("quantidade"));
				System.out.println(itens.getJSONObject(i).getDouble("valor"));
				System.out.println(itens.getJSONObject(i).getLong("idPedido"));
				System.out.println(itens.getJSONObject(i).getLong("idProduto"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
}
