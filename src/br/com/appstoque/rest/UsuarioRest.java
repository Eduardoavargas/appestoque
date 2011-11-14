package br.com.appstoque.rest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.repackaged.org.json.JSONArray;
import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;

@SuppressWarnings("serial")
public class UsuarioRest extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		JSONArray arrayObj = new JSONArray();
		try{
			JSONObject jobj1 = new JSONObject();
			jobj1.put("nome", "Andre");
			jobj1.put("sobrenome", "Tricano");
			jobj1.put("idade", "35");
			jobj1.put("tel", "92227052");
			JSONObject jobj2 = new JSONObject();
			jobj2.put("nome", "Alan");
			jobj2.put("sobrenome", "Tricano");
			jobj2.put("idade", "29");
			jobj2.put("tel", "92227052");
			JSONObject jobj3 = new JSONObject();
			jobj3.put("nome", "Elediane Tonta");
			jobj3.put("sobrenome", "Tricano");
			jobj3.put("idade", "29");
			jobj3.put("tel", "92227052");
			arrayObj.put(jobj1);
			arrayObj.put(jobj2);
			arrayObj.put(jobj3);
		}catch(JSONException e) {
			e.printStackTrace();
		}
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		out.print(arrayObj);
		out.flush();
	}
	
}
