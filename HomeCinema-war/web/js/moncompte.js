$(function () {
    $('.sidebar a').on('click', function (e) {
        e.preventDefault();
        var href = $(this).attr("href");
        console.log(href);
        var $link = $('.sidebar').find('a[href="' + href + '"]');
        $('.sidebar').find('a').removeClass('active');
        $link.addClass("active");
        history.pushState({key: 'value'}, document.title, href);
        $(href).siblings().hide();
        $(href).fadeIn("slow");
        $(window).scrollTop(0);
    });

    window.onpopstate = function (e) {
        if (e.state !== null) {
            var link = (location.hash);
            $('.sidebar').find('a[href="' + link + '"]').trigger("click");
        }
    };

    var ancre = (location.hash);
    if ($('a[href="' + ancre + '"]').length > 0) {
        var courant = ancre;
    } else {
        var courant = '#films';
    }

    if ($('.sidebar').find('a[href="' + courant + '"]').length > 0) {
        $('.sidebar').find('a[href="' + courant + '"]').click();
    } else {
        $('.sidebar').find('a[href="#films"]').trigger("click");
    }
    window.onload = function () {
        $(window).scrollTop(0);
        console.log("Scroll");
    };
});