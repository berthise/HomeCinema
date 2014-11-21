/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers.dtos;

import dtos.ProductDto;
import entities.Product;
import enums.OrderTypes;
import java.util.Comparator;
import javax.persistence.EntityManager;

/**
 *
 * @author titou
 */
public class ProductDtoManager {

    public static ProductDto getDto(Product p) {
	if (p == null) {
	    return null;
	}

	ProductDto pdto = new ProductDto();
	pdto.id = p.getId();
	pdto.addDate = p.getAddDate();
	pdto.name = p.getName();
	pdto.nbSales = p.getNbSales();
	pdto.price = p.getPrice();
	pdto.state = p.getState();

	return pdto;
    }

    public static Product makeProduct(ProductDto pdto) {
	Product p = new Product();
	p.setId(pdto.id);
	p.setName(pdto.name);
	p.setPrice(pdto.price);
	p.setState(pdto.state);

	return p;
    }

    public static Product mergeOrSave(ProductDto pdto, EntityManager em) {

	Product p = null;
	if (pdto.id != null) {
	    em.find(Product.class, pdto.id);
	}
	if (p == null) {
	    p = makeProduct(pdto);
	    em.persist(p);
	} else {
	    p.setName(pdto.name);
	    p.setPrice(pdto.price);
	    p.setState(pdto.state);
	    em.merge(p);
	}
	return p;
    }

    public static Comparator<ProductDto> getComparator(OrderTypes ot) {
	switch (ot) {
	    case SALES:
		new Comparator<ProductDto>() {
		    @Override
		    public int compare(ProductDto t, ProductDto t1) {
			return t.nbSales.compareTo(t1.nbSales);
		    }
		};
		break;
	    case NEW:
		new Comparator<ProductDto>() {
		    @Override
		    public int compare(ProductDto t, ProductDto t1) {
			return t.addDate.compareTo(t1.addDate);

		    }
		};
		break;
	    case ALPH:
		new Comparator<ProductDto>() {
		    @Override
		    public int compare(ProductDto t, ProductDto t1) {
			return t.name.compareTo(t1.name);
		    }
		};
		break;
	    default:
		throw new AssertionError(ot.name());

	}
	return null;
    }
}
