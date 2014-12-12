/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author http://stackoverflow.com/questions/20934016/how-to-add-cookie-in-jsf
 */
public class Cookies {

  public static void setCookie(String name, String value, int expiry) { // expire in second

    FacesContext facesContext = FacesContext.getCurrentInstance();

    HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
    Cookie cookie = null;

    Cookie[] userCookies = request.getCookies();
    if (userCookies != null && userCookies.length > 0) {
      for (int i = 0; i < userCookies.length; i++) {
	if (userCookies[i].getName().equals(name)) {
	  cookie = userCookies[i];
	  break;
	}
      }
    }

    if (cookie != null) {
      cookie.setValue(value);
    } else {
      cookie = new Cookie(name, value);
      cookie.setPath(request.getContextPath());
    }

    cookie.setMaxAge(expiry);

    HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
    response.addCookie(cookie);
  }

  public static Cookie getCookie(String name) {

    FacesContext facesContext = FacesContext.getCurrentInstance();

    HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
    Cookie cookie = null;

    Cookie[] userCookies = request.getCookies();
    if (userCookies != null && userCookies.length > 0) {
      for (int i = 0; i < userCookies.length; i++) {
	if (userCookies[i].getName().equals(name)) {
	  cookie = userCookies[i];
	  return cookie;
	}
      }
    }
    return null;
  }
}
