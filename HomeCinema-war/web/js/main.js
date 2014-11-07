$(function () {
    $("#login").click(function () {
        console.log($("#form-login").length);
        $("#form-login").slideToggle();
    });
});