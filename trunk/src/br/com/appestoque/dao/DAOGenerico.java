package br.com.appestoque.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import javax.jdo.PersistenceManager;

public class DAOGenerico <T, PK extends Serializable> implements IDAO<T, PK>{
	
	protected Class<T> tipo;
	private PersistenceManager pm;

	@SuppressWarnings("unchecked")
	public DAOGenerico() {
        this.tipo = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
	
	public PersistenceManager getPm() {
		return pm;
	}

	public void setPm(PersistenceManager pm) {
		this.pm = pm;
	}

	@Override
	public void criar(T entidade) {
		pm.makePersistent(entidade);
	}
	
	@Override
	public void remover(T entidade) {
		pm.deletePersistent(entidade);
	}

}