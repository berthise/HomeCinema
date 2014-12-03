$(function () {
    $("#login").click(function () {
        console.log($("#form-login").length);
        $("#form-login").slideToggle();
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
});