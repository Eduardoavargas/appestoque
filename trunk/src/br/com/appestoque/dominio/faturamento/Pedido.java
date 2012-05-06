package br.com.appestoque.dominio.faturamento;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import br.com.appestoque.dominio.cadastro.Cliente;
import br.com.appestoque.dominio.cadastro.Representante;

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
	
	@NotPersistent
	private Representante representante;
	
	@Persistent
	private Long idCliente;
	
	@NotPersistent
	private Cliente cliente;
	
	@Persistent
	private Long idEmpresa;
	
	@Persistent
	private String obs;
	
	@Persistent
	private String uuid;
	
	@NotPersistent
	private List<Item> itens;
	
	public Pedido() {
		super();
	}
	
	public Pedido(	String numero, 
					Date data, 
					Long idRepresentante,
					Long idCliente,
					Long idEmpresa,
					String obs,
					String uuid){
		super();
		this.numero = numero;
		this.data = data;
		this.idRepresentante = idRepresentante;
		this.idCliente = idCliente;
		this.idEmpresa = idEmpresa;
		this.obs = obs;
		this.uuid = uuid;
	}

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
	
	public Representante getRepresentante() {
		return representante;
	}

	public void setRepresentante(Representante representante) {
		this.representante = representante;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}