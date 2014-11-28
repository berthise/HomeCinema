$(function () {
    $("#login").click(function () {
        console.log($("#form-login").length);
        $("#form-login").slideToggle();
    });
    var vid = document.getElementById("myVideo");
    $("#myVideo").on({
        pause: function(e) {
            $("#ufCP").val(parseInt(vid.currentTime));
            console.log("Pause : " + parseInt(vid.currentTime));
            $("#updateCT").click();
        },
        play: function(e) {
            console.log("Play : " + vid.currentTime);
        }
    });
    $("#ufCP").on("keyup", function() {
        console.log($(this).val());
    });
    
    function saveCurrentTime(time) {
        
    }
    /*var vid = document.getElementById("myVideo");
	    vid.onpause = function () {
	      // Mise à jour de l'état pause
              console.log(vid.currentTime);
	    };
	    vid.onplay = function () {
	      // Mise à jour de l'état play
	    };
	    function initVideo() {
	      // Mise à jour de la position de la vidéo selon l'état
	      //vid.currentTime = 4;
	    }*/
});