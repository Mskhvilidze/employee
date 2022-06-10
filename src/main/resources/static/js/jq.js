$(document).ready(function () {
    //Information of Article
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

    //Article
    $("#clo").click(function () {
        $("#myModal").css("display", "none");
    });

    //Article Delete
    $(".deleteArt").click(function (e){
        let $target = $(e.target);
        let nickname = $target.attr("data-id");
        alert("Test: " + nickname);
    });

    //select Topics
    let tbody = document.querySelector("#tbody");
    let tr = tbody.getElementsByTagName("tr");
    let arrayTr = [];
    for (let i = 0; i < tr.length; i++) {
        arrayTr.push(tr[i]);
    }
    $("#selectTopic").change(function (e){
        /*$('table > tbody  > tr').each(function(index, tr) {
        tbody.appendChild(tr)
        });*/

        let value = $("#selectTopic option:selected").val();
        tbody.innerHTML = "";
        for (let i = 0; i < value; i++) {
            tbody.appendChild(arrayTr[i]);
        }
    });
});