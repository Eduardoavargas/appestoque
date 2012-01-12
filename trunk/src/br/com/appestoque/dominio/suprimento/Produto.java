package br.com.appestoque.dominio.suprimento;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class Produto {

	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	private String nome;
	
	@Persistent
	private String numero;
	
	@Persistent
	private Double preco = new Double(0);
	
	@Persistent
	private Double estoque = new Double(0);
	
	@Persistent
	private Long idEmpresa;
	
	@Persistent
	private String imagem1;
	
	@Persistent
	private String imagem2;
	
	@Persistent
	private String imagem3;
	
	@Persistent
	private String imagem4;
	
	@Persistent
	private String imagem5;
	
	public Produto(){
		super();
	}
	
	public Produto(String nome, String numero, Double preco, Double estoque, 
			String imagem1, String imagem2, String imagem3, 
			String imagem4, String imagem5, Long idEmpresa) {
		super();
		this.nome = nome;
		this.numero = numero;
		this.preco = preco;
		this.estoque = estoque;
		this.imagem1 = imagem1;
		this.imagem2 = imagem2;
		this.imagem3 = imagem3;
		this.imagem4 = imagem4;
		this.imagem5 = imagem5;
		this.idEmpresa = idEmpresa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	public Double getEstoque() {
		return estoque;
	}

	public void setEstoque(Double estoque) {
		this.preco = estoque;
	}

	public String getImagem1() {
		return imagem1;
	}

	public void setImagem1(String imagem1) {
		this.imagem1 = imagem1;
	}

	public String getImagem2() {
		return imagem2;
	}

	public void setImagem2(String imagem2) {
		this.imagem2 = imagem2;
	}

	public String getImagem3() {
		return imagem3;
	}

	public void setImagem3(String imagem3) {
		this.imagem3 = imagem3;
	}

	public String getImagem4() {
		return imagem4;
	}

	public void setImagem4(String imagem4) {
		this.imagem4 = imagem4;
	}

	public String getImagem5() {
		return imagem5;
	}

	public void setImagem5(String imagem5) {
		this.imagem5 = imagem5;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
}