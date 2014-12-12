/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.Ejbs;
import dtos.CaddieDto;
import dtos.FilmDto;
import dtos.ProductDto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import static utils.Beans.findBean;
import utils.Pages;
import utils.Redirect;

/**
 *
 * @author titou
 */
@ManagedBean
@ViewScoped
public class AccountManagedBean {

    public CaddieDto cdto;
    public Long idUser;

    LanguageManagedBean lang = findBean("languageManagedBean");
    
    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
    
    public List<FilmDto> getListFilmsOwned (){
	return Ejbs.user().getFilms(idUser,lang.getLang());
    }

    private int isInMyFilms(List<Long> ids, Long iduser) {
	int toReturn = 0;
	for (Long i : ids) {
	    for (FilmDto l : Ejbs.user().getFilms(iduser,lang.getLang())) {
		if (l.id.equals(i)) {
		    toReturn++;
		}
	    }
	}
	return toReturn;
    }

    private int isInMyCaddie(List<Long> ids, Long iduser) {
	int toReturn = 0;
	for (Long i : ids) {
	    for (ProductDto l : Ejbs.transaction().getCaddieDto(iduser,lang.getLang()).films) {
		for (FilmDto f : Ejbs.product().getFilms(l.id,lang.getLang())) {
		    if (f.id.equals(i)) {
			toReturn++;
		    }
		}
	    }
	}
	return toReturn;
    }

    public String getCodeButtonsFilm(Long idfilm, Long iduser) {
        int caddie = isInMyCaddie(Arrays.asList(idfilm), iduser);
        int films = isInMyFilms(Arrays.asList(idfilm), iduser);

        if (caddie == 0 && films == 0) {
            return "FREE";
        } else if (caddie == 1) {
            return "CADDIE";
        } else {
            return "OWNED";
        }
    }

    public String getCodeButtonsProduct(Long idproduct, Long iduser) {
	List<Long> l = new ArrayList<>();
	for (FilmDto f : Ejbs.product().getFilms(idproduct,lang.getLang()))
	    l.add(f.id);
	
	int n = Ejbs.product().getFilms(idproduct,lang.getLang()).size();
	
	int caddie = isInMyCaddie(l, iduser);
	int films = isInMyFilms(l, iduser);

        if (caddie == 0 && films == 0) {
            return "FREE";
        } else if (caddie == n || caddie + films == n) {
            return "CADDIE";
        } else if (caddie == 0 && films < n) {
            return "PART_FILM";
        } else if (caddie < n && films == 0) {
            return "PART_CADDIE";
        } else if (caddie < n && films < n) {
            return "PART";
        } else {
            return "OWNED";
        }
    }

    public void checkIsMyFilm(Long idfilm, Long iduser) throws IOException {
        if (isInMyFilms(Arrays.asList(idfilm), iduser) == 0) {
            Redirect.redirectTo(Pages.MON_COMPTE);
        }
    }
}
