package br.com.appestoque.restful.faturamento;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.appestoque.dao.PMF;
import br.com.appestoque.dao.faturamento.PedidoDAO;
import br.com.appestoque.dominio.cadastro.Empresa;
import br.com.appestoque.dominio.cadastro.Representante;
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
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String data = bufferedReader.readLine();
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
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}	

}