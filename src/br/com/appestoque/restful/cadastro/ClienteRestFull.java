package br.com.appestoque.restful.cadastro;

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

import br.com.appestoque.TipoBusca;
import br.com.appestoque.dao.cadastro.ClienteDAO;
import br.com.appestoque.dominio.cadastro.Cliente;
import br.com.appestoque.dominio.cadastro.Empresa;

@SuppressWarnings("serial")
public class ClienteRestFull extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PersistenceManager pm = (PersistenceManager) request.getAttribute("pm");
		ClienteDAO clienteDAO = new ClienteDAO(pm);
		HttpSession httpSession = request.getSession();
		Empresa empresa = (Empresa) httpSession.getAttribute("empresa");
		JSONArray objetos = new JSONArray();
		try {
			for (Cliente cliente : clienteDAO.listar(empresa.getId(),TipoBusca.ANSIOSA)) {
				JSONObject objeto = new JSONObject();
				objeto.put("_id",cliente.getId());			
				objeto.put("nome",cliente.getNome());
				objeto.put("cnpj",cliente.getCnpj());
				objeto.put("endereco",cliente.getEndereco());
				objeto.put("numero",cliente.getNumero());
				objeto.put("cep",cliente.getCep());
				objeto.put("complemento",cliente.getComplemento());
				objeto.put("bairro",cliente.getBairro().getNome());
				objeto.put("cidade",cliente.getBairro().getCidade().getNome());
				objetos.put(objeto);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(objetos);
		out.flush();
	}
	    
		
}


//
//		CÓDIGO PARA RECEBER PEDIDO DO SMARTPHONE
//
//	     try {
//	    	 
//	    	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
//			String data = bufferedReader.readLine();
//			System.out.println(data);
//		
//			JSONObject pedido = new JSONObject(data);
//			System.out.println(pedido.getString("numero"));
//			System.out.println(pedido.getString("data"));
//			System.out.println(pedido.getLong("idRepresentante"));
//			System.out.println(pedido.getLong("idCliente"));
//			
//			JSONArray itens = pedido.getJSONArray("itens");
//			for (int i = 0; i <= itens.length() - 1; ++i) {
//				System.out.println(itens.getJSONObject(i).getDouble("quantidade"));
//				System.out.println(itens.getJSONObject(i).getDouble("valor"));
//				System.out.println(itens.getJSONObject(i).getLong("idPedido"));
//				System.out.println(itens.getJSONObject(i).getLong("idProduto"));
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		

