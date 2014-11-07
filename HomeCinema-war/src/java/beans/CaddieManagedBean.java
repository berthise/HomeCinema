/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dtos.CaddieDto;
import ejbs.ManageFilmRemote;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author titou
 */
@ManagedBean
@ViewScoped
public class CaddieManagedBean {

    private ManageFilmRemote filmManager;

    public CaddieDto fdto;

    public CaddieManagedBean() throws NamingException {
        filmManager = (ManageFilmRemote) new InitialContext().lookup("java:global/HomeCinema/HomeCinema-ejb/ManageTransaction!ejbs.ManageTransactionRemote");
        this.fdto = new CaddieDto();
    }
    
}
