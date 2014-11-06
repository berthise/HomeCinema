/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.entities;

import entities.Film;
import entities.Product;

/**
 *
 * @author titou
 */
public class ManageEntitieProduct {
    public static void linkProductFilm(Film f, Product p)
    {
        f.addProduct(p);
        p.addFilm(f);
    }
}
