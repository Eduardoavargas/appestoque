package br.com.appestoque.controle;

import javax.servlet.http.HttpServlet;

import br.com.appestoque.comum.Constantes;

@SuppressWarnings("serial")
public class BaseControle extends HttpServlet {
	
	private int primeiroRegistro;
	protected int totalRegistros;
	protected int paginaCorrente;
	protected int totalPaginas;
	protected int paginaFaixa;
	protected Integer[] paginas;
	
	public void paginar(int registro){
		this.primeiroRegistro = registro;
		paginaCorrente = (totalRegistros / Constantes.REGISTROS_POR_PAGINA) - ((totalRegistros - primeiroRegistro) / Constantes.REGISTROS_POR_PAGINA) + 1;
        totalPaginas = (totalRegistros / Constantes.REGISTROS_POR_PAGINA) + ((totalRegistros % Constantes.REGISTROS_POR_PAGINA != 0) ? 1 : 0);
        int paginaTamanho = Math.min(paginaFaixa, totalPaginas);
        paginas = new Integer[paginaTamanho];
        int paginaPrimeiro = Math.min(Math.max(0, paginaCorrente - (paginaFaixa / 2)), totalPaginas - paginaTamanho);
        for (int i = 0; i < paginaTamanho; i++) {
            paginas[i] = ++paginaPrimeiro;
        }
	}

	public int getPrimeiroRegistro() {
		return primeiroRegistro;
	}

}