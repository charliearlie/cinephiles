$(document).ready(function() {
  var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
  };

  var movieId = getUrlParameter("id");
        sUrl = 'http://www.omdbapi.com/?i=' + movieId;
        $.ajax(sUrl, {
            complete: function(p_oXHR, p_sStatus){
                oData = $.parseJSON(p_oXHR.responseText);
                var $title = document.getElementById("name-of-item");
                var $runtime = document.getElementById("runtime");
                var $release = document.getElementById("release-date");
                var $poster = document.getElementById("mov-show-poster");
                var img = document.createElement("img");
                var $cast = document.getElementById("item-cast");
                var $director = document.getElementById("item-director");
                var $plot = document.getElementById("plot-summary");
                img.setAttribute("src", oData.Poster);
                img.setAttribute("height", "400px");
                $poster.appendChild(img);
                $title.appendChild(document.createTextNode(oData.Title + " (" + oData.Year + ")") );
                $runtime.appendChild(document.createTextNode(oData.Runtime));
                $release.appendChild(document.createTextNode(oData.Released));
                $cast.appendChild(document.createTextNode(oData.Actors));
                $director.appendChild(document.createTextNode(oData.Director));
                $plot.appendChild(document.createTextNode(oData.Plot));
            }
        });
});
