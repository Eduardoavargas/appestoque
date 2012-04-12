package br.com.appestoque.controle;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.appestoque.Constantes;
import br.com.appestoque.dominio.cadastro.Empresa;

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
	
	public Long getId(HttpServletRequest request){
		HttpSession session = request.getSession();
		Empresa empresa = (Empresa) session.getAttribute("empresa");
		return empresa.getId();
	}

}