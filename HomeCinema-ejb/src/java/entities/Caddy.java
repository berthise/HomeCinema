/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author titou
 */
@Entity
public class Caddy implements Serializable {


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

      @ManyToMany
      @JoinColumn(name = "CADDY")
  private Set<Product> products;
    
      public Caddy ()
      {
          this.products = new HashSet<Product>();
      }
      
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

        public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> caddy) {
        this.products = caddy;
    }
    
    public void addCaddy (Product p)
    {
        this.products.add(p);
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Caddy)) {
            return false;
        }
        Caddy other = (Caddy) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Caddy[ id=" + id + " ]";
    }
    
}
