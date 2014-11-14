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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author narcisse
 */
@ManagedBean
@ViewScoped
public class ListFilmBean {

    private ManageListFilmsRemote listFilmsmanager;
    private List<FilmDto> listFilmDto = new ArrayList<>();
   

 
   
    

public List<FilmDto> getAllFilms(){
       
   
    try {
            listFilmsmanager = (ManageListFilmsRemote) new InitialContext().lookup("java:global/HomeCinema/HomeCinema-ejb/ManageListFilms!ejbs.ManageListFilmsRemote");
        } catch (NamingException ex) {
            Logger.getLogger(ListFilmBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        listFilmDto.clear();
        listFilmDto= listFilmsmanager.findAllFilms();
          return listFilmDto;
       }

public List<FilmDto> getAllFilmsByActor(String actor){
       
   
    try {
            listFilmsmanager = (ManageListFilmsRemote) new InitialContext().lookup("java:global/HomeCinema/HomeCinema-ejb/ManageListFilms!ejbs.ManageListFilmsRemote");
        } catch (NamingException ex) {
            Logger.getLogger(ListFilmBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        listFilmDto.clear();
        listFilmDto= listFilmsmanager.FindFilmsByActor(actor);
          return listFilmDto;
       }
    
public List<FilmDto> getAllFilmsByTitle(String title){
   try {
            listFilmsmanager = (ManageListFilmsRemote) new InitialContext().lookup("java:global/HomeCinema/HomeCinema-ejb/ManageListFilms!ejbs.ManageListFilmsRemote");
        } catch (NamingException ex) {
            Logger.getLogger(ListFilmBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        listFilmDto.clear();
        listFilmDto= listFilmsmanager.FindFilmsByActor(title);
          return listFilmDto;
       }
    
}
