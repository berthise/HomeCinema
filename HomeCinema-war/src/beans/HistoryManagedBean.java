/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.Ejbs;
import dtos.TransactionDto;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import static utils.Beans.findBean;

/**
 *
 * @author titou
 */
@ManagedBean
@ViewScoped
public class HistoryManagedBean {
    
        LanguageManagedBean lang = findBean("languageManagedBean");
	
    public List<TransactionDto> ltdto;

    public HistoryManagedBean() {
	ltdto = null;
    }

    public Integer counter(Long idUser) {
	if (idUser == null) {
	    return null;
	}

	this.ltdto = Ejbs.user().getTransaction(idUser,lang.getLang());
	return ltdto.size();
    }

    public List<TransactionDto> getListTransaction() {
	return ltdto;
    }
    
    

}
