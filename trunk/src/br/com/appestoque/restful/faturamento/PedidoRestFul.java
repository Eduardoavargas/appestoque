package br.com.appestoque.restful.faturamento;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.UUID;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.appestoque.Constantes;
import br.com.appestoque.Util;
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
				String uuid = UUID.randomUUID().toString();
				JSONObject json = new JSONObject(data);
				Pedido pedido = new Pedido( json.getString("numero"),
											new Date(json.getLong("data")),										
											representante.getId(),
											json.getLong("idCliente"),
											empresa.getId(),
											json.getString("obs"),
											uuid);
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
				
				if(empresa.getEmailPedido()!=null&&empresa.getEmailPedido().equals("")){
				
					StringBuffer corpo = new StringBuffer();
					
					corpo.append("<html>");
					corpo.append("<head>");
					corpo.append("</head>");
					corpo.append("<body>");
					corpo.append("<body>");
					corpo.append("<div style='font-family: 'Helvetica Neue', Arial, Helvetica, sans-serif; font-size: 13px; margin: 14px';>");
					corpo.append("<img src='http://www.appestoque.com.br/img/logo.jpg'/>");
					corpo.append("<h2 style='font-family: 'Helvetica Neue', Arial, Helvetica, sans-serif; margin: 0 0 16px; font-size: 18px; font-weight: normal'>Olá, "+empresa.getNome()+".</h2>");
					
					corpo.append("<p>Por favor, clique no link abaixo para imprimir pedido de venda:<br>");
					corpo.append("<a href='"+Constantes.URL+Constantes.URI_PEDIDO_VENDA+"&&uuid="+uuid+"'");
					corpo.append("target='_blank'>"+Constantes.URL+Constantes.URI_PEDIDO_VENDA+"&&uuid="+uuid+"</a></p>");
					
					corpo.append("<p style='font-family: 'Helvetica Neue', Arial, Helvetica, sans-serif; font-size: 13px; line-height: 18px; border-bottom: 1px solid rgb(238, 238, 238); padding-bottom: 10px; margin: 0 0 10px'>");
					corpo.append("<span style='font: italic 13px Georgia, serif; color: rgb(102, 102, 102)'>Equipe do Appestoque</span></p>");
					
					corpo.append("<p style='font-family: 'Helvetica Neue', Arial, Helvetica, sans-serif; margin-top: 5px; font-size: 10px; color: #888888'>");
					corpo.append("Se você recebeu essa mensagem por engano e não criou uma conta do Appestoque, clique"); 
					corpo.append(" <a href='mailto:suporte@appestoque.com.br?subject=[Cadastro]Mensagem por engano&&body='target='_blank'>não é minha conta</a>.</p>");
					
					corpo.append("<p style='font-family: 'Helvetica Neue', Arial, Helvetica, sans-serif; margin-top: 5px; font-size: 10px; color: #888888'>");
					corpo.append("Por favor não responda esta mensagem; ela foi enviada por um endereço");
					corpo.append("de e-mail não monitorado. Esta mensagem é relacionada ao seu uso do");
					corpo.append(" Appestoque. Para mais informações sobre a sua conta, por");
					corpo.append("favor encaminhe um e-mail para o");
					corpo.append(" <a href='mailto:suporte@appestoque.com.br' target='_blank'>Suporte do Appestoque</a>.</p>");
					
					corpo.append("</div>");
					corpo.append("</body>");
					corpo.append("</html>");
					
					Util.enviarEmail(empresa.getEmailPedido(),Constantes.ASSUNTO_PEDIDO_VENDA,corpo);
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