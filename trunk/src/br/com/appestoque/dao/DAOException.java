package br.com.appestoque.dao;

@SuppressWarnings("serial")
public class DAOException extends Exception{

    public DAOException() {
    }

    public DAOException(String msg) {
        super(msg);
    }

	public DAOException(Throwable throwable) {
		super(throwable);
	}

	
}
