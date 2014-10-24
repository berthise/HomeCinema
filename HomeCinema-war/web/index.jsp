<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> 
<html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title></title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="css/main.css">

        <script src="js/vendor/modernizr-2.6.2-respond-1.1.0.min.js"></script>
    </head>
    <body>
        <c:import url="components/header.jsp" />
        <section>
            <div class="container">
                <div class="row">
                    <div class="col-md-7">
                        <div class="well">
                            <h2 class="well-title">Texte de bienvenue</h2>
                            <p>
                                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do 
                                eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut 
                                enim ad minim veniam, quis nostrud exercitation ullamco laboris 
                                nisi. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do 
                                eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut 
                                enim ad minim veniam, quis nostrud exercitation ullamco laboris 
                                nisi ...
                            </p>
                            <a href="#">FAQ</a><br />
                            <a href="#">Demo</a>
                            <hr />
                            <center>
                                <a href="signup.jsp" class="btn btn-primary btn-lg">Inscription</a>
                                <a href="customers/" class="btn btn-success btn-lg">Connexion</a>
                            </center>
                        </div>
                    </div>
                    <div class="col-md-5">
                        <div class="well">
                            <h2 class="well-title">Demo</h2>
                            <object type="application/x-shockwave-flash" width="100%" height="212px" data="http://www.youtube.com/v/_etwz7NkemE&amp;hl=fr">
                                <param name="movie" value="http://www.youtube.com/v/_etwz7NkemE&amp;hl=fr" />
                                <param name="wmode" value="transparent" />
                            </object>
                        </div>
                    </div>
                </div>

                <h1>Exclus</h1>
                <hr />
                <div class="row exclus">
                    <div class="col-md-2 col-sm-6 col-xs-12 film">
                        <a href="affiche.jsp">
                            <img class="cover" src="http://image.tmdb.org/t/p/w300//3UBQGKS8c1dxRnDiu5kUK6ej3pP.jpg?api_key=63d250a5b71c307f7592228c79b729cf" />
                            <span class="film_title">Titre 1</span>
                        </a>
                    </div>
                    <div class="col-md-2 col-sm-6 col-xs-12 film">
                        <img class="cover" src="http://image.tmdb.org/t/p/w300//2lECpi35Hnbpa4y46JX0aY3AWTy.jpg?api_key=63d250a5b71c307f7592228c79b729cf" />
                        <span class="film_title">Titre 1</span>
                    </div>
                    <div class="col-md-2 col-sm-6 col-xs-12 film">
                        <img class="cover" src="http://image.tmdb.org/t/p/w300//cVJaWyM9Pn8MSJO8MCnwkuJnGlj.jpg?api_key=63d250a5b71c307f7592228c79b729cf" />
                        <span class="film_title">Titre 1</span>
                    </div>
                    <div class="col-md-2 col-sm-6 col-xs-12 film">
                        <img class="cover" src="http://image.tmdb.org/t/p/w300//fQMSaP88cf1nz4qwuNEEFtazuDM.jpg?api_key=63d250a5b71c307f7592228c79b729cf" />
                        <span class="film_title">Titre 1</span>
                    </div>
                    <div class="col-md-2 col-sm-6 col-xs-12 film">
                        <img class="cover" src="http://image.tmdb.org/t/p/w300//3UBQGKS8c1dxRnDiu5kUK6ej3pP.jpg?api_key=63d250a5b71c307f7592228c79b729cf" />
                        <span class="film_title">Titre 1</span>
                    </div>
                    <div class="col-md-2 col-sm-6 col-xs-12 film">
                        <img class="cover" src="http://image.tmdb.org/t/p/w300//2lECpi35Hnbpa4y46JX0aY3AWTy.jpg?api_key=63d250a5b71c307f7592228c79b729cf" />
                        <span class="film_title">Titre 1</span>
                    </div>
                </div>
            </div>
        </section>
        <c:import url="components/footer.jsp" />
    </body>
</html>
