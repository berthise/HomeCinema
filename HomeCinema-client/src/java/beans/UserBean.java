/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dtos.UserDto;
import ejbs.ManageUserRemote;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author toure
 */
@ManagedBean
@RequestScoped
public class UserBean {

    public void singUp(UserDto u) {
        System.out.println("Creating new user...");
        ManageUserRemote mur = null;
        try {
            InitialContext ic = new InitialContext();
            mur = (ManageUserRemote) ic.lookup("java:global/HomeCinema/HomeCinema-ejb/ManageUser!ejbs.ManageUserRemote");
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        mur.signUp(u);
        System.out.println("User created successfully !");
    }
}
