/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ResourceBundle;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;

/**
 *
 * @author pierre
 */
public class Lang {
    
    private final ResourceBundle backendText;
    
    public ResourceBundle getBackendText(){
	return backendText;
    }
    
    public static String getString(String id){
	return getInstance().getBackendText().getString(id);
    }
    
    public static void reset(){
	NewSingletonHolder.INSTANCE = new Lang();
    }
    
    private Lang() {
	FacesContext context = FacesContext.getCurrentInstance();
	Application app = context.getApplication();
	backendText = app.getResourceBundle(context, "msg");
    }
    
    private static Lang getInstance() {
	return NewSingletonHolder.INSTANCE;
    }
    
    private static class NewSingletonHolder {
	private static Lang INSTANCE = new Lang();
    }
}
