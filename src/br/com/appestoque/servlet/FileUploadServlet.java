package br.com.appestoque.servlet;

import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.com.appestoque.dominio.seguranca.Usuario;
import br.com.appestoque.dominio.suprimento.Produto;

import java.io.InputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

@SuppressWarnings("serial")
public class FileUploadServlet extends HttpServlet{
	
	private static String ARQUIVO_PRODUTO = "produtos.xml";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.print("<p>Error: The request method <code>"+request.getMethod()+"</code> is inappropriate for the URL <code>"+request.getRequestURI()+"</code></p>");
		out.close();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PersistenceManager persistenceManager = (PersistenceManager) request.getAttribute("pm");
		HttpSession httpSession = request.getSession();
		Usuario usuario = (Usuario) httpSession.getAttribute("usuario");
		
		try {
			ServletFileUpload servletFileUpload = new ServletFileUpload();
			FileItemIterator fileItemIterator = servletFileUpload.getItemIterator(request);
			while (fileItemIterator.hasNext()) {
				FileItemStream fileItemStream = fileItemIterator.next();				
				if(fileItemStream.getName()!=null&&fileItemStream.getName().equals(ARQUIVO_PRODUTO)){				
			        InputStream inputStream = fileItemStream.openStream();
			        
			        
			        
			        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
					Document document = documentBuilder.parse(inputStream);
					
					document.getDocumentElement().normalize();
					NodeList nodeList = document.getElementsByTagName("produto");
					
					List<Produto> produtos = new ArrayList<Produto>();
					
					for (int i = 0; i < nodeList.getLength(); i++){
						Node node = nodeList.item(i);
						if(node.getNodeType()==Node.ELEMENT_NODE){
							Element element = (Element) node;
							
							try{
					
								Produto produto = new Produto();
								produto.setNome(getTagValue("nome", element)!=null?getTagValue("nome", element):"");
								produto.setNumero(getTagValue("numero", element));
								produto.setPreco(new Double(getTagValue("preco", element)));
								produto.setIdEmpresa(usuario.getIdEmpresa());
								System.out.println( produto.getNome() );
								produtos.add(produto);
								
							}catch(NullPointerException e){
								e.printStackTrace();
							}
							
						}					
					}
					
					if(produtos.size()>0){
						persistenceManager.makePersistentAll(produtos);
					}
					
				}
			}
			
		} catch (Exception e) {
			throw new ServletException(e);
		} 
		
	}
	
	private static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
	    Node nValue = (Node) nlList.item(0);
		return nValue.getNodeValue();
	}

}