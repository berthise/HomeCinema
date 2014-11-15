/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.Ejbs;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;

/**
 *
 * @author titou
 */
@ManagedBean
@RequestScoped
public class PaiementManagedBean {


  public Long idUser;

  public String numCarte;

  public String getNumCarte() {
    return numCarte;
  }

  public void setNumCarte(String numCarte) {
    this.numCarte = numCarte;
  }

  public Long getIdUser() {
    return idUser;
  }

  public void setIdUser(Long idUser) {
    this.idUser = idUser;
  }

  public PaiementManagedBean() throws NamingException {
  }

  public void buy(Long userId) {
    Long t_id = Ejbs.transaction().validate(userId);

    // TODO do that when we receive payment confirmation.
    Ejbs.transaction().validatePayement(t_id, 52L);

    
    try {
      FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès de l'achat !", null);
      FacesContext.getCurrentInstance().addMessage(null, message);
      FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
      FacesContext.getCurrentInstance().getExternalContext().redirect("moncompte.xhtml");
    } catch (IOException ex) {
      Logger.getLogger(PaiementManagedBean.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void returnCaddie() {
    try {
      FacesContext.getCurrentInstance().getExternalContext().redirect("moncompte.xhtml?box=caddie");
    } catch (IOException ex) {
      Logger.getLogger(PaiementManagedBean.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
