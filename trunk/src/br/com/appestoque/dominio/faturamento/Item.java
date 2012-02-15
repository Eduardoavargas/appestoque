package br.com.appestoque.dominio.faturamento;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

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

	public Item( Long idPedido, 
				 Long idProduto, 
				 Double quantidade, 
				 Double valor) {
		super();
		this.quantidade = quantidade;
		this.valor = valor;
		this.idPedido = idPedido;
		this.idProduto = idProduto;
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
	
}