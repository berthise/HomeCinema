/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import dtos.FilmDto;
import dtos.ProductDto;
import dtos.SimpleUserDto;
import dtos.UserDto;
import dtos.VideoDto;
import enums.Lang;
import exception.SignupEmailException;
import exception.SignupNickNameException;
import exception.UncorrectPasswordException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import utils.Securite;

/**
 *
 * @author titou
 */
public class add_fr {

    public static void main(String[] args) throws SignupEmailException, SignupNickNameException, JSONException, IOException, ParseException, InterruptedException {

	    Admin a = new Admin();
	    List<FilmDto> lfdto = a.getManageFilmRemote().getAllFilm(Lang.EN);
	    int i=0;
	    for ( FilmDto fdto : lfdto)
	    {

		a.getManageFilmRemote().mergeOrSave(a.createFilmFr(fdto.id),Lang.FR);
		Thread.sleep(500);
		System.out.println(i++);
	    }
    }
}
