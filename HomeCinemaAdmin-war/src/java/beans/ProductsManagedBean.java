/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;



import dtos.ProductDto;
import ejbs.ManageProductRemote;
import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author titou
 */
@ManagedBean
@RequestScoped
public class ProductsManagedBean {

  private ManageProductRemote productManager;

  private List<ProductDto> products;

  public ProductsManagedBean() throws NamingException {
    productManager = (ManageProductRemote) new InitialContext().lookup("java:global/HomeCinema/HomeCinema-ejb/ManageProduct!ejbs.ManageProductRemote");
    this.products = productManager.getAllProduct();
  }

  public List<ProductDto> getProducts() {
    return this.products;
  }

  public void setProducts(List<ProductDto> array) {
    this.products = array;
  }

  public Integer getTotal() {
    return this.products.size();
  }

  public void delProduct(Long id) {
    // productManager.removeProduct(id);
    FacesMessage message = new FacesMessage("Succès de la suppresion ! TODO: decommenter instruction");
    FacesContext.getCurrentInstance().addMessage(null, message);
  }

}
