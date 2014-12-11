/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.faces.context.FacesContext;

/**
 *
 * @author seb
 */
public class Pages {
  
  public static String getURI(Boolean sec) {
    String server_name =  FacesContext.getCurrentInstance().getExternalContext().getRequestServerName();
    Integer server_port = FacesContext.getCurrentInstance().getExternalContext().getRequestServerPort();
    String context = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
    String prot = (server_port == 443 || sec ) ? "https://" : "http://";
    return prot + server_name  + context;
  }

    public final static String NOT_FOUND = "404.xhtml";
    public final static String DEMO = "demo.xhtml";
    public final static String FAQ = "faq.xhtml";
    public final static String FICHE_FILM = "fiche_film.xhtml";
    public final static String FICHE_PRODUCT = "fiche_product.xhtml";
    public final static String FILMS = "films.xhtml";
    public final static String INDEX = "index.xhtml";
    public final static String LOGIN = "login.xhtml";
    public final static String MON_COMPTE = "moncompte.xhtml";
    public final static String PAYMENT = "paiement.xhtml";
    public final static String SIGNUP = "signup.xhtml";
    public final static String VISIONNEUSE = "visionneuse.xhtml";
    public final static String ACTIVATE = "activate.xhtml";
    public final static String FORGOT_PASS = "forgot_pass.xhtml";
    public final static String RETRIEVE_PASS = "retrieve_pass.xhtml";
    public final static String SITEMAP = "sitemap.xhtml";
    public final static String CONTACT = "contact.xhtml";

}
