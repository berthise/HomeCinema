/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.util.List;

/**
 *
 * @author titou
 */
public class FilmFicheDto extends FilmDto {
    public VideoDto trailler;
    public List<VideoDto> files;
}
