package br.com.appestoque.dao;

import java.io.Serializable;

public interface IDAO<T, PK extends Serializable> {
	void incluir(T entidade);
	void excluir(T entidade);
}
