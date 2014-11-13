/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dtos.CaddieDto;
import dtos.FilmDto;
import dtos.ProductDto;
import dtos.UserDto;
import ejbs.ManageProductRemote;
import ejbs.ManageTransactionRemote;
import ejbs.ManageUserRemote;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.naming.NamingException;

/**
 *
 * @author titou
 */
@ManagedBean
@ViewScoped
public class AccountManagedBean {

    @EJB
    private ManageTransactionRemote transactionManager;

    @EJB
    private ManageProductRemote productManager;

    @EJB
    private ManageUserRemote userManager;

    public CaddieDto cdto;

    public String initBox = "films";
    
    private Long iduser;

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

    public void setDtoFromId(Long id) {
        this.iduser = id;
        this.cdto = transactionManager.getCaddieDto(id);
    }    

    public AccountManagedBean() throws NamingException {
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

    private String printLineCaddieFirst(Long id_product, int nb_films, String product_name, Double product_price, int ind_product) {
        return "<tr class=\"tr-caddie-" + id_product + "\">"
                + "<td class=\"td-right\" rowspan=\"" + (nb_films + 1) + "\">" + ind_product + "</td><td class=\"td-invisible\"></td>"
                + "<td class=\"td-right title-film-caddie\"><a href=\"#\"><b>" + product_name + "</b></a></td><td class=\"td-right\">" + product_price + "€</td><td>"
                + "<a onclick=\"removeFromCaddie('" + id_product + "');return false;\" href=\"\"><img src=\"img/delete.png\" title=\"Retirer du caddie\" /></a></td></tr>";
    }

    private String printLineCaddieSolo(Long id_product, Long id_film, String product_name, Double product_price, String image, String date, int ind_product) {
        return "<tr class=\"tr-solo tr-caddie-" + id_product + "\">"
                + "<td>" + ind_product + "</td>"
                + "<td><span class=\"affiche\"><img src=\"img/glass.png\" />"
                + "<span><img src=\"http://image.tmdb.org/t/p/w396/" + image + "\" /></span></span></td><td class=\"title-film-caddie\"><b><a href=\"fiche_film.xhtml?id=" + id_film + "\">" + product_name + " (" + date + ")</a></b></td><td>" + product_price + "€</td><td>"
                + "<a onclick=\"removeFromCaddie('" + id_product + "');return false;\" href=\"\"><img src=\"img/delete.png\" title=\"Retirer du caddie\" /></a></td></tr>";
    }

    public String printLinesCaddie() {
        if (cdto.films.isEmpty()) {
            return "<tr class=\"empty\"><td colspan=\"6\">Votre caddie est vide ... <a href=\"#\">Trouvez une film dans notre liste !</a></td></tr>";
        }

        String toReturn = "";
        int i = 1;
        SimpleDateFormat formater = new SimpleDateFormat("yyyy");
        for (ProductDto pd : cdto.films) {
            List<FilmDto> list_films = productManager.getFilms(pd.id);
            if (list_films.size() == 1) {
                FilmDto f = list_films.get(0);
                toReturn += printLineCaddieSolo(pd.id, f.id, f.title, pd.price, f.cover, formater.format(f.release_date), i);
            } else {
                toReturn += printLineCaddieFirst(pd.id, list_films.size(), pd.name, pd.price, i);
                for (FilmDto f : list_films) {
                    toReturn += printLineCaddie(pd.id, f.id, f.cover, f.title, formater.format(f.release_date));
                }
            }
        }

        return toReturn;
    }

    public void deleteFromCaddie(Long id_user, Long id_film) {
        // Supprimer l'élément du caddie
    }

    public String printLinesMyFilms() {
        String toReturn = "";
        SimpleDateFormat formater = new SimpleDateFormat("yyyy");

        for (FilmDto f : userManager.getFilms(iduser)) {
            toReturn += printLineFilm(f.cover, f.title, formater.format(f.release_date), f.id);
        }

        return toReturn;
    }

    private String printLineFilm(String image, String titre, String date, Long id) {
        return "<tr class=\"tr-hover\"><td><span class=\"affiche\"><img src=\"img/glass.png\" />"
                + "<span><img src=\"http://image.tmdb.org/t/p/w396/" + image + "\" /></span></span></td>"
                + "<td><a href=\"fiche_film.xhtml?id=" + id + "\" title=\"Voir la fiche du film\">" + titre + " (" + date + ")</a></td>"
                + "<td><a href=\"visionneuse.xhtml?id=" + id + "\"><img src=\"img/eye.png\" title=\"Voir le film\" /></a></td></tr>";
    }
}
