/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entities.Caddy;
import entities.Product;
import java.util.List;

/**
 *
 * @author titou
 */
public class UtilCaddie {

    public static Double totalprice(Caddy c) {
        Double sum = 0D;
        for (Product p : c.getProducts()) {
            sum += p.getPrice();
        }
        return sum;
    }

}
