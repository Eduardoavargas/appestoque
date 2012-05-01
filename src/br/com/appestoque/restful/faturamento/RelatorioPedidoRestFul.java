package br.com.appestoque.restful.faturamento;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.appestoque.Constantes;
import br.com.appestoque.Util;
import br.com.appestoque.dao.PMF;
import br.com.appestoque.dao.faturamento.PedidoDAO;
import br.com.appestoque.dominio.faturamento.Pedido;

import com.pdfjet.Align;
import com.pdfjet.Cell;
import com.pdfjet.Font;
import com.pdfjet.Letter;
import com.pdfjet.PDF;
import com.pdfjet.Page;
import com.pdfjet.Point;
import com.pdfjet.Table;
import com.pdfjet.TextLine;


@SuppressWarnings("serial")
public class RelatorioPedidoRestFul extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OutputStream outStream;	
		PersistenceManager pm = null;
		
		ResourceBundle bundle = ResourceBundle.getBundle(Constantes.I18N,request.getLocale());
		
		
		try {
			
			pm = PMF.get().getPersistenceManager();
			PedidoDAO dao = new PedidoDAO(pm);
			Pedido pedido = dao.pesquisar("1AC");
			
			outStream = response.getOutputStream();
			response.setContentType("application/pdf");
			PDF pdf = new PDF(outStream);
			Page page = new Page(pdf, Letter.PORTRAIT);
			
			Font fontTitulo = new Font(pdf, "Helvetica");
			fontTitulo.setSize(10.0);
			
			Font font = new Font(pdf, "Helvetica");
			font.setSize(7.0);		
			
			TextLine textLine = null;
			
			textLine = new TextLine(fontTitulo,"Pedido de Venda");  
			textLine.setPosition(70.0,30.00);			
			textLine.drawOn(page);
			
			Double top = 30d;
			Double left = 70d;
			
			top += 20.00;
			
			textLine = new TextLine(font,bundle.getString("pedido.numero"));  
			textLine.setPosition(left,top);			
			textLine.drawOn(page);
			
			textLine = new TextLine(font,bundle.getString("pedido.data"));  
			textLine.setPosition(left+50,top);			
			textLine.drawOn(page);
			
			textLine = new TextLine(font,bundle.getString("pedido.hora"));  
			textLine.setPosition(left+100,top);			
			textLine.drawOn(page);
			
			textLine = new TextLine(font,bundle.getString("pedido.cliente"));  
			textLine.setPosition(left+150,top);			
			textLine.drawOn(page);
			
			textLine = new TextLine(font,bundle.getString("pedido.representante"));  
			textLine.setPosition(left+200,top);			
			textLine.drawOn(page);
			
//			bundle.getString("pedido.obs")
			
			top += 10.00;
			
			textLine = new TextLine(font,pedido.getNumero());  
			textLine.setPosition(left,top);			
			textLine.drawOn(page);
			
			textLine = new TextLine(font,Util.dateToStr(Constantes.MASCARA_DATA_PADRAO,pedido.getData()));  
			textLine.setPosition(left+50,top);			
			textLine.drawOn(page);
			
			textLine = new TextLine(font,Util.dateToStr(Constantes.MASCARA_HORA_PADRAO,pedido.getData()));  
			textLine.setPosition(left+100,top);			
			textLine.drawOn(page);
			
			textLine = new TextLine(font,pedido.getCliente().getNome());  
			textLine.setPosition(left+150,top);			
			textLine.drawOn(page);
			
			textLine = new TextLine(font,pedido.getRepresentante().getNome());  
			textLine.setPosition(left+200,top);			
			textLine.drawOn(page);
			
			
			/*
			
			Table tabela = new Table(font,font);
			
			List<List<Cell>> dados = new ArrayList<List<Cell>>();
			List<Cell> registro = null;
			
			Cell coluna1 = null;
			Cell coluna2 = null;
			Cell coluna3 = null;
			
			coluna1 = new Cell(font,"COLUNA 1");
			coluna2 = new Cell(font,"COLUNA 2");
			coluna3 = new Cell(font,"COLUNA 3");
			
			coluna1.setNoBorders();
			coluna2.setNoBorders();
			coluna3.setNoBorders();			
			coluna3.setTextAlignment(Align.RIGHT);
			
			registro = new ArrayList<Cell>();
			registro.add(coluna1);
			registro.add(coluna2);
			registro.add(coluna3);
			dados.add(registro);
			
			for(int i=0; i<237;++i){
				coluna1 = new Cell(font,"DADO " + i);
				coluna2 = new Cell(font,"DADO " + i);
				coluna3 = new Cell(font,String.valueOf(i));
				registro = new ArrayList<Cell>();
				coluna1.setNoBorders();
				coluna2.setNoBorders();
				coluna3.setNoBorders();
				registro.add(coluna1);
				registro.add(coluna2);
				registro.add(coluna3);
				dados.add(registro);
			}
			
			tabela.setData(dados,Table.DATA_HAS_1_HEADER_ROWS);
			
			for(int i=0; i<237;++i){
				tabela.removeLineBetweenRows(0,i+1);
			}
			
			tabela.setPosition(70.0, 35.0);			
			tabela.autoAdjustColumnWidths();
			tabela.setColumnWidth(0,120);
			tabela.setColumnWidth(1,120);
			tabela.setColumnWidth(2,120);
			tabela.rightAlignNumbers();
			
			int numOfPages = tabela.getNumberOfPages(page);
			while (true) {
				textLine.drawOn(page);
				Point point = tabela.drawOn(page);
				if (!tabela.hasMoreData())
					break;
				page = new Page(pdf, Letter.PORTRAIT);
			}
			
			*/
			
			pdf.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			pm.close();
		}
		
	}
	
}
