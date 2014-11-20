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
import javax.faces.bean.RequestScoped;

/**
 *
 * @author titou
 */
@ManagedBean
@RequestScoped
public class HistoryManagedBean {
    
    public List<TransactionDto> ltdto;

    public HistoryManagedBean() {
	ltdto = null;
    }

    public Integer counter(Long idUser) {
	if (idUser == null) {
	    return null;
	}

	this.ltdto = Ejbs.user().getTransaction(idUser);
	return ltdto.size();
    }
    
    public List<TransactionDto> getListTransaction() {
	return ltdto;
    }

}