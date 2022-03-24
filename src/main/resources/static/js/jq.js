$(document).ready(function () {
    $(".inf").click(function () {
        $("#myModal").css("display", "block");
        let desc = $(".inf").attr("value");
        $("#descT").text(desc);
    })

    $("#clo").click(function () {
        $("#myModal").css("display", "none");
    });
});