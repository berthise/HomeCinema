/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dtos.CaddieDto;
import dtos.PaymentDto;
import enums.Lang;
import javax.ejb.Remote;

/**
 *
 * @author titou
 */
@Remote
public interface ManageTransactionRemote {

    //public CaddieDto getCaddieDto (Long id_user);
    
    public CaddieDto addProduct(Long user, Long id,Lang lang);
    
    public Long validate(Long user, PaymentDto pdto);
    
    public void validatePayement(Long id,Long btn);
    
     public CaddieDto removeProduct(Long user ,Long id, Lang lang);

   // public CaddieDto getCaddieDto(Long id_user, Lang lang);

    public CaddieDto getCaddieDto(Long id_user, Lang lang);
}
