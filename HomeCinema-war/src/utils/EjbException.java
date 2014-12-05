/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.ejb.EJBException;

/**
 *
 * @author seb
 */
public class EjbException {

  public static Exception getRootCause(EJBException exception) {
    if (null == exception) {
      return null;
    }

    EJBException effect = exception;
    Exception cause = effect.getCausedByException();

    while (null != cause && cause instanceof EJBException) {
      effect = (EJBException) cause;
      cause = effect.getCausedByException();
    }

    return null == cause ? effect : cause;
  }
}
