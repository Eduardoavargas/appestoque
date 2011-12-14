package br.com.appestoque;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.io.PrintWriter;

import com.pdfjet.*;
import com.d_project.qrcode.*;

import java.net.HttpURLConnection;
import java.net.URL;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.appestoque.comum.Pagina;
import br.com.appestoque.dao.PMF;
import br.com.appestoque.dao.cadastro.EmpresaDAO;
import br.com.appestoque.dominio.cadastro.Empresa;
import br.com.appestoque.dominio.seguranca.Usuario;

@SuppressWarnings("serial")
public class Processo extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("acao").equals("logoff")) {
			HttpSession session = request.getSession();
			session.setAttribute("autorizado", new Boolean("false"));
			HttpServletResponse servletResponse = (HttpServletResponse) response;
    		servletResponse.sendRedirect(Pagina.PAGINA_APRESENTACAO);
		}else if (request.getParameter("acao").equals("cadastrar")) {
			request.setAttribute("empresa", new Empresa());
			request.setAttribute("usuario", new Usuario());
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_EMPRESA_CADASTRAR);
			dispatcher.forward(request, response);
		}else if (request.getParameter("acao").equals("selecionar")) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			EmpresaDAO dao = new EmpresaDAO(pm);
			Long id = new Long(request.getParameter("id"));
			Empresa empresa = dao.pesquisar(id);
	        HttpSession session = request.getSession();
	        session.setAttribute("empresa", empresa);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Pagina.PAGINA_MENU);
			dispatcher.forward(request, response);
		}else if(request.getParameter("acao").equals("noticia")) {
			try{
				HttpURLConnection httpSource = null;
				//URL url = new URL("http://g1.globo.com/dynamo/brasil/rss2.xml");
				URL url = new URL("http://feeds.folha.uol.com.br/folha/dinheiro/rss091.xml");				
				httpSource = (HttpURLConnection)url.openConnection();
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(httpSource.getInputStream());
				doc.getDocumentElement().normalize();
				NodeList nList = doc.getElementsByTagName("item");
				PrintWriter out = response.getWriter();
				response.setContentType("text/html");
//				for (int temp = 0; temp < nList.getLength(); temp++) {
				for (int temp = 0; temp < 3; temp++){
				   Node nNode = nList.item(temp);
				   if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				      Element eElement = (Element) nNode;
				      out.print(getTagValue("title", eElement)+"<br/><br/>");
//				      System.out.println("Data de Publicação : " + getTagValue("pubDate", eElement));
//				      System.out.println("Titulo : " + getTagValue("title", eElement));
//				      System.out.println("link : " + getTagValue("link", eElement));
//			          System.out.println("Descrição : " + getTagValue("description", eElement));
				   }
				}
		  } catch (Exception e) {
			e.printStackTrace();
		  }
		}else if(request.getParameter("acao").equals("pdf")) {
			try {
				ServletOutputStream servletOutputStream = response.getOutputStream();
				PDF pdf = new PDF(servletOutputStream);
				response.setHeader("Content-Disposition", "attachment; filename=qrcode.pdf");
				response.setContentType("application/pdf");
				Page page = new Page(pdf, Letter.PORTRAIT);
				
				QRCode qr = null;
				
				qr = new QRCode();
				qr.setTypeNumber(Mode.MODE_8BIT_BYTE);
		        qr.setErrorCorrectLevel(ErrorCorrectLevel.M);
		        qr.addData("André Silva Tricano");
		        qr.make();
		        qr.setPosition(200, 100);
		        qr.drawOn(page);
		        
		        qr = new QRCode();
				qr.setTypeNumber(Mode.MODE_8BIT_BYTE);
		        qr.setErrorCorrectLevel(ErrorCorrectLevel.M);
		        qr.addData("Alan Silva Tricano");
		        qr.make();
		        qr.setPosition(400, 100);
		        qr.drawOn(page);
		        
		        pdf.flush();
		        servletOutputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(request.getParameter("acao").equals("file")) {
			
			
			try {
				  File file = new File("d:\\MyXMLFile.xml");
				  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				  DocumentBuilder db = dbf.newDocumentBuilder();
				  Document doc = db.parse(file);
				  doc.getDocumentElement().normalize();
				  System.out.println("Root element " + doc.getDocumentElement().getNodeName());
				  NodeList nodeLst = doc.getElementsByTagName("employee");
				  System.out.println("Information of all employees");

				  for (int s = 0; s < nodeLst.getLength(); s++) {

				    Node fstNode = nodeLst.item(s);
				    
				    if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
				  
				           Element fstElmnt = (Element) fstNode;
				      NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("firstname");
				      Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
				      NodeList fstNm = fstNmElmnt.getChildNodes();
				      System.out.println("First Name : "  + ((Node) fstNm.item(0)).getNodeValue());
				      NodeList lstNmElmntLst = fstElmnt.getElementsByTagName("lastname");
				      Element lstNmElmnt = (Element) lstNmElmntLst.item(0);
				      NodeList lstNm = lstNmElmnt.getChildNodes();
				      System.out.println("Last Name : " + ((Node) lstNm.item(0)).getNodeValue());
				    }

				  }
				  } catch (Exception e) {
				    e.printStackTrace();
				  }
			
			
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
	 
	        Node nValue = (Node) nlList.item(0);
	 
		return nValue.getNodeValue();
	}
	
}
