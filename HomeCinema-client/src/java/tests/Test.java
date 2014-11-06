package tests;

import dtos.UserDto;
import ejbs.ManageUserRemote;
import java.util.Date;
import javax.naming.InitialContext;
import javax.naming.NamingException;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author toure
 */
public class Test {

    public static void main(String[] args) {
        System.out.println("Filling new user informations");
        UserDto u = new UserDto();
        u.id = null;
        u.email = "email@email.com";
        u.nickName = "nickname";
        u.firstName = "firstname";
        u.lastName = "lastname";
        u.password = "password";
        u.birthDate = new Date();
        System.out.println("Creating new user...");
        ManageUserRemote mur = null;
        try {
            InitialContext ic = new InitialContext();
            mur = (ManageUserRemote) ic.lookup("java:global/HomeCinema/HomeCinema-ejb/ManageUser!ejbs.ManageUserRemote");
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        mur.signUp(u);
        System.out.println("New user created !");
    }
}
