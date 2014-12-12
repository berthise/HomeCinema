$(function () {
    $(".toggle-btn").on("click", function (e) {
        e.preventDefault();
        var $this = $(this);
        $(this).siblings(".toggle-row").slideUp("slow", function () {
            $this.siblings(".toggle-row").find(".alert").parent().hide();
            $this.next().slideDown("slow");
        });
    });

    var ancre = (location.hash);
    if ($('a[href="' + ancre + '"]').length > 0) {
        var courant = ancre;
    } else {
        var courant = '#films';
    }
    console.log(courant);

    if ($('.sidebar').find('a[href="' + courant + '"]').length > 0) {
        $('.sidebar').find('a[href="' + courant + '"]').trigger('click');
        $(courant).siblings().hide();
        $(courant).fadeIn("slow");
    } else {
        $("#films").siblings().hide();
        $("#films").fadeIn("slow");
    }
    $('.sidebar a').on('click', function (e) {
        console.log("ok");
        e.preventDefault();
        var $this = $(this);
        var link = $(this).attr('href');
        $('.sidebar').find('a').removeClass('active');
        $('.sidebar').find('a[href="' + link + '"]').addClass('active');
        history.pushState({key: 'value'}, document.title, link);
        $(link).siblings().hide();
        $(link).fadeIn("slow");
    });
});