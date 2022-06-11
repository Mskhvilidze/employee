$(document).ready(function (newChild, refChild) {
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
    $(".deleteArt").click(function (e) {
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
    /* for (let i = 0; i < 12; i++) {
         tbody.appendChild(arrayTr[i]);
     }*/
    $("#selectTopic").change(function (e) {
        let value = $("#selectTopic option:selected").val();
        if (value == "NO SET") {
            ('table > tbody  > tr').each(function (index, tr) {
                tbody.appendChild(tr);
            });
        } else
            tbody.innerHTML = "";
        for (let i = 0; i < value; i++) {
            tbody.appendChild(arrayTr[i]);
        }
    });

    if (arrayTr.length > 2) {
        const nofPage = arrayTr.length / 2;
        for (let i = 0; i <= nofPage; i++) {
            let li = document.createElement("li");
            li.className = "ulLi";
            let a = document.createElement("a");
            a.href = "#";
            a.setAttribute("data-page", "" +i);
            li.appendChild(a);
            a.innerHTML = "" + i;
            $(".pagination").append(li);
        }
        let li = document.createElement("li");
        let a = document.createElement("a");
        li.className = "next nextLi ulLi";
        a.href = "#";
        a.id = "next";
        li.appendChild(a);
        a.innerHTML = "&#155";
        $(".pagination").append(li);
    }
});