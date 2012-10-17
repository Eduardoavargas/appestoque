package br.com.appestoque;

public class Constantes {
	
	public static final String SITE = "wwww.appestoque.com.br";

	public static final String CONTENT_TYPE_PDF = "application/pdf";
	
	public static final String VERSAO = "Beta 1.0.3";
	
	//public static final String URL = "http://www.appestoque.com.br";
	//public static final String URL = "http://localhost:8888";
	//public static final String URL = "http://4.appestoque.appspot.com/";
	public static final String URL = "http://appestoque.appspot.com/";
	
	public static final String URI_CONFIRMAR = "/processo?acao=confirmar";
	public static final String URI_PEDIDO_VENDA = "/RESTFul/relatorioPedido";
	
	public static final String I18N = "i18n";
	
	public static final String MASCARA_VALOR_DUAS_CASAS_DECIMAIS = "##,###,##0.00";
	public static final String MASCARA_VALOR_TRES_CASAS_DECIMAIS = "##,###,##0.000";
	public static String MASCARA_PRECO = "##,###,##0.000";
	public static String MASCARA_ESTOQUE = "##,###,##0.000";
	
	public static String MASCARA_DATA_PADRAO = "dd/MM/yyyy";
	public static String MASCARA_HORA_PADRAO = "hh:mm:ss";
	public static String MASCARA_DATA_HORA_PADRAO = "dd/MM/yyyy hh:mm:ss a";
	public static final String GMT_BRASIL = "GMT-3:00"; 
	
	public static final Integer PRECISAO_PRECO = 3;
	public static final Integer PRECISAO_ESTOQUE = 3;
	
	public static final Integer PRECISAO_VALOR = 3;
	
	public static final String [] uris = {"/RESTFul/produto","/RESTFul/cliente","/RESTFul/pedido"};
	
	public static final String EMAIL_ADMINISTRADOR = "andre.tricano@gmail.com";
	
	public static final String ASSUNTO_CADASTRO = "Confirme sua conta do Appestoque e seja bem vindo!";
	public static final String ASSUNTO_PEDIDO_VENDA = "[APPESTOQUE] Pedido de Venda ";
	
	public static final int REGISTROS_POR_PAGINA = 10;
	
}