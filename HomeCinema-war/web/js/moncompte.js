$(function() {
    $(".toggle-btn").on("click", function(e) {
        e.preventDefault();
        var $this = $(this);
        $(this).siblings(".toggle-row").slideUp("slow", function() {
            $this.siblings(".toggle-row").find(".alert").parent().hide();
            $this.next().slideDown("slow");
        });
    });
});