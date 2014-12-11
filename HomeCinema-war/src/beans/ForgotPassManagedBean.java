/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import static utils.Beans.findBean;
import utils.Pages;

/**
 *
 * @author toure
 */
@ManagedBean
@RequestScoped
public class ForgotPassManagedBean {

  private String email = "";
  private String code = "";
  private String p1 = "";
  private String p2 = "";
  
  public static String getUrl(String code) {
    return "http://"+ Pages.DOMAIN + Pages.ROOT + Pages.RETRIEVE_PASS + "?c=" + code;
  }

  public void retrieve() {
    SessionManagedBean session = findBean("sessionManagedBean");

    session.retrievePass(this);
  }
  
  public void change() {
    SessionManagedBean session = findBean("sessionManagedBean");

    session.changePassRetrieve(this);
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getP1() {
    return p1;
  }

  public void setP1(String p1) {
    this.p1 = p1;
  }

  public String getP2() {
    return p2;
  }

  public void setP2(String p2) {
    this.p2 = p2;
  }
  
  

}
