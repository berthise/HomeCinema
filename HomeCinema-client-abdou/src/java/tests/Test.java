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
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("0: Quit");
            System.out.println("1: SignUp");
            System.out.println("2: Login");
            System.out.print("enter your choice : ");
            switch (sc.nextLine()) {
                case "0":
                    System.out.println("\nBye");
                    System.exit(0);
                case "1": {
                    System.out.println("Fill your informations");
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
                    break;
                }
                case "2": {
                    System.out.println("Fill your informations");
                    System.out.println("Enter your email : ");
                    String email = sc.nextLine();
                    System.out.println("Enter your password : ");
                    String password = sc.nextLine();
                    UserBean bean = new UserBean();
                    bean.login(email, password);
                    break;
                }
            }
        }
    }
}
