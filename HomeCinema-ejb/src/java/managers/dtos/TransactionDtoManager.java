/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.dtos;

import dtos.FilmDto;
import dtos.FilmFicheDto;
import dtos.TransactionDto;
import dtos.VideoDto;
import entities.Film;
import entities.Transaction;
import entities.Video;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author titou
 */
public class TransactionDtoManager {

    public static TransactionDto getDto(Transaction t) {
        if (t == null) {
            return null;
        }

        TransactionDto tdto = new TransactionDto();
        tdto.id=t.getId();
        tdto.addDate= t.getAddDate();
        tdto.bankTransNum=t.getBankTransNum();
        tdto.state=t.getState();
        tdto.totalPrice=t.getTotalPrice();
        tdto.user=t.getUser().getId();
        return tdto;
    }

/*
    public static Transaction makeTransaction(TransactionDto fdto) {
        Transaction t = new Transaction();
        
        return t;
    }

    public static Transaction mergeOrSave(TransactionDto fdto, EntityManager em) {

    }
*/
}
