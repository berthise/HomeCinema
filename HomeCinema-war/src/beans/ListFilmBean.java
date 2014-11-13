/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dtos.FilmDto;
import ejbs.ManageFilmRemote;
import ejbs.ManageListFilmsRemote;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author narcisse
 */
@ManagedBean
@SessionScoped
public class ListFilmBean {

    private ManageListFilmsRemote listFilmsmanager;
    private List<FilmDto> listFilmDto = new ArrayList<>();
   

    public List<FilmDto> findAllFilms() {

        try {

            listFilmsmanager = (ManageListFilmsRemote) new InitialContext().lookup("java:global/HomeCinema/HomeCinema-ejb/ManageListFilms!ejbs.ManageListFilmsRemote");
            
    }
    catch (Exception e ) {
           }
        return listFilmsmanager.findAllFilms();
        
    
    } 
   
    

public List<FilmDto> getAllFilms(){
          listFilmDto= findAllFilms();
          return listFilmDto;
       }
    
}
