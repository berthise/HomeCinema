/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dtos.PaymentDto;
import ejbs.Ejbs;
import enums.CardTypes;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import javax.naming.NamingException;
import static utils.Beans.findBean;
import utils.Lang;
import utils.Message;
import utils.Pages;
import utils.Redirect;

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
    }

    public void setCardType() {
	pdto.setType(CardTypes.CB);
    }

    public void buy() {
	SessionManagedBean session = findBean("sessionManagedBean");
	if (session.checkPaymentCanceled()) {
	    return;
	}

	// TODO
	Long t_id = Ejbs.transaction().validate(session.getId(), pdto);

	// TODO do that when we receive payment confirmation.
	Ejbs.transaction().validatePayement(t_id, 52L);

	Message.Info(Lang.getString("payement-bean-info"));
	
	session.closePaiement();

    }

    public void cancel() {
	SessionManagedBean session = findBean("sessionManagedBean");

	session.cancelPaiement();
	Redirect.redirectTo(Pages.MON_COMPTE + "#caddie");
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
