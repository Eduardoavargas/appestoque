package br.com.appestoque.restful.faturamento;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.appestoque.TipoBusca;
import br.com.appestoque.dao.cadastro.RepresentanteDAO;
import br.com.appestoque.dominio.cadastro.Representante;

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
		
		PersistenceManager pm = (PersistenceManager) request.getAttribute("pm");
		RepresentanteDAO representanteDAO = new RepresentanteDAO(pm);		
		try {			
			JSONObject json = new JSONObject(data);
			Representante representante = representanteDAO.pesquisar(json.getJSONObject("parametro").getString("os"),TipoBusca.PREGUICOSA);
			
//			Pedido pedido = new Pedido( json.getString("numero") ,  
//					Date data, 
//					Long idRepresentante,
//					Long idCliente, 
//					Double valor )
			
			System.out.println(json.getJSONObject("parametro").getString("os"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}	

}