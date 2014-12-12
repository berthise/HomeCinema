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

    if ($('.sidebar').find('a[href="' + courant + '"]').length > 0) {
        clickLink(courant);
    } else {
        clickLink("#films");
    }
    $('.sidebar').find('a[href="' + courant + '"]').trigger('click');
    $('.sidebar a').on('click', function (e) {
        e.preventDefault();
        clickLink($(this).attr('href'));
    });
    window.onpopstate = function(e) {
        if (e.state !== null) {
            var link = (location.hash);
            clickLink(link);
        }
    };
    function clickLink(id) {
        var $link = $('.sidebar').find('a[href="' + id + '"]');
        $('.sidebar').find('a').removeClass('active');
        $link.addClass("active");
        history.pushState({key: 'value'}, document.title, id);
        $(id).siblings().hide();
        $(id).fadeIn("slow");
    } 
});