package br.com.appestoque.dominio.cadastro;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@SuppressWarnings("serial")
@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class Bairro implements Serializable{
	
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	private String nome;
	
	@Persistent
	private Long idCidade;
	
	@Persistent
	private Long idEmpresa;
	
	@NotPersistent
	private Cidade cidade;

	public Bairro() {
		super();
	}

	public Bairro(String nome, Long idCidade, Long idEmpresa) {
		super();
		this.nome = nome;
		this.idCidade = idCidade;
		this.idEmpresa = idEmpresa;
	}

	public Bairro(String nome, Cidade cidade, Empresa empresa) {
		super();
		this.nome = nome;
		this.idCidade = cidade.getId();
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

	public Long getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(Long idCidade) {
		this.idCidade = idCidade;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

}