package br.com.appestoque.dominio.suprimento;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@SuppressWarnings("serial")
@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class Produto implements Serializable{

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
	
	public Produto(){
		super();
	}
	
	public Produto(String nome, String numero, Double preco, Double estoque, Long idEmpresa) {
		super();
		this.nome = nome;
		this.numero = numero;
		this.preco = preco;
		this.estoque = estoque;
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

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
}