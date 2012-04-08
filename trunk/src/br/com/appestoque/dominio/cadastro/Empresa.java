package br.com.appestoque.dominio.cadastro;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@SuppressWarnings("serial")
@PersistenceCapable
public class Empresa implements Serializable{
	
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	private String nome;
	
	@Persistent
	private String razao;
	
	@Persistent
	private String email;
	
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
	private String bairro;
	
	@Persistent
	private String cidade;
	
	@Persistent
	private String uuid;
	
	@Persistent
	private Boolean ativo = false;
	
	@Persistent
	private Long bairros = new Long(0);
	
	@Persistent
	private Long cidades = new Long(0);
	
	@Persistent
	private Long clientes = new Long(0);
	
	@Persistent
	private Long representantes = new Long(0);
	
	@Persistent
	private Long pedidos = new Long(0);
	
	@Persistent
	private Long usuarios = new Long(0);
	
	@Persistent
	private Long produtos = new Long(0);

	public Empresa() {
		super();
	}

	public Empresa(String nome, String razao, String email, String cnpj, String endereco, Integer numero,
			String cep, String complemento, String bairro, String cidade, String uuid) {
		super();
		this.nome = nome;
		this.razao = razao;
		this.email = email;
		this.cnpj = cnpj;
		this.endereco = endereco;
		this.numero = numero;
		this.cep = cep;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uuid = uuid;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
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

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Long getBairros() {
		return bairros;
	}

	public void setBairros(Long bairros) {
		this.bairros = bairros;
	}

	public Long getCidades() {
		return cidades;
	}

	public void setCidades(Long cidades) {
		this.cidades = cidades;
	}

	public Long getClientes() {
		return clientes;
	}

	public void setClientes(Long clientes) {
		this.clientes = clientes;
	}

	public Long getRepresentantes() {
		return representantes;
	}

	public void setRepresentantes(Long representantes) {
		this.representantes = representantes;
	}

	public Long getPedidos() {
		return pedidos;
	}

	public void setPedidos(Long pedidos) {
		this.pedidos = pedidos;
	}

	public Long getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Long usuarios) {
		this.usuarios = usuarios;
	}

	public Long getProdutos() {
		return produtos;
	}

	public void setProdutos(Long produtos) {
		this.produtos = produtos;
	}
	
}