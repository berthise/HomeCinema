/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entities.Product;
import java.util.List;

/**
 *
 * @author titou
 */
public class UtilCaddie {

    public static Double totalprice(List<Product> lp) {
        Double sum = 0D;
        for (Product p : lp) {
            sum += p.getPrice();
        }
        return sum;
    }

}
