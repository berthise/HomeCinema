$(function () {
    $("#login").click(function () {
        if (window.location.protocol != "https:")
	  $("#warning-http").show();
        console.log($("#form-login").length);
        $("#form-login").slideToggle();
    });
    
    $("#warning-http-lien").on("click", function (e) {
	e.preventDefault();
        if (window.location.protocol != "https:")
	  window.location.href = "https:" + 
		window.location.href.substring(window.location.protocol.length);
    });

    var vid = document.getElementById("myVideo");

    var playing = false;

    function saveCurrentTime() {
        if (($("#updateCT").length > 0) && playing) {
            $("#ufCP").val(parseInt(vid.currentTime));
            $("#lastPosition").text(parseInt(vid.currentTime));
            console.log("Updating video current time to the data base");
            $("#updateCT").trigger("click");
        }
    }
    $("#myVideo").on({
        pause: function (e) {
            $("#ufCP").val(parseInt(vid.currentTime));
            $("#lastPosition").text(parseInt(vid.currentTime));
            console.log("Pause : " + parseInt(vid.currentTime));
            $("#updateCT").trigger("click");
            playing = false;
            clearInterval(update);
        },
        play: function (e) {
            console.log("Play : " + vid.currentTime);
            playing = true;
            update = setInterval(saveCurrentTime, 5000);
        }
    });
    if($("#lastPosition").length > 0) {
        vid.currentTime = $("#lastPosition").text();
    }
    $(window).scroll(function () {
        var header = $('#header'), scroll = $(window).scrollTop();

        if (scroll >= 80) {
            header.addClass('fixed');
            $(".userTools").slideUp();
            $("#search-form").prev().show().removeClass("bounceOutRight").addClass("bounceInRight");
            $("#form-login").slideUp();
        } else {
            header.removeClass('fixed');
            $(".userTools").slideDown();
            $("#search-form").prev().removeClass("bounceInRight").addClass("bounceOutRight").slideUp("slow");
        }
    });
});