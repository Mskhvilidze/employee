$(document).ready(function () {
    $(".inf").click(function (e) {
        $("#myModal").css("display", "block");
        let $target = $(e.target);
        let desc = $target.attr("data-id");
        $("#descT").text(desc);
    })


    $("#clo").click(function () {
        $("#myModal").css("display", "none");
    });
});