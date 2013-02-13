package br.com.appestoque.restful.faturamento;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.appestoque.Constantes;
import br.com.appestoque.Util;
import br.com.appestoque.dao.PMF;
import br.com.appestoque.dao.faturamento.PedidoDAO;
import br.com.appestoque.dominio.faturamento.Item;
import br.com.appestoque.dominio.faturamento.Pedido;
import br.com.appestoque.restful.suprimento.enviar.ProdutosRESTful;

import com.pdfjet.A4;
import com.pdfjet.Align;
import com.pdfjet.Cell;
import com.pdfjet.CoreFont;
import com.pdfjet.Font;
import com.pdfjet.Line;
import com.pdfjet.PDF;
import com.pdfjet.Page;
import com.pdfjet.Point;
import com.pdfjet.RGB;
import com.pdfjet.Table;
import com.pdfjet.TextLine;
import com.pdfjet.Box;

@SuppressWarnings("serial")
public class RelatorioPedidoRestFul extends HttpServlet{

	private Double top;
    private Double left;

    private Page page;
    private PDF pdf;
    private Font font;
    private TextLine textLine;
    private Point point1;
    private Point point2;
    private Line line;
    
    private Pedido pedido;
    
    private ResourceBundle bundle; 
    
    private static final Logger logger = Logger.getLogger(ProdutosRESTful.class.getCanonicalName());
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	private void imprimirCabecario(){
		Font font = null;
		Box box = null;
		TextLine textLine = null;
		try {
			
	        box = new Box();
	        box.setPosition(0.0, 0.0);
	        box.setSize(612.0, 22.00);
	        box.setColor(RGB.BLUE);
	        box.setLineWidth(2.5);
	        box.setFillShape(true);
	        box.drawOn(page);
	        
	        font = new Font(pdf,CoreFont.HELVETICA);
	        font.setSize(10.0);
            textLine = new TextLine(font,bundle.getString("relatorio.pedido.venda.titulo"));  
            textLine.setPosition(20.0,13.50);
            textLine.setColor(RGB.WHITE);
            textLine.drawOn(page);
            
            font = new Font(pdf,CoreFont.HELVETICA);
	        font.setSize(7.0);
            textLine = new TextLine(font,Constantes.SITE);  
            textLine.setPosition(485.0,17.50);
            textLine.setColor(RGB.WHITE);
            textLine.setURIAction(Constantes.URL);            
            textLine.drawOn(page);
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void imprimirMestre(){
		top += 20.00;
		try {
			
			font = new Font(pdf,CoreFont.HELVETICA_BOLD);			
			font.setSize(7.0);
			
	        textLine = new TextLine(font,"Número");  
	        textLine.setPosition(left,top);                 
	        textLine.drawOn(page);
	        
	        textLine = new TextLine(font,"Data");  
	        textLine.setPosition(left+65,top);                      
	        textLine.drawOn(page);
	        
	        textLine = new TextLine(font,"Hora");  
	        textLine.setPosition(left+110,top);                     
	        textLine.drawOn(page);
	        
	        textLine = new TextLine(font,"Cliente");  
	        textLine.setPosition(left+150,top);                     
	        textLine.drawOn(page);
	        
	        textLine = new TextLine(font,"Representante");  
	        textLine.setPosition(left+300,top);                     
	        textLine.drawOn(page);
	        
	        textLine = new TextLine(font,"Observação");  
	        textLine.setPosition(left+420,top);                     
	        textLine.drawOn(page);
	        
	        top += 10.00;
	        
	        font = new Font(pdf,CoreFont.HELVETICA);
	        font.setSize(7.0);
	        
	        textLine = new TextLine(font,this.pedido.getNumero());  
	        textLine.setPosition(left,top);                 
	        textLine.drawOn(page);
	        
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constantes.MASCARA_DATA_PADRAO);
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone(Constantes.GMT_BRASIL));
			
	        textLine = new TextLine(font,simpleDateFormat.format(this.pedido.getData()));  
	        textLine.setPosition(left+65,top);                      
	        textLine.drawOn(page);
	        
	        simpleDateFormat = new SimpleDateFormat(Constantes.MASCARA_HORA_PADRAO);
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone(Constantes.GMT_BRASIL));
	        
	        textLine = new TextLine(font,simpleDateFormat.format(this.pedido.getData()));  
	        textLine.setPosition(left+110,top);                     
	        textLine.drawOn(page);
	        
	        textLine = new TextLine(font,this.pedido.getCliente().getNome());  
	        textLine.setPosition(left+150,top);                     
	        textLine.drawOn(page);
	        
	        textLine = new TextLine(font,this.pedido.getRepresentante().getNome());  
	        textLine.setPosition(left+300,top);                     
	        textLine.drawOn(page);
	        
	        textLine = new TextLine(font,this.pedido.getObs());  
	        textLine.setPosition(left+420,top);                     
	        textLine.drawOn(page);
	        
	        top += 10.00;
	        
	        font = new Font(pdf,CoreFont.HELVETICA_BOLD);			
			font.setSize(7.0);
			
			textLine = new TextLine(font,"Endereço");  
	        textLine.setPosition(left,top);                 
	        textLine.drawOn(page);
			
	        textLine = new TextLine(font,"Bairro");  
	        textLine.setPosition(left+300,top);                 
	        textLine.drawOn(page);
	        
	        textLine = new TextLine(font,"Cidade");  
	        textLine.setPosition(left+420,top);                 
	        textLine.drawOn(page);
	        
	        top += 10.00;
	        
	        font = new Font(pdf,CoreFont.HELVETICA);
	        font.setSize(7.0);
	        
	        textLine = new TextLine(font,this.pedido.getCliente().getEndereco());  
	        textLine.setPosition(left,top);                 
	        textLine.drawOn(page);
	        
	        textLine = new TextLine(font,this.pedido.getCliente().getBairro().getNome());  
	        textLine.setPosition(left+300,top);                 
	        textLine.drawOn(page);
	        
	        textLine = new TextLine(font,this.pedido.getCliente().getBairro().getCidade().getNome());  
	        textLine.setPosition(left+420,top);                 
	        textLine.drawOn(page);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.bundle = ResourceBundle.getBundle(Constantes.I18N,request.getLocale());
		if(request.getParameter("uuid")!=null&&!request.getParameter("uuid").equals("")){
			OutputStream outStream = null;	
			PersistenceManager pm = null;
			outStream = response.getOutputStream();
	        response.setContentType(Constantes.CONTENT_TYPE_PDF);        
			try {
				pm = PMF.get().getPersistenceManager();
				PedidoDAO dao = new PedidoDAO(pm);
				this.pedido = dao.pesquisar(request.getParameter("uuid"));
				if(this.pedido!=null){
			
					Table table = null;            
		            List<List<Cell>> dados = null;
		            List<Cell> registro = null;
		            
		            Cell coluna0 = null, coluna1 = null, coluna2 = null, coluna3 = null, coluna4 = null;
					
					pdf = new PDF(outStream);			
					page = new Page(pdf,A4.PORTRAIT);
					
					imprimirCabecario();
					
					top = 20d; left = 20d;
					
					imprimirMestre();
					
					top += 2.00;
		            
		            table = new Table(font,font);
		            
		            dados = new ArrayList<List<Cell>>();
		            
		            font = new Font(pdf,CoreFont.HELVETICA_BOLD);            
					font.setSize(7.0);
		            
					coluna0 = new Cell(font,bundle.getString("item.numero"));
					coluna1 = new Cell(font,bundle.getString("item.produto"));
					coluna2 = new Cell(font,bundle.getString("item.quantidade"));
					coluna3 = new Cell(font,bundle.getString("item.valor"));
					coluna4 = new Cell(font,bundle.getString("item.total"));
		            
					coluna0.setWidth( 65.00);
		            coluna1.setWidth(185.00);
		            coluna2.setWidth(100.00);
		            coluna3.setWidth(100.00);
		            coluna4.setWidth(100.00);
		            
		            coluna0.setNoBorders();
		            coluna1.setNoBorders();
		            coluna2.setNoBorders();
		            coluna3.setNoBorders();
		            coluna4.setNoBorders();
		            
		            coluna2.setTextAlignment(Align.RIGHT);
		            coluna3.setTextAlignment(Align.RIGHT);
		            coluna4.setTextAlignment(Align.RIGHT);
		            
		            registro = new ArrayList<Cell>();
		            registro.add(coluna0);
		            registro.add(coluna1);
		            registro.add(coluna2);
		            registro.add(coluna3);
		            registro.add(coluna4);
		            dados.add(registro);
		            
		            font = new Font(pdf,CoreFont.HELVETICA);            
					font.setSize(7.0);
					
					Double qtdTotal = 0.0, vlrTotal = 0.0; 
					
					for(Item item : this.pedido.getItens()){

						qtdTotal += item.getQuantidade();
						vlrTotal += (item.getQuantidade()*item.getValor());
						
						coluna0 = new Cell(font,item.getProduto().getNumero());
						coluna1 = new Cell(font,item.getProduto().getNome());
						coluna2 = new Cell(font,Util.doubleToString(item.getQuantidade(),Constantes.MASCARA_VALOR_TRES_CASAS_DECIMAIS));
						coluna3 = new Cell(font,Util.doubleToString(item.getValor(),Constantes.MASCARA_VALOR_TRES_CASAS_DECIMAIS));
						coluna4 = new Cell(font,Util.doubleToString((item.getQuantidade()*item.getValor()),Constantes.MASCARA_VALOR_DUAS_CASAS_DECIMAIS));
						
						coluna0.setWidth( 65.00);
						coluna1.setWidth(185.00);
		                coluna2.setWidth(100.00);
		                coluna3.setWidth(100.00);
		                coluna4.setWidth(100.00);
		                
		                registro = new ArrayList<Cell>();
		                
		                coluna0.setNoBorders();
		                coluna1.setNoBorders();
		                coluna2.setNoBorders();
		                coluna3.setNoBorders();
		                coluna4.setNoBorders();
		                
		                coluna2.setTextAlignment(Align.RIGHT);
		                coluna3.setTextAlignment(Align.RIGHT);
		                coluna4.setTextAlignment(Align.RIGHT);
		                
		                registro.add(coluna0);
		                registro.add(coluna1);
		                registro.add(coluna2);
		                registro.add(coluna3);
		                registro.add(coluna4);
		                
		                dados.add(registro);
		                
		                /*
		                 * IMPRIMINDO ITENS DO PEDIDO
		                 */
		                if(!item.getObs().equals(null)){
		                	
		                	Font fontePequena = new Font(pdf,CoreFont.HELVETICA);            
		                	fontePequena.setSize(6.0);
		                	
		                	final int divisor = 70;
		                	
		                	//dividendo = item.getObs().length(), 
		                	int resto = item.getObs().length()%divisor; 
		                	double quociente = item.getObs().length()/divisor;
		                	
		                	int i = 1, pos = 0;		                	
		                	while(i<=quociente){
		                		
		                		coluna0 = new Cell(fontePequena);
								coluna1 = new Cell(fontePequena,item.getObs().substring(pos,pos+divisor));
								coluna2 = new Cell(fontePequena);
								coluna3 = new Cell(fontePequena);
								coluna4 = new Cell(fontePequena);
								
								coluna0.setWidth( 65.00);
								coluna1.setWidth(185.00);
				                coluna2.setWidth(100.00);
				                coluna3.setWidth(100.00);
				                coluna4.setWidth(100.00);
				                
				                registro = new ArrayList<Cell>();
				                
				                coluna0.setNoBorders();
				                coluna1.setNoBorders();
				                coluna2.setNoBorders();
				                coluna3.setNoBorders();
				                coluna4.setNoBorders();
				                
				                coluna2.setTextAlignment(Align.RIGHT);
				                coluna3.setTextAlignment(Align.RIGHT);
				                coluna4.setTextAlignment(Align.RIGHT);
				                
				                registro.add(coluna0);
				                registro.add(coluna1);
				                registro.add(coluna2);
				                registro.add(coluna3);
				                registro.add(coluna4);
				                
				                dados.add(registro);
		                		
		                		pos+=divisor;
		                		++i;
		                		if(i>quociente&&resto>0){
		                			coluna0 = new Cell(fontePequena);
									coluna1 = new Cell(fontePequena,item.getObs().substring(item.getObs().length()-resto,item.getObs().length()));
									coluna2 = new Cell(fontePequena);
									coluna3 = new Cell(fontePequena);
									coluna4 = new Cell(fontePequena);
									
									coluna0.setWidth( 65.00);
									coluna1.setWidth(185.00);
					                coluna2.setWidth(100.00);
					                coluna3.setWidth(100.00);
					                coluna4.setWidth(100.00);
					                
					                registro = new ArrayList<Cell>();
					                
					                coluna0.setNoBorders();
					                coluna1.setNoBorders();
					                coluna2.setNoBorders();
					                coluna3.setNoBorders();
					                coluna4.setNoBorders();
					                
					                coluna2.setTextAlignment(Align.RIGHT);
					                coluna3.setTextAlignment(Align.RIGHT);
					                coluna4.setTextAlignment(Align.RIGHT);
					                
					                registro.add(coluna0);
					                registro.add(coluna1);
					                registro.add(coluna2);
					                registro.add(coluna3);
					                registro.add(coluna4);
					                
					                dados.add(registro);
		                		}
		                	}
		                	
		                }
						
					}
					
					coluna0 = new Cell(font,"");
		            coluna1 = new Cell(font,"");
		            coluna2 = new Cell(font,"-------------------------");
		            coluna3 = new Cell(font,"");
		            coluna4 = new Cell(font,"-------------------------");
		            
		            coluna0.setWidth( 50.00);
		            coluna1.setWidth(200.00);
		            coluna2.setWidth(100.00);
		            coluna3.setWidth(100.00);
		            coluna4.setWidth(100.00);
		            
		            coluna0.setNoBorders();
		            coluna1.setNoBorders();
		            coluna2.setNoBorders();
		            coluna3.setNoBorders();
		            coluna4.setNoBorders();
		            
		            coluna2.setTextAlignment(Align.RIGHT);
		            coluna3.setTextAlignment(Align.RIGHT);
		            coluna4.setTextAlignment(Align.RIGHT);
		            
		            registro = new ArrayList<Cell>();
		            registro.add(coluna0);
		            registro.add(coluna1);
		            registro.add(coluna2);
		            registro.add(coluna3);
		            registro.add(coluna4);
		            dados.add(registro);
		            
		            font = new Font(pdf,CoreFont.HELVETICA_BOLD);            
					font.setSize(7.0);
		            
					coluna0 = new Cell(font,"");
		            coluna1 = new Cell(font,"");
		            coluna2 = new Cell(font,Util.doubleToString(qtdTotal,Constantes.MASCARA_VALOR_DUAS_CASAS_DECIMAIS));
		            coluna3 = new Cell(font,"");
		            coluna4 = new Cell(font,Util.doubleToString(vlrTotal,Constantes.MASCARA_VALOR_DUAS_CASAS_DECIMAIS));
		            
		            coluna0.setWidth( 50.00);
		            coluna1.setWidth(200.00);
		            coluna2.setWidth(100.00);
		            coluna3.setWidth(100.00);
		            coluna4.setWidth(100.00);
		            
		            coluna0.setNoBorders();
		            coluna1.setNoBorders();
		            coluna2.setNoBorders();
		            coluna3.setNoBorders();
		            coluna4.setNoBorders();
		            
		            coluna2.setTextAlignment(Align.RIGHT);
		            coluna3.setTextAlignment(Align.RIGHT);
		            coluna4.setTextAlignment(Align.RIGHT);
		            
		            registro = new ArrayList<Cell>();
		            registro.add(coluna0);
		            registro.add(coluna1);
		            registro.add(coluna2);
		            registro.add(coluna3);
		            registro.add(coluna4);
		            dados.add(registro);
		            
			        table.setData(dados,Table.DATA_HAS_1_HEADER_ROWS);
			        table.setPosition(left,top);                   

			        int paginas = table.getNumberOfPages(page);
			        int pagina = 1;
			        
					while (true) {

						imprimirCabecario();
						top = 20d; left = 20d;	            
			            imprimirMestre();	                        
			            
						table.drawOn(page);
						
						point1 = new Point(20, 36);
				        point2 = new Point(550,36);
				        line = new Line( point1.getX(), point1.getY() + point2.getY(), point1.getX() + point2.getX(),
				        		point1.getY() + point2.getY());
				        line.drawOn(page);
						
						point1 = new Point(20, 405);
				        point2 = new Point(550,405);
				        Line line = new Line( point1.getX(), point1.getY() + point2.getY(), point1.getX() + point2.getX(),
				        		point1.getY() + point2.getY());
				        line.drawOn(page);
				        
						font = new Font(pdf,CoreFont.HELVETICA);            
						font.setSize(7.0);
						
						textLine = new TextLine(font);
						textLine.setText(bundle.getString("app.direito"));
						textLine.setPosition(20.0, 820.0);
						textLine.drawOn(page);
						
						textLine = new TextLine(font);
						textLine.setText(Constantes.VERSAO);
						textLine.setPosition(85.0, 820.0);
						textLine.drawOn(page);
						
						textLine = new TextLine(font);
						textLine.setText(pagina++ + " / " + paginas);
						textLine.setPosition(300.0, 820.0);
						textLine.drawOn(page);
						
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constantes.MASCARA_DATA_HORA_PADRAO);
						simpleDateFormat.setTimeZone(TimeZone.getTimeZone(Constantes.GMT_BRASIL));
						
						textLine = new TextLine(font,simpleDateFormat.format(new Date()));
			            textLine.setPosition(493.0,820.0);
			            textLine.setColor(RGB.BLACK);
			            textLine.drawOn(page);
						
						if (!table.hasMoreData()) break;
						page = new Page(pdf, A4.PORTRAIT);
					}
					
		            pdf.flush();
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				pm.close();
			}
		}else{
			logger.log(Level.SEVERE,bundle.getString("app.mensagem.uuid.invalido"));
			throw new IOException();
		}
		
	}
	
}