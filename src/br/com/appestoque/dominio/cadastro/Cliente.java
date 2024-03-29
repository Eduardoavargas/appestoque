package br.com.appestoque.dominio.cadastro;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@SuppressWarnings("serial")
@PersistenceCapable
public class Cliente implements Serializable{

	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	private String nome;
	
	@Persistent
	private String razao;
	
	@Persistent
	private String cnpj;
	
	@Persistent
	private String endereco;
	
	@Persistent
	private Integer numero = new Integer(0);
	
	@Persistent
	private String cep;
	
	@Persistent
	private String complemento;
	
	@Persistent
	private Long idBairro;
	
	@Persistent
	private Long idEmpresa;
	
	@NotPersistent
	private Bairro bairro;
	
	public Cliente(	String nome, 
					String cnpj, 
					String endereco,
					String complemento,
					Integer numero, 
					String cep,
					Long idBairro, 
					Long idEmpresa) {
		super();
		this.nome = nome;
		this.cnpj = cnpj;
		this.numero = numero;
		this.cep = cep;
		this.complemento = complemento;
		this.idBairro = idBairro;
		this.idEmpresa = idEmpresa;
		this.endereco = endereco;
	}
	
	public Cliente(	String nome, 
					String razao,
					String cnpj, 
					String endereco,
					String complemento, 
					Integer numero, 
					String cep, 
					Bairro bairro,
					Empresa empresa) {
		super();
		this.nome = nome;
		this.razao = razao;
		this.cnpj = cnpj;
		this.numero = numero;
		this.cep = cep;
		this.complemento = complemento;
		this.idBairro = bairro.getId();
		this.idEmpresa = empresa.getId();
		this.endereco = endereco;
	}

	public Cliente() {
		super();
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
	
	public String getRazao() {
		return razao;
	}

	public void setRazao(String razao) {
		this.razao = razao;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Long getIdBairro() {
		return idBairro;
	}

	public void setIdBairro(Long idBairro) {
		this.idBairro = idBairro;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
}