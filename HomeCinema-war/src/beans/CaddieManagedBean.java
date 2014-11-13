/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dtos.CaddieDto;
import dtos.ProductDto;
import ejbs.ManageFilmRemote;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.naming.NamingException;

/**
 *
 * @author titou
 */
@ManagedBean
@ViewScoped
public class CaddieManagedBean {

    private ManageFilmRemote filmManager;

    public CaddieDto fdto;

    public String initBox = "films";

    public String getInitBox() {
        return initBox;
    }

    public void setInitBox(String b) {
        String[] ref = {"films", "caddie", "historique", "infos"};
        List<String> list = Arrays.asList(ref);
        if (!list.contains(b)) {
            b = ref[0];
        }
        initBox = b;
    }

    public CaddieManagedBean() throws NamingException {
        //filmManager = (ManageFilmRemote) new InitialContext().lookup("java:global/HomeCinema/HomeCinema-ejb/ManageTransaction!ejbs.ManageTransactionRemote");
        //this.fdto = new CaddieDto();
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

    private String printLineCaddie(String image, String titre, String date, String prix, String id) {       
        return "<tr class=\"tr-hover\" id=\"tr-caddie-" + id + "\"><td><span class=\"affiche\"><img src=\"img/glass.png\" />"
                + "<span><img src=\"http://image.tmdb.org/t/p/w396/" + image + "\" /></span></span></td>"
                + "<td><a href=\"fiche_film.xhtml?id=" + id + "\" title=\"Voir la fiche du film\">" + titre + " (" + date + ")</a></td>"
                + "<td>" + prix + "€</td><td><a onclick=\"removeFromCaddie('"+id+"');return false;\" href=\"\"><img src=\"img/delete.png\" title=\"Retirer du caddie\" /></a></td></tr>";
    }

    public String printLinesCaddie() {
        String toReturn = "";
        for (int i = 0; i < 10; i++) {
            toReturn += printLineCaddie("3UBQGKS8c1dxRnDiu5kUK6ej3pP.jpg", "American Beauty", "1999", "8", "14");
        }
        toReturn += printLineCaddie("hpt3aa5i0TrSAnEdl3VJrRrje8C.jpg", "Fight Club", "1999", "9", "550");
        return toReturn;
    }
}
