/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dtos.FilmDto;
import dtos.GenreDto;
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
    
    public List<GenreDto> getAllGenres();
    
    /**
     *
     * @param actor film avec l'acteur ayant cet id 0||null => tout
     * @param director film réalisateur par le realisateur ayant cet id 0||null => tout
     * @param lgdto liste de genre produit renvoyé si au moins 1 film appatient a 1 genre empty||null => tout
     * @param str chaine de recherche (*str*) sur le nom du produit ""|null => tout 
     * @param year annee de sortie  ""||null =>tout
     * @param sort critere de tri
     * @param limit nb de row max 0||null => tout
     * @param row row de depart null=>0
     * @return film correspondant a tous les critéres précedents , empty si aucun 
     */
    public List<ProductDto> getFilteredProducts(Long actor, Long director, List<Long> lgdto, String str, String year, OrderTypes sort, Integer limit, Integer row,boolean main);
    
    
}
