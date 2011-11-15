package br.com.appestoque.dao;

import java.io.Serializable;

public interface IDAO<T, PK extends Serializable> {
	void criar(T entidade);
	void remover(T entidade);
}
