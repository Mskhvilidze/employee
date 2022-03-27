$(document).ready(function () {
    $(".inf").click(function (e) {
        $("#myModal").css("display", "block");
        if (userSession != null) {
            let $target = $(e.target);
            let desc = $target.attr("data-id");
            $("#descT").text(desc);
        } else {
            $("#descT").text("Um die Informationen zu bekommen, müssen Sie sich anmelden!");
        }
    })


    $("#clo").click(function () {
        $("#myModal").css("display", "none");
    });
});