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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;

/**
 *
 * @author titou
 */
@ManagedBean
@ViewScoped
public class AccountManagedBean {


    public CaddieDto cdto;

    public String initBox;

    public Long idUser;

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getInitBox() {
        return initBox;
    }

    public void setInitBox(String b) {
        String[] ref = {"films", "caddie", "historique", "infos"};
        List<String> list = Arrays.asList(ref);
        if (!list.contains(b) || b == null) {
            b = ref[0];
        }
        initBox = b;
    }

    public AccountManagedBean() throws NamingException {
        this.cdto = new CaddieDto();
        this.initBox = "films";
    }

    public String getHtmlForLink(String link, String title) {
        return "<p id=\"link-" + link + "\" " + ((link.equals(initBox)) ? "class=\"activated\"" : "") + "><a onclick=\"display('" + link + "'); return false;\" href=\"\">" + title + "</a></p>";
    }

    public String getClassDisplay(String link) {
        if (!link.equals(initBox)) {
            return "box-right display-none";
        } else {
            return "box-right";
        }
    }



    public List<List<String>> getListFilms() {
        List<List<String>> toReturn = new ArrayList<>();
        List<FilmDto> list = Ejbs.user().getFilms(idUser);

        if (list.isEmpty()) {
            List<String> toAdd = new ArrayList<>();
            toAdd.add("EMPTY");
            toReturn.add(toAdd);
            return toReturn;
        }

        SimpleDateFormat formater = new SimpleDateFormat("yyyy");

        for (FilmDto f : Ejbs.user().getFilms(idUser)) {
            List<String> toAdd = new ArrayList<>();
            toAdd.add("SIMPLE");
            toAdd.add(f.cover);
            toAdd.add(f.title);
            toAdd.add(formater.format(f.release_date));
            toAdd.add(f.id.toString());
            toReturn.add(toAdd);
        }

        return toReturn;
    }

    public boolean isOneOfMyFilm(Long idfilm, Long iduser) {
        if (iduser == null) {
            return false;
        }
        for (FilmDto l : Ejbs.user().getFilms(iduser)) {
            if (l.id.equals(idfilm)) {
                return true;
            }
        }
        return false;
    }

    public boolean isInMyCaddie(Long idfilm, Long iduser) {
        if (iduser == null) {
            return false;
        }
	cdto = Ejbs.transaction().getCaddieDto(iduser);
        for (ProductDto l : cdto.films) {
            for (FilmDto f : Ejbs.product().getFilms(l.id)) {
                if (f.id.equals(idfilm)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isFree(Long idfilm, Long iduser) {
        if (iduser == null) {
            return false;
        }
        return !isInMyCaddie(idfilm, iduser) && !isOneOfMyFilm(idfilm, iduser);
    }

    
    public void checkIsMyFilm (Long idfilm, Long iduser) throws IOException {
        if (!isOneOfMyFilm(idfilm, iduser))
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }
}
