/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dtos.PaymentDto;
import ejbs.Ejbs;
import enums.CardTypes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.naming.NamingException;

/**
 *
 * @author titou
 */
@ManagedBean
@RequestScoped
public class PaymentManagedBean {

  private PaymentDto pdto;
  private final List<SelectItem> months = new ArrayList<>();
  private final List<SelectItem> years = new ArrayList<>();

  public PaymentManagedBean() throws NamingException {
    months.add(new SelectItem("", ""));
    for (int i = 1; i <= 9; i++) {
      months.add(new SelectItem(i, "0" + i));
    }
    for (int i = 10; i <= 12; i++) {
      months.add(new SelectItem(i, "" + i));
    }
    years.add(new SelectItem("", ""));
    for (int i = Calendar.getInstance().get(Calendar.YEAR); i <= Calendar.getInstance().get(Calendar.YEAR) + 6; i++) {
      years.add(new SelectItem(i, "" + i));
    }
    pdto = new PaymentDto();
    //   pdto.setType(CardTypes.CB);
//    pdto.setMonth(Calendar.getInstance().get(Calendar.MONTH)+1);
//    pdto.setYear(Calendar.getInstance().get(Calendar.YEAR));
  }

  public void setCardType() {
    pdto.setType(CardTypes.CB);
  }

  public void buy(Long userId) {

    Long t_id = Ejbs.transaction().validate(userId, pdto);

    // TODO do that when we receive payment confirmation.
    Ejbs.transaction().validatePayement(t_id, 52L);

    try {
      FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès de l'achat !", null);
      FacesContext.getCurrentInstance().addMessage(null, message);
      FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
      FacesContext.getCurrentInstance().getExternalContext().redirect("moncompte.xhtml");
    } catch (IOException ex) {
      Logger.getLogger(PaymentManagedBean.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

  public void returnCaddie() {
    try {
      FacesContext.getCurrentInstance().getExternalContext().redirect("moncompte.xhtml?box=caddie");
    } catch (IOException ex) {
      Logger.getLogger(PaymentManagedBean.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public PaymentDto getPdto() {
    return pdto;
  }

  public void setPdto(PaymentDto pdto) {
    this.pdto = pdto;
  }

  public CardTypes[] getCardTypes() {
    return CardTypes.values();
  }

  public List<SelectItem> getYears() {
    return years;
  }

  public List<SelectItem> getMonths() {
    return months;
  }
}
