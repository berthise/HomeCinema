/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dtos.CaddieDto;
import dtos.FilmDto;
import dtos.ProductDto;
import dtos.VideoDto;
import enums.OrderTypes;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author titou
 */
@Remote
public interface ManageProductRemote {

    public Long createProduct(ProductDto pdto);
    
    public Long createProductWithFilm(FilmDto fdto, VideoDto trailer, VideoDto vid, Double price);

    public void addFilms(Long pid, List<FilmDto> lfdto);

    public void addFilm(Long pid, FilmDto fdto,boolean main);

    public void addExistingFilms(Long pid, List<Long> lfid);

    public void addExistingFilm(Long pid, Long fid,boolean main);
    
    public List<ProductDto> getAllProduct();
    
    public List<FilmDto> getFilms(Long pid);
    
    public ProductDto getProduct(Long pid);

    public ProductDto mergeOrSave(ProductDto pdto);

    public List<ProductDto> findProducts(Long actor, Long director, List<Long> lgdto, String str, String year);

    public List<ProductDto> getFilteredProducts(Long actor, Long director, List<Long> lgdto, String str, String year, OrderTypes sort, Integer limit, Integer row);
    
    
}
