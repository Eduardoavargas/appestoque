package br.com.appestoque.restfull.faturamento;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("serial")
public class PedidoRestFull  extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String data = bufferedReader.readLine();
		System.out.println(data);
		try {
			JSONObject json = new JSONObject(data);
			System.out.println(json.getJSONObject("parametro").getString("os"));
			System.out.println(json.getString("so"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}	
	
}