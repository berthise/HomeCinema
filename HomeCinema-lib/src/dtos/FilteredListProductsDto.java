/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author seb
 */
public class FilteredListProductsDto implements Serializable {

    public int size;
    public List<ProductDto> list;

    public FilteredListProductsDto (List<ProductDto> l, int s){
	this.size = s;
	this.list = l;
    }
    
    public int getSize() {
	return size;
    }

    public void setSize(int size) {
	this.size = size;
    }

    public List<ProductDto> getList() {
	return list;
    }

    public void setList(List<ProductDto> list) {
	this.list = list;
    }
    
}
