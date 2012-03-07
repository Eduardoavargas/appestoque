package br.com.appestoque.dominio.faturamento;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import br.com.appestoque.dominio.cadastro.Cliente;

@SuppressWarnings("serial")
@PersistenceCapable
public class Pedido implements Serializable{
	
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	private String numero;

	@Persistent
	private Date data;
	
	@Persistent
	private Long idRepresentante;
	
	@Persistent
	private Long idCliente;
	
	@NotPersistent
	private Cliente cliente;
	
	public Pedido(	String numero, 
					Date data, 
					Long idRepresentante,
					Long idCliente, 
					Double valor){
		super();
		this.numero = numero;
		this.data = data;
		this.idRepresentante = idRepresentante;
		this.idCliente = idCliente;
		this.valor = valor;
	}

	@Persistent
	private Double valor = new Double(0);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Long getIdRepresentante() {
		return idRepresentante;
	}

	public void setIdRepresentante(Long idRepresentante) {
		this.idRepresentante = idRepresentante;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}