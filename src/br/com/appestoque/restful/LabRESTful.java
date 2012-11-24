package br.com.appestoque.restful;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LabRESTful  extends HttpServlet {
	
	private static final long serialVersionUID = -7945450236066527190L;
	
	protected Logger logger = Logger.getLogger(getClass().getCanonicalName());
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)	throws IOException {
		processServer(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		processServer(request, response);
	}
	
	public void processServer(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try{
			
			JSONArray objetos = new JSONArray();
			
			JSONObject objeto1 = new JSONObject();
			objeto1.put("nome", "andré");
			objeto1.put("sobreNome", "tricano");
			objetos.put(objeto1);
			
			JSONObject objeto2 = new JSONObject();
			objeto2.put("nome", "alan");
			objeto2.put("sobreNome", "tricano");
			objetos.put(objeto2);
			
			//out.print(objetos);
			//out.print("[[9233,14837,11442,8080,10302,5373,2450,9612,18656,8999],[7963,7845,8646,5130,2570,8936,17487,9141,6728,6046]]");
			//out.print("[{ name: 'name1',y: [32.6,16.6,1.5]}, { name: 'name2', y: [6.7,0.2,0.6]}]");
			
			out.print("{'aaData':[{'x':1300406400000,'y':3},{'x':1300410000000,'y':4},{'x':1300413600000,'y':5}]}");
			
			
			out.flush();
		} catch (JSONException e) {
		}
		
		
	}	

}
