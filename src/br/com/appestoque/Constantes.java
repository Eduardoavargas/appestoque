package br.com.appestoque;

public class Constantes {

	public static String URL = "http://2.appestoque.appspot.com";
	public static String URI_CONFIRMAR = "/processo?acao=confirmar";
	public static String URI_PEDIDO_VENDA = "/RESTFul/relatorioPedido";
	
	public static String I18N = "i18n";
	
	public static final String MASCARA_VALOR_DUAS_CASAS_DECIMAIS = "##,###,##0.00";
	public static final String MASCARA_VALOR_TRES_CASAS_DECIMAIS = "##,###,##0.000";
	
	public static String MASCARA_PRECO = "##,###,##0.000";
	public static String MASCARA_ESTOQUE = "##,###,##0.000";
	public static String MASCARA_DATA_PADRAO = "dd/MM/yyyy";
	public static String MASCARA_HORA_PADRAO = "hh:mm:ss";
	
	public static Integer PRECISAO_PRECO = 3;
	public static Integer PRECISAO_ESTOQUE = 3;
	
	public static Integer PRECISAO_VALOR = 3;
	
	public static String [] uris = {"/RESTFul/produto","/RESTFul/cliente","/RESTFul/pedido"};
	
	public static String EMAIL_ADMINISTRADOR = "andre.tricano@gmail.com";
	
	public static String ASSUNTO_CADASTRO = "Confirme sua conta do Appestoque e seja bem vindo!";
	public static String ASSUNTO_PEDIDO_VENDA = "[APPESTOQUE] Pedido de Venda ";
	
	public static final int REGISTROS_POR_PAGINA = 10;
	
}