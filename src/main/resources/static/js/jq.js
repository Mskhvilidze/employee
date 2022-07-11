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

    //Update-Button, Wenn du angemeldet bist und das Artikel ist von dir erstellt, darfst du aktualisieren, sonst nicht!
    $(".btn-update").click(function (e) {
        $("#myModal").css("display", "block");
        let $target = $(e.target);
        let nickname = $target.attr("data-name");
        if (userSession != null) {
            if (userSession.toString() == nickname) {
                let id = $target.attr("data-id");
                window.location.href = "http://localhost:8086/fricke/model/topics/update/" + id;
            } else {
                $("#descT").text("Artikel von anderen Benutzern, darfst du nicht ändern!");
            }
        } else {
            $("#descT").text("Um Articel aktualisieren zu können, müssen sie sich anmelden!");
        }
    });

    //Article
    $("#clo").click(function () {
        $("#myModal").css("display", "none");
    });

    //Article Delete
    $(".deleteArt").click(function (e) {
        let $target = $(e.target);
        let nickname = $target.attr("data-id");
        if (userSession.toString() != nickname) {
            alert("Artikel von anderen Benutzer darfst du nicht löschen!");
        }
    });

    //select Topics
    let tbody = document.querySelector("#tbody");
    let tr = tbody.getElementsByTagName("tr");
    let arrayTr = [];
    for (let i = 0; i < tr.length; i++) {
        arrayTr.push(tr[i]);
    }

    let li = document.createElement("li");
    let a = document.createElement("a");
    let li1 = document.createElement("li");
    let a1 = document.createElement("a");
    let liPage;
    let aPage;
    let nextPage = 1;
    li1.className = "next nextLi ulLi";
    if (arrayTr.length >= 12) {
        const nofPage = Math.ceil(arrayTr.length / value);
        for (let i = 1; i <= nofPage; i++) {
            liPage = document.createElement("li");
            liPage.className = "ulLi";
            aPage = document.createElement("a");
            aPage.href = "#";
            aPage.setAttribute("data-page", "" + i);
            liPage.appendChild(aPage);
            aPage.innerHTML = "" + i;
            $(".pagination").append(liPage);
            aPage.onclick = ev => {
                //TODO
            }
        }
        //li1.className = "next nextLi ulLi";
        a1.href = "#";
        a1.id = "next";
        li1.appendChild(a1);
        a1.innerHTML = "&#155";
        $(".pagination").append(li1);
        a1.onclick = ev => {
            //TODO
        }
    } else {
        //Wenn die Länder der Array kleiner ist als Selections-Value. Am Anfang Value = 12
        li.className = "ulLi";
        a.href = "#";
        a.setAttribute("data-page", "1");
        li.appendChild(a);
        a.innerHTML = "1";
        $(".pagination").append(li);
        a.onclick = ev => {
            alert("Es gibt mehr keine Seite!");
        }
        //li1.className = "next nextLi ulLi";
        a1.href = "#";
        a1.id = "next";
        li1.appendChild(a1);
        a1.innerHTML = "&#155";
        $(".pagination").append(li1);
        a1.onclick = ev => {
            alert("Es gibt mehr keine Seite!");
        }
    }
    //Selector, hier wird ein Wert aus dem Selector ausgewählt und so viel tr wurd auf dem Page erstellt
    $("#selectTopic").change(function () {
        let value = $("#selectTopic option:selected").val();
        let neil = document.querySelectorAll(".ulLi")
        neil.forEach(n => n.remove());
        for (let i = 0; i < arrayTr.length; i++) {
            tbody.appendChild(arrayTr[i]);
        }
        tbody.innerHTML = "";
        if (arrayTr.length >= value) {
            const nofPage = Math.ceil(arrayTr.length / value);
            for (let i = 1; i <= nofPage; i++) {
                liPage = document.createElement("li");
                liPage.className = "ulLi";
                aPage = document.createElement("a");
                aPage.href = "#";
                aPage.setAttribute("data-page", "" + i);
                liPage.appendChild(aPage);
                aPage.innerHTML = "" + i;
                $(".pagination").append(liPage);
                aPage.onclick = ev => {
                    let $target = $(ev.target);
                    let x = $target.attr("data-page");
                    nextPage = x;
                    tbody.innerHTML = "";
                    x--;
                    let start = value * x;
                    let end = start + value;
                    let page = arrayTr.slice(start, end);
                    for (let j = 0; j < page.length; j++) {
                        tbody.appendChild(page[j]);
                    }
                }
            }
            //   li1.className = "next nextLi ulLi";
            a1.href = "#";
            a1.id = "next";
            li1.appendChild(a1);
            a1.innerHTML = "&#155";
            $(".pagination").append(li1);
            a1.onclick = ev => {
                let $target = $(ev.target);
                tbody.innerHTML = "";
                let start = value * nextPage;
                let end = start + value;
                let page = arrayTr.slice(start, end);
                for (let j = 0; j < page.length; j++) {
                    tbody.appendChild(page[j]);
                }
                nextPage++;
            }
        } else {
            //Wenn Value größer ist als die Länder der Array, wird neue a Tag erstellt, damit < 1 > aussieht
            li.className = "ulLi";
            a.href = "#";
            a.setAttribute("data-page", "1");
            li.appendChild(a);
            a.innerHTML = "1";
            $(".pagination").append(li);
            a.onclick = ev => {
                //TODO
            }
            // li1.className = "next nextLi ulLi";
            a1.href = "#";
            a1.id = "next";
            li1.appendChild(a1);
            a1.innerHTML = "&#155";
            $(".pagination").append(li1);
            a1.onclick = ev => {
                //TODO
            }
        }
        for (let i = 0; i < value; i++) {
            tbody.appendChild(arrayTr[i]);
        }
    });
});