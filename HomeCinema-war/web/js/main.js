$(function () {
    $("#login").click(function () {
        console.log($("#form-login").length);
        $("#form-login").slideToggle();
    });
    var vid = document.getElementById("myVideo");
    $("#myVideo").on({
        pause: function(e) {
            console.log("Pause : " + vid.currentTime);
        },
        play: function(e) {
            console.log("Play : " + vid.currentTime);
        }
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