package tests;

import beans.UserBean;
import dtos.UserDto;
import enums.UserStates;
import java.util.Date;
import java.util.Scanner;
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
        Scanner sc = new Scanner(System.in);
        UserDto u = new UserDto();
        u.id = null;
        System.out.println("Enter your email : ");
        u.email = sc.nextLine();
        System.out.println("Enter your login : ");
        u.nickName = sc.nextLine();
        System.out.println("Enter your firstname : ");
        u.firstName = sc.nextLine();
        System.out.println("Enter your lastname : ");
        u.lastName = sc.nextLine();
        System.out.println("Enter your password : ");
        u.password = sc.nextLine();
        u.birthDate = new Date();
        u.addDate = new Date();
        u.state = UserStates.Unactived;
        UserBean bean = new UserBean();
        bean.singUp(u);
    }
}
