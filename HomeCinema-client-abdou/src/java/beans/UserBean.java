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

    private static ManageUserRemote mur = null;

    public UserBean() {
        try {
            InitialContext ic = new InitialContext();
            mur = (ManageUserRemote) ic.lookup("java:global/HomeCinema/HomeCinema-ejb/ManageUser!ejbs.ManageUserRemote");
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
    }

    public void singUp(UserDto u) {
        System.out.println("Creating new user...");
        mur.signUp(u);
        System.out.println("User created successfully !");
    }

    public void login(String email, String password) {
        UserDto udto = mur.login(email, password);
        if (udto != null) {
            System.out.println("Welcome : " + udto.firstName);
        }
    }
}
