package br.com.appestoque.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class DAOGenerico<T, PK extends Serializable> implements IDAO<T, PK> {

	protected Class<T> tipo;
	private PersistenceManager pm;

	@SuppressWarnings("unchecked")
	public DAOGenerico() {
		this.tipo = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public PersistenceManager getPm() {
		return pm;
	}

	public void setPm(PersistenceManager pm) {
		this.pm = pm;
	}

	@Override
	public void criar(T entidade) {
		try {
			pm.currentTransaction().begin();
			pm.makePersistent(entidade);
			pm.currentTransaction().commit();
		} finally {
			if (pm.currentTransaction().isActive()) {
				pm.currentTransaction().rollback();
			}
		}
	}
	
	@Override
	public T adicionar(T entidade) {
		try {
			pm.currentTransaction().begin();
			pm.makePersistent(entidade);
			pm.currentTransaction().commit();
			return entidade;
		} finally {
			if (pm.currentTransaction().isActive()) {
				pm.currentTransaction().rollback();
			}
		}
	}

	@Override
	public void remover(T entidade) {
		try {
			pm.currentTransaction().begin();
			pm.deletePersistent(entidade);
			pm.currentTransaction().commit();
		} finally {
			if (pm.currentTransaction().isActive()) {
				pm.currentTransaction().rollback();
			}
		}
	}

	@Override
	public T pesquisar(Long id) {
		T t = null;
		Key k = KeyFactory.createKey(tipo.getSimpleName(), id.intValue());
		try{
			t = pm.getObjectById(tipo, k);
		}catch(JDOObjectNotFoundException e){
			e.printStackTrace();
		}
		return t;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listar() {
		Query query = pm.newQuery(tipo);
		return (List<T>) query.execute();
	}

}