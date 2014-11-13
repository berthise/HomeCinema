/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dtos.CaddieDto;
import dtos.FilmFicheDto;
import dtos.ProductDto;
import ejbs.ManageFilmRemote;
import ejbs.ManageTransactionRemote;
import ejbs.ManageUserRemote;
import java.util.Arrays;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author titou
 */
@ManagedBean
@ViewScoped
public class CaddieManagedBean {

    @EJB
    private ManageTransactionRemote transactionManager;

    public CaddieDto cdto;

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
    
    public void setDtoFromId (Long id){
        this.cdto = transactionManager.getCaddieDto(10L);
    }

    public CaddieManagedBean() throws NamingException {
        this.cdto = new CaddieDto();
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

    private String printLineCaddie(Long id_product, Long id_film, String image, String titre, String date) {
        return "<tr class=\"tr-caddie-" + id_product + "\">"
                + "<td class=\"td-right\"><span class=\"affiche\"><img src=\"img/glass.png\" />"
                + "<span><img src=\"http://image.tmdb.org/t/p/w396/" + image + "\" /></span></span></td>"
                + "<td class=\"align-left\" colspan=\"3\"><a href=\"fiche_film.xhtml?id=" + id_film + "\" title=\"Voir la fiche du film\">" + titre + " (" + date + ")</a></td></tr>";
    }

    private String printLineCaddieFirst(Long id_product, int nb_films, String product_name, String product_price, int ind_product) {
        return "<tr class=\"tr-caddie-" + id_product + "\">"
                + "<td class=\"td-right\" rowspan=\"" + (nb_films + 1) + "\">" + ind_product + "</td><td class=\"td-invisible\"></td>"
                + "<td class=\"td-right title-film-caddie\"><a href=\"#\"><b>" + product_name + "</b></a></td><td class=\"td-right\">" + product_price + "€</td><td>"
                + "<a onclick=\"removeFromCaddie('" + id_product + "');return false;\" href=\"\"><img src=\"img/delete.png\" title=\"Retirer du caddie\" /></a></td></tr>";
    }

    private String printLineCaddieSolo(Long id_product, Long id_film, String product_name, String product_price, String image, String date, int ind_product) {
        return "<tr class=\"tr-solo tr-caddie-" + id_product + "\">"
                + "<td>" + ind_product + "</td>"
                + "<td><span class=\"affiche\"><img src=\"img/glass.png\" />"
                + "<span><img src=\"http://image.tmdb.org/t/p/w396/" + image + "\" /></span></span></td><td class=\"title-film-caddie\"><b><a href=\"fiche_film.xhtml?id=" + id_film + "\">" + product_name + " (" + date + ")</a></b></td><td>" + product_price + "€</td><td>"
                + "<a onclick=\"removeFromCaddie('" + id_product + "');return false;\" href=\"\"><img src=\"img/delete.png\" title=\"Retirer du caddie\" /></a></td></tr>";
    }

    public String printLinesCaddie() {
        String toReturn = "";

        toReturn += printLineCaddieFirst(1L, 2, "Saga Harry Potter", "24", 1);
        toReturn += printLineCaddie(1L, 14L, "3UBQGKS8c1dxRnDiu5kUK6ej3pP.jpg", "American Beauty", "1999");
        toReturn += printLineCaddie(1L, 14L, "3UBQGKS8c1dxRnDiu5kUK6ej3pP.jpg", "American Beauty", "1999");

        toReturn += printLineCaddieSolo(2L, 550L, "Fight Club", "9", "hpt3aa5i0TrSAnEdl3VJrRrje8C.jpg", "1999", 2);

        toReturn += printLineCaddieSolo(3L, 550L, "Fight Club", "9", "hpt3aa5i0TrSAnEdl3VJrRrje8C.jpg", "1999", 3);

        toReturn += printLineCaddieSolo(4L, 550L, "Fight Club", "9", "hpt3aa5i0TrSAnEdl3VJrRrje8C.jpg", "1999", 4);
        
        /*for (ProductDto pd : cdto.films){
            pd.
        }
        
        toReturn = "<p>"+cdto.films.get(0)get(0).name+"</p>";*/
        
        return toReturn;
    }

    public void deleteFromCaddie(Long id_user, Long id_film) {
        // Supprimer l'élément du caddie
    }
}
