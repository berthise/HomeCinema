/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author titou
 */
public class FilmDto implements Serializable {
    public Long id;
    public String title;
    public String overview;
    public String cover;
    public Date release_date;
    public String country;
    public Integer runtime;
    public Integer rating;
}
