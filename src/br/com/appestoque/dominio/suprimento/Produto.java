package br.com.appestoque.dominio.suprimento;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import br.com.appestoque.dominio.cadastro.Empresa;

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
	private Long idEmpresa;
	
	public Produto(){
		super();
	}
	
	public Produto(	String nome, 
					String numero, 
					Double preco, 
					Long idEmpresa) {
		super();
		this.nome = nome;
		this.numero = numero;
		this.preco = preco;
		this.idEmpresa = idEmpresa;
	}
	
	public Produto(String nome, String numero, Double preco, 
			Empresa empresa) {
		super();
		this.nome = nome;
		this.numero = numero;
		this.preco = preco;
		this.idEmpresa = empresa.getId();
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
	
	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
}