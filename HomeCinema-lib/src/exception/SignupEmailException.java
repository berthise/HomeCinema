/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

import javax.ejb.ApplicationException;

/**
 *
 * @author seb
 */
@ApplicationException(rollback=true)
public class SignupEmailException extends Exception {
  
}