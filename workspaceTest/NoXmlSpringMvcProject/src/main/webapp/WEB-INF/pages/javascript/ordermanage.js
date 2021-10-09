
$(function () {
    var defaultOpen = $('#newordertbody > tr:first').attr("id");
    document.getElementById(defaultOpen).click();
});
         // more btn for add info
        var elemWidth, fitCount, fixedWidth = 120,
            $menu = $("ul#tab_menu_initial"), $collectedSet;

        function collect() {
            elemWidth = $menu.width();
            fitCount = Math.floor(elemWidth / fixedWidth) - 1;
            $collectedSet = $menu.children(":gt(" + fitCount + ")");
            $("#more_menu").empty().append($collectedSet.clone());
        }
        $(document.body).on('mouseenter', ".c_dropdown", function () {
            collect();
        });
        $(window).resize(collect);

        //tr點擊
        function neworDerShowDetail(evt, orderID) {
            var i, tabcontent, tablinks;
            tabcontent = document.getElementsByClassName("tabcontent");
            for (i = 0; i < tabcontent.length; i++) {
                tabcontent[i].style.display = "none";
            }
            tablinks = document.getElementsByClassName("tablinks");
            for (i = 0; i < tablinks.length; i++) {
                tablinks[i].className = tablinks[i].className.replace(" active", "");
            }
            document.getElementById(orderID).style.display = "block";
            evt.currentTarget.className += " active";
        }

        // Get the element with id="defaultOpen" and click on it
        var defaultOpen = $('#newordertbody > tr:first').attr("id");
        document.getElementById(defaultOpen).click();

        //----------
        function makingShowDetail(evt, orderID) {
            neworDerShowDetail(evt, orderID)
        }
        //----------
        function untakeShowDetail(evt, orderID) {
            neworDerShowDetail(evt, orderID)
        }
        //----------
        function untakeShowDetail(evt, orderID) {
            neworDerShowDetail(evt, orderID);
        }
        //----------
        function cancelShowDetail(evt, orderID) {
            neworDerShowDetail(evt, orderID);
        }
        //----------
        function searchShowDetail(evt, orderID) {
            neworDerShowDetail(evt, orderID);
        }