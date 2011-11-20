package br.com.appestoque.controle;

public class BaseControle {
	
	private int primeiroRegistro;
	private int totalRegistros;
	private int paginaCorrente;
	private int totalPaginas;
	public int getPrimeiroRegistro() {
		return primeiroRegistro;
	}
	public void setPrimeiroRegistro(int primeiroRegistro) {
		this.primeiroRegistro = primeiroRegistro;
	}
	public int getTotalRegistros() {
		return totalRegistros;
	}
	public void setTotalRegistros(int totalRegistros) {
		this.totalRegistros = totalRegistros;
	}
	public int getPaginaCorrente() {
		return paginaCorrente;
	}
	public void setPaginaCorrente(int paginaCorrente) {
		this.paginaCorrente = paginaCorrente;
	}
	public int getTotalPaginas() {
		return totalPaginas;
	}
	public void setTotalPaginas(int totalPaginas) {
		this.totalPaginas = totalPaginas;
	}

}
