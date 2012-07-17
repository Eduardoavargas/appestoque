package br.com.appestoque.restful.suprimento;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.logging.Level;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.AsyncDatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

import br.com.appestoque.restful.BaseRESTFul;
import br.com.appestoque.seguranca.Criptografia;
import br.com.appestoque.seguranca.HashCode;
import br.com.appestoque.util.Constantes;
import br.com.appestoque.util.Conversor;

@SuppressWarnings("serial")
public class LimparProdutoRestFul extends BaseRESTFul{

	@Override
	public void processServer(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		if(request.getParameter("uuid")!=null&&request.getParameter("cnpj")!=null){
			super.processServer(request, response);
			
			String uuid = request.getParameter("uuid");
			String cnpj = request.getParameter("cnpj");
			
			Criptografia criptografia = new Criptografia();
			
			try {
				uuid = criptografia.decifrar(Conversor.stringToByte(uuid,Constantes.DELIMITADOR));
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (BadPaddingException e) {
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			AsyncDatastoreService datastore = DatastoreServiceFactory.getAsyncDatastoreService();
			Query query = new Query("Empresa");
			query.setFilter(new FilterPredicate("uuid",FilterOperator.EQUAL,uuid));
			Entity empresa = datastore.prepare(query).asSingleEntity();
			if(empresa!=null){					
				HashCode hashCode = new HashCode();
				if(hashCode.processar(empresa.getProperty("cnpj").toString()).equals(cnpj)){
					query = new Query("Produto");
					query.setFilter(new FilterPredicate("idEmpresa",FilterOperator.EQUAL,empresa.getKey().getId()));
					for (Entity entity : datastore.prepare(query).asIterable()) {
						datastore.delete(entity.getKey());
					}
				}else{
					logger.log(Level.SEVERE,bundle.getString("app.mensagem.hash.invalido"));
					throw new IOException();
				}
			}else{
				logger.log(Level.SEVERE,bundle.getString("app.mensagem.uuid.invalido"));
				throw new IOException();
			}
			
		}else{
			logger.log(Level.SEVERE,bundle.getString("app.mensagem.parametro.nao.informado"));
			throw new IOException();
		}
	}
	
}