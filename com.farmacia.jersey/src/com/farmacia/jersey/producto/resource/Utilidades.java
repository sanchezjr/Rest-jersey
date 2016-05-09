package com.farmacia.jersey.producto.resource;

import org.json.JSONException;
import org.json.JSONObject;

import com.farmacia.jersey.usuario.model.UsuarioDao;

public class Utilidades {
	
	/*contruir mensaje de login correcto*/
	public static String constructJSON(String tag, boolean status) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("tag", tag);
            obj.put("status", new Boolean(status));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
        }
        return obj.toString();
    }
 
	/*contruir mensaje de login incorrecto*/
    public static String constructJSON(String tag, boolean status,String err_msg) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("tag", tag);
            obj.put("status", new Boolean(status));
            obj.put("error_msg", err_msg);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
        }
        return obj.toString();
    }
    
    /*Comprueba si el user esta registrado*/
    static boolean checkCredenciales(String user, String pass){
        System.out.println("Inside checkCredentials");
        boolean result = false;
        UsuarioDao usuario = new UsuarioDao();
        if(isNotNull(user) && isNotNull(pass)){
            try {
                result = usuario.login(user, pass);
                //System.out.println("Inside checkCredentials try "+result);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                //System.out.println("Inside checkCredentials catch");
                result = false;
            }
        }else{
            //System.out.println("Inside checkCredentials else");
            result = false;
        }
        return result;
    }
    
    public static boolean isNotNull(String txt) {
        // System.out.println("Inside isNotNull");
        return txt != null && txt.trim().length() >= 0 ? true : false;
    }

}
