/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;

/**
 *
 * @author titou
 */
public class UserDto implements Serializable {
    public Long id;
    public String firstname;
    public String lastname;
    public String email;
}