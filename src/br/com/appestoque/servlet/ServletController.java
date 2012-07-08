package br.com.appestoque.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletController extends HttpServlet{

	private static final long serialVersionUID = -5898564556376171840L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		resp.setContentType("application/vnd.google-earth.kml+xml");
		resp.setHeader("Content-Encoding", "gzip");
		
		resp.getWriter().println("<?xml version='1.0' encoding='UTF-8'?>");
		resp.getWriter().println("<kml xmlns='http://www.opengis.net/kml/2.2'>");
		
		resp.getWriter().println("<Folder>");
		resp.getWriter().println("  <open>1</open>");
		resp.getWriter().println("  <name>Pedidos de Venda</name>");	
		resp.getWriter().println("  <Placemark>");
		resp.getWriter().println("    <name>Pedido de Venda 1</name>");
		resp.getWriter().println("    <description><![CDATA[Esta e a descricao do pedido de venda 1.]]></description>");
		resp.getWriter().println("    <Point>");
		resp.getWriter().println("      <coordinates>-41.275635,-20.971699</coordinates>");
		resp.getWriter().println("    </Point>");
		resp.getWriter().println("  </Placemark>");
		resp.getWriter().println("  <Placemark>");
		resp.getWriter().println("    <name>Pedido de Venda 2</name>");
		resp.getWriter().println("    <description><![CDATA[Esta e a descricao do pedido de venda 2.]]></description>");
		resp.getWriter().println("    <Point>");
		resp.getWriter().println("      <coordinates>-40.561523,-19.777042</coordinates>");
		resp.getWriter().println("    </Point>");
		resp.getWriter().println("  </Placemark>");
		resp.getWriter().println("  <Placemark>");
		resp.getWriter().println("    <name>Pedido de Venda 3</name>");
		resp.getWriter().println("    <description><![CDATA[Esta e a descricao do pedido de venda 3.]]></description>");
		resp.getWriter().println("    <Point>");
		resp.getWriter().println("      <coordinates> -40.292358,-18.443136</coordinates>");
		resp.getWriter().println("    </Point>");
		resp.getWriter().println("  </Placemark>");
		resp.getWriter().println("</Folder>");
		
		resp.getWriter().println("</kml>");
		resp.getWriter().flush();

	}

}