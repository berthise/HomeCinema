/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var api_key = "63d250a5b71c307f7592228c79b729cf";

function load_tmdb()
{

    var id = window.prompt("id du film","");
        var xhr = new XMLHttpRequest();
    xhr.onload = function() {
        var tmdbJSON = JSON.parse(xhr.responseText);
        res = tmdbJSON;
        document.getElementById("formFilm:id").value=res.id;
      document.getElementById("formFilm:title").value=res.title;
      document.getElementById("formFilm:releaseDate").value=res.release_date;
      document.getElementById("formFilm:rating").value=res.vote_average;
      document.getElementById("formFilm:overview").value=res.overview;
      document.getElementById("formFilm:runtime").value=res.runtime;
      document.getElementById("formFilm:cover").value=res.poster_path;
    };
    xhr.open("get", "https://api.themoviedb.org/3/movie/" + id + "?api_key=" + api_key + "", true);
    xhr.send();
}

