/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author titou
 */
public class DuplicateKey extends Exception {

    public String entitie;
    public Long id;

    public DuplicateKey(String entitie, Long id) {
        this.entitie = entitie;
        this.id = id;
    }

    @Override
    public String toString() {
        return "a " + entitie + " with id=" + id + "already exist, insertion is impossible";
    }
}
