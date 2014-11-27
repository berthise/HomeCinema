/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.HashMap;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author seb
 */
public class Beans {

  // TODO
  private final static String prefix = "/HomeCinema-war/";

  @SuppressWarnings("unchecked")
  public static <T> T findBean(String beanName) {
    FacesContext context = FacesContext.getCurrentInstance();
    return (T) context.getApplication().
	    evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
  }

  public static String getRequestPage() {
    Object request = FacesContext.getCurrentInstance().getExternalContext().getRequest();
    if (request instanceof HttpServletRequest) {
      return ((HttpServletRequest) request).getRequestURI().replace(prefix, "");
    } else {
      return "";
    }
  }
}
