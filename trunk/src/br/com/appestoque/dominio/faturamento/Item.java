package br.com.appestoque.dominio.faturamento;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import br.com.appestoque.dominio.suprimento.Produto;

@SuppressWarnings("serial")
@PersistenceCapable
public class Item implements Serializable{

	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	private Double quantidade = new Double(0);
	
	@Persistent
	private Double valor = new Double(0);
	
	@Persistent
	private Long idPedido;
	
	@Persistent
	private Long idProduto;
	
	@NotPersistent
	private Produto produto;
	
	@Persistent
	private String obs;

	public Item( Long idPedido, 
				 Long idProduto, 
				 Double quantidade, 
				 Double valor,
				 String obs) {
		super();
		this.quantidade = quantidade;
		this.valor = valor;
		this.idPedido = idPedido;
		this.idProduto = idProduto;
		this.obs = obs;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}
	
}