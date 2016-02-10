<%-- 
    Document   : index
    Created on : Nov 16, 2015, 8:06:58 PM
    Author     : charliearlie
--%>
<%@page import="cinephiles.data.model.User"%>
<%
    String login = "login";
    boolean logged = false;
    User loggedUser = new User();
    session = request.getSession(false);
    if (session != null) {
        loggedUser = (User) session.getAttribute("user");
        if (session.getAttribute("user") == null) {

        } else {
            logged = true;
        }
    }
    
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Cinephiles</title>

        <!--Core Bootstrap CSS-->
        <link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
        <!-- CSS files -->
        <link rel="stylesheet" href="css/menu.css" />
        <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
        <!-- Bootstrap and JQuery JavaScript files -->
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/homepage.js"></script>

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>

        <!--web fonts-->
        <link href='https://fonts.googleapis.com/css?family=Oxygen:400,700,300' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet' type='text/css'>
        
        <link rel='icon' href='favicon.ico' type='image/x-icon'/>

        <!-- start menu --><!-- 
        <link href="css/megamenu.css" rel="stylesheet" type="text/css" media="all" /> -->
        <script type="text/javascript" src="js/megamenu.js"></script>
        <script>$(document).ready(function () {
                $(".megamenu").megamenu();
            });</script>
        <script type="text/javascript" src="js/jquery.leanModal.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">


        <script src="js/easyResponsiveTabs.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $('#horizontalTab').easyResponsiveTabs({
                    type: 'default', //Types: default, vertical, accordion
                    width: 'auto', //auto or any width like 600px
                    fit: true   // 100% fit in a container
                });
            });
        </script>
        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
        <!---- start-smoth-scrolling---->
        <script type="text/javascript" src="js/move-top.js"></script>
        <script type="text/javascript" src="js/easing.js"></script>
        <script type="text/javascript">
            jQuery(document).ready(function ($) {
                $(".scroll").click(function (event) {
                    event.preventDefault();
                    $('html,body').animate({scrollTop: $(this.hash).offset().top}, 1200);
                });
            });
        </script>
        <!---- start-smooth-scrolling---->

    </head>
    <body>

        <div class="container">
            <div class="main-content">
                <div class="header">
                    <div class="logo">
                        <a href="watchlist.jsp" style="font-family:Montserrat"><h1>Cinephile</h1></a>
                    </div>
                    <div class="search">
                        <div class="search2">
                            <form id="searchmedia" name="searchmedia" onsubmit="this.action='search.jsp?search=' + document.getElementById('searchquery').value" action="" method="POST" >
                                <i class="fa fa-search"></i>
                                <input type="text" name="searchquery" id="searchquery" value="Search for a movie or show.." onfocus="this.value = '';" onblur="if (this.value == '') {
                                            this.value = 'Search for a movie or show..';
                                        }"/>
                                <input type="submit" style="position: absolute; left: -9999px; width: 1px; height: 1px;"
       tabindex="-1" />
                            </form>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <div class="bootstrap_container">
                    <nav class="navbar navbar-default w3_megamenu" role="navigation">
                        <div class="navbar-header">
                            <button type="button" data-toggle="collapse" data-target="#defaultmenu" class="navbar-toggle"><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button><a href="index.html" class="navbar-brand"><i class="fa fa-home"></i></a>
                        </div><!-- end navbar-header -->

                        <div id="defaultmenu" class="navbar-collapse collapse">
                            <ul class="nav navbar-nav">
                                <li class="active"><a href="index.html">Home</a></li>
                                <!-- Mega Menu -->
                                <li class="dropdown w3_megamenu-fw"><a href="movies.html" data-toggle="dropdown" class="dropdown-toggle">Movies<b class="caret"></b></a>
                                    <ul class="dropdown-menu fullwidth">
                                        <li class="w3_megamenu-content">
                                            <div class="row">
                                                <div class="col-sm-6">
                                                    <h3 class="title">Hot movies</h3>
                                                    <ul class="mov_list">
                                                        <li><i class="fa fa-fire fa-lg"></i>99</li>
                                                        <li><a href="view.html?id=tt0086190">Star Wars: Episode VI - Return of The Jedi</a></li>
                                                    </ul>
                                                    <ul class="mov_list">
                                                        <li><i class="fa fa-fire fa-lg"></i>97</li>
                                                        <li><a href="view.html?id=tt0080684">Star Wars: Episode V - The Empire Strikes Back</a></li>
                                                    </ul>
                                                    <ul class="mov_list">
                                                        <li><i class="fa fa-fire fa-lg"></i>95</li>
                                                        <li><a href="view.html?id=tt0080684">Spectre</a></li>
                                                    </ul>
                                                    <ul class="mov_list">
                                                        <li><i class="fa fa-fire fa-lg"></i>93</li>
                                                        <li><a href="view.html?id=tt0080684">The Martian</a></li>
                                                    </ul>
                                                    <ul class="mov_list">
                                                        <li><i class="fa fa-fire fa-lg"></i>91</li>
                                                        <li><a href="view.html?id=tt0080684">The Man From U.N.C.L.E</a></li>
                                                    </ul>
                                                </div><!-- end col-4 -->
                                                <div class="col-sm-6 movie-dd">
                                                    <h3 class="title">Coming soon</h3>
                                                    <p><a href="view.html?id=tt0080684">Star Wars: Episode VII - The Force Awakens</a><span>... 18th December 2015</span></p>
                                                    <p><a href="view.html?id=tt0080684">The Revenant</a><span>... 8th January 2016</span></p>
                                                    <p><a href="view.html?id=tt0080684">Dirty Grandpa</a><span>... 22nd January 2016</span></p>
                                                    <p><a href="view.html?id=tt0080684">Deadpool</a><span>... 12th February 2016</span></p>
                                                    <p><a href="view.html?id=tt0080684">Batman v Superman: Dawn of Justice</a><span>... 25th March 2016</span></p>
                                                </div>
                                                <div class="clearfix"></div>
                                                <div class="menu-featured-movies">
                                                    <h3 class="title">Featured Trailers</h3>
                                                    <div class="col-md-2 menu-featured-movies-img">
                                                        <a href="view.html?id=tt0080684"><img src="images/mf1.jpg" alt="" /></a>
                                                    </div>
                                                    <div class="col-md-2 menu-featured-movies-img">
                                                        <a href="view.html?id=tt0080684"><img src="images/mf1.jpg" alt="" /></a>
                                                    </div>
                                                    <div class="col-md-2 menu-featured-movies-img">
                                                        <a href="view.html?id=tt0080684"><img src="images/mf1.jpg" alt="" /></a>
                                                    </div>
                                                    <div class="col-md-2 menu-featured-movies-img">
                                                        <a href="view.html?id=tt0080684"><img src="images/mf1.jpg" alt="" /></a>
                                                    </div>
                                                    <div class="col-md-2 menu-featured-movies-img">
                                                        <a href="view.html?id=tt0080684"><img src="images/mf1.jpg" alt="" /></a>
                                                    </div>
                                                    <div class="col-md-2 menu-featured-movies-img">
                                                        <a href="view.html?id=tt0080684"><img src="images/mf1.jpg" alt="" /></a>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                </div>
                                            </div><!-- end row -->
                                            <hr>

                                        </li>
                                    </ul>
                                </li>
                                <li class="dropdown w3_megamenu-fw"><a href="#" data-toggle="dropdown" class="dropdown-toggle">TV Shows<b class="caret"></b></a>
                                    <ul class="dropdown-menu half">
                                        <li class="w3_megamenu-content withdesc">
                                            <div class="row">
                                                <h3 class="title"></h3>

                                            </div>
                                        </li>
                                    </ul>
                                </li>

                                <!-- end dropdown w3_megamenu-fw -->
                            </ul><!-- end nav navbar-nav -->

                            <ul class="nav navbar-nav navbar-right">
                                <li class="dropdown"><a href="#" data-toggle="dropdown" class="dropdown-toggle">
                                        
                                        <% if(logged) { %> 
                                        <%= loggedUser.getEmailAddress() %>
                                        <% } else { %> Login <% } %>
                                        <b class="caret"></b></a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <form id="login" action="Login" name="loginform" method="post">
                                                <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                                    <br><br><input type="text" name="adminLogin" id="adminlogin" placeholder="Email...."><br><br>
                                                    <input type="password" name="adminPassword" id="adminpassword" placeholder="Password....">
                                                    <p>Not a member? Register <a href="register.jsp">here</a></p>
                                                    
                                                </div>
                                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                    <div class="pull-right">
                                                        <input type="submit" value="Log in" class="btn btn-primary">
                                                    </div>
                                                </div>
                                                <div class="clearfix"></div>
                                            </form>
                                        </li>
                                    </ul>
                                </li>
                            </ul><!-- end nav navbar-nav navbar-right -->
                        </div><!-- end #navbar-collapse-1 -->

                    </nav><!-- end navbar navbar-default w3_megamenu -->
                </div><!-- end container -->


                <div class="main-banner">
                    <section class="slider">
                        <div class="flexslider">
                            <ul class="slides">
                                <li>
                                    <img src="images/swfa.jpg" height="600px" class="img-responsive" alt="" />
                                </li>
                                <li>
                                    <img src="images/revenant.jpg" class="img-responsive" alt="" />
                                </li>
                                <li>
                                    <img src="images/he.jpg" class="img-responsive" alt="" />
                                </li>
                                <li>
                                    <img src="images/ithots.jpg" class="img-responsive" alt="" />
                                </li>
                            </ul>
                        </div>
                    </section>

                    <!-- FlexSlider -->
                    <script defer src="js/jquery.flexslider.js"></script>
                    <link rel="stylesheet" href="css/flexslider.css" type="text/css" media="screen" />
                    <script type="text/javascript">
                                    $(function () {
                                        SyntaxHighlighter.all();
                                    });
                                    $(window).load(function () {
                                        $('.flexslider').flexslider({
                                            animation: "slide",
                                            start: function (slider) {
                                                $('body').removeClass('loading');
                                            }
                                        });
                                    });
                    </script>


                    <div class="clearfix"></div>


                </div>
                <br>
                <div class="footer-top-grid">
                    <div class="list-of-movies col-md-8">
                        <div class="tabs">
                            <div class="sap_tabs">
                                <div id="horizontalTab" style="display: block; width: 100%; margin: 0px;">
                                    <ul class="resp-tabs-list">
                                        <li class="resp-tab-item" aria-controls="tab_item-2" role="tab"><span>Hot this week</span></li>
                                        <li class="resp-tab-item" aria-controls="tab_item-1" role="tab"><span>On Netflix</span></li>
                                        <li class="resp-tab-item" aria-controls="tab_item-0" role="tab"><span>Hottest Shows</span></li>

                                        <div class="clearfix"></div>

                                    </ul>
                                    <div class="resp-tabs-container">
                                        <div class="tab-1 resp-tab-content" aria-labelledby="tab_item-0">
                                            <ul class="tab_img">
                                                <li>
                                                    <div class="view view-first">
                                                        <a id="hot1"></a>
                                                        <div class="info1"> </div>
                                                        <div class="mask">
                                                        </div>

                                                    </div>
                                                </li>
                                                <li>
                                                    <div class="view view-first">
                                                        <a id="hot2"></a>
                                                        <div class="info1"> </div>
                                                        <div class="mask">
                                                        </div>
                                                    </div>
                                                </li>
                                                <li>
                                                    <div class="view view-first">
                                                        <a id="hot3"></a>
                                                        <div class="mask">
                                                            <div class="info1"> </div>
                                                        </div>

                                                    </div>
                                                </li>
                                                <div class="clearfix"></div>
                                            </ul>
                                        </div>
                                        <div class="tab-1 resp-tab-content" aria-labelledby="tab_item-1">
                                            <ul class="tab_img">
                                                <li>
                                                    <div class="view view-first">
                                                        <a id="net1"></a>
                                                        <div class="mask">
                                                            <div class="info1"> </div>
                                                        </div>
                                                    </div>
                                                </li>
                                                <li>
                                                    <div class="view view-first">
                                                        <a id="net2"></a>
                                                        <div class="mask">
                                                            <div class="info1"> </div>
                                                        </div>

                                                    </div>
                                                </li>
                                                <li class="last">
                                                    <div class="view view-first">
                                                        <a id="net3"></a>
                                                        <div class="mask">
                                                            <div class="info1"> </div>
                                                        </div>

                                                    </div>
                                                </li>
                                                <div class="clearfix"></div>
                                            </ul>
                                        </div>
                                        <div class="tab-1 resp-tab-content" aria-labelledby="tab_item-2">
                                            <ul class="tab_img">
                                                <li>
                                                    <div class="view view-first">
                                                        <a id="show1"></a>
                                                        <div class="mask">
                                                            <div class="info1"> </div>
                                                        </div>

                                                    </div>
                                                </li>
                                                <li>
                                                    <div class="view view-first">
                                                        <a id="show2"></a>
                                                        <div class="mask">
                                                            <div class="info1"> </div>
                                                        </div>

                                                    </div>
                                                </li>
                                                <li class="last">
                                                    <div class="view view-first">
                                                        <a id="show3"></a>
                                                        <div class="mask">
                                                            <div class="info1"> </div>
                                                        </div>

                                                    </div>
                                                </li>
                                                <div class="clearfix"></div>
                                            </ul>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                        <div class="featured">
                            <h4>Recommended for you</h4>
                            <ul>
                                <li>
                                    <div class="f-movie">
                                        <!--<div class="f-movie-img">-->
                                        <div class="view view-first">
                                            <a id="rec1"></a>
                                        </div>
                                        <div class="f-watchlist">
                                            <a href="view.html?id=tt0361748">Add to watchlist</a>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="f-movie">
                                        <!--<div class="f-movie-img">-->
                                        <div class="view view-first">
                                            <a id="rec2"></a>
                                        </div>

                                        <div class="f-watchlist">
                                            <a href="view.html?id=tt2294629">Add to watchlist</a>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="f-movie">
                                        <!--<div class="f-movie-img">-->
                                        <div class="view view-first">
                                            <a id="rec3"></a>
                                        </div>

                                        <div class="f-watchlist">
                                            <a href="movie-select-show.html">Add to watchlist</a>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="f-movie">
                                        <!--<div class="f-movie-img">-->
                                        <div class="view view-first">
                                            <a id="rec4"></a>
                                        </div>
                                        <div class="f-watchlist">
                                            <a href="#">Add to watchlist</a>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="f-movie">
                                        <!--<div class="f-movie-img">-->
                                        <div class="view view-first">
                                            <a id="rec5"></a>
                                        </div>

                                        <div class="f-watchlist">
                                            <a href="#">Add to watchlist</a>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="f-movie">
                                        <!--<div class="f-movie-img">-->
                                        <div class="view view-first">
                                            <a id="rec6"></a>
                                        </div>

                                        <div class="f-watchlist">
                                            <a href="#">Add to watchlist</a>
                                        </div>
                                    </div>
                                </li>
                                <div class="clearfix"></div>
                            </ul>
                        </div>
                    </div>
                    <div class="right-side-bar">
                        <div class="top-movies-and-shows">
                            <h4>Most viewed this week</h4>
                            <ul class="mov_list">
                                <li><i class="fa fa-star"></i></li>
                                <li>8.6</li>
                                <li><a href="#">The Walking Dead</a></li>
                            </ul>
                            <ul class="mov_list">
                                <li><i class="fa fa-star"></i></li>
                                <li>8.2</li>
                                <li><a href="#">Spectre</a></li>
                            </ul>
                            <ul class="mov_list">
                                <li><i class="fa fa-star"></i></li>
                                <li>n/a</li>
                                <li><a href="#">The Hunger Games: Mockingjay Part 2</a></li>
                            </ul>
                            <ul class="mov_list">
                                <li><i class="fa fa-star"></i></li>
                                <li>8.1</li>
                                <li><a href="#">The Martian</a></li>
                            </ul>
                            <ul class="mov_list">
                                <li><i class="fa fa-star"></i></li>
                                <li>9.1</li>
                                <li><a href="#">Narcos</a></li>
                            </ul>
                            <ul class="mov_list">
                                <li><i class="fa fa-star"></i></li>
                                <li>8.6</li>
                                <li><a href="#">Inside Out</a></li>
                            </ul>
                            <ul class="mov_list">
                                <li><i class="fa fa-star"></i></li>
                                <li>9.1</li>
                                <li><a href="#">Mr. Robot</a></li>
                            </ul>
                            <ul class="mov_list">
                                <li><i class="fa fa-star"></i></li>
                                <li>n/a</li>
                                <li><a href="#">Star Wars: Episode VII - The Force Awakens</a></li>
                            </ul>
                            <ul class="mov_list">
                                <li><i class="fa fa-star"></i></li>
                                <li>7.0</li>
                                <li><a href="#">Crimson Peak</a></li>
                            </ul>
                            <ul class="mov_list">
                                <li><i class="fa fa-star"></i></li>
                                <li>7.3</li>
                                <li><a href="#">The Gift</a></li>
                            </ul>
                        </div>
                        <div class="our-blog">
                            <br>
                            <h5>Latest News</h5><br>
                            <!-- start feedwind code --><script type="text/javascript">document.write('\x3Cscript type="text/javascript" src="' + ('https:' == document.location.protocol ? 'https://' : 'http://') + 'feed.mikle.com/js/rssmikle.js">\x3C/script>');</script><script type="text/javascript">(function() {var params = {rssmikle_url: "http://feeds.feedburner.com/thr/news.",rssmikle_frame_width: "271",rssmikle_frame_height: "720",frame_height_by_article: "0",rssmikle_target: "_blank",rssmikle_font: "Arial, Helvetica, sans-serif",rssmikle_font_size: "12",rssmikle_border: "off",responsive: "off",rssmikle_css_url: "",text_align: "left",text_align2: "left",corner: "off",scrollbar: "on",autoscroll: "on",scrolldirection: "up",scrollstep: "5",mcspeed: "20",sort: "Off",rssmikle_title: "on",rssmikle_title_sentence: "",rssmikle_title_link: "",rssmikle_title_bgcolor: "#0066FF",rssmikle_title_color: "#FFFFFF",rssmikle_title_bgimage: "",rssmikle_item_bgcolor: "#FFFFFF",rssmikle_item_bgimage: "",rssmikle_item_title_length: "55",rssmikle_item_title_color: "#0066FF",rssmikle_item_border_bottom: "on",rssmikle_item_description: "on",item_link: "off",rssmikle_item_description_length: "150",rssmikle_item_description_color: "#666666",rssmikle_item_date: "gl1",rssmikle_timezone: "Etc/GMT",datetime_format: "%b %e, %Y %l:%M %p",item_description_style: "text+tn",item_thumbnail: "crop",item_thumbnail_selection: "auto",article_num: "15",rssmikle_item_podcast: "off",keyword_inc: "",keyword_exc: ""};feedwind_show_widget_iframe(params);})();</script><div style="font-size:10px; text-align:center; width:371px;"><a href="http://feed.mikle.com/" target="_blank" style="color:#CCCCCC;">RSS Feed Widget</a><!--Please display the above link in your web page according to Terms of Service.--></div><!-- end feedwind code -->
                        </div>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
            <div class="footer">
                <div class="col-md-3 footer-left">
                    <div class="f-mov-list">
                        <h4>Streaming Services</h4>
                        <ul>
                            <li><a href="http://netflix.com">Netflix</a></li>
                            <li><a href="http://www.amazon.co.uk">Amazon Instant Video</a></li>
                            <li><a href="http://www.roku.com">Roku</a></li>
                            <li><a href="http://www.crackle.com">Crackle</a></li>
                            <li><a href="http://www.youtube.com">YouTube</a></li>
                        </ul>
                        <div class="clearfix"></div>
                    </div>
                </div>
                <div class="col-md-3 footer-left">
                    <div class="f-mov-list">
                        <h4>Cinemas & Regions</h4>
                        <ul>
                            <li><a href="regions.html">Local cinemas</a></li>
                            <li><a href="cinema-chain.html">National cinemas</a></li>
                            <li><a href="cinemas.html">Special events</a></li>
                        </ul>
                        <div class="clearfix"></div>
                    </div>
                </div>
                <div class="col-md-3 footer-left">
                    <div class="f-mov-list">
                        <h4>Something can go here</h4>
                        <ul>
                            <li><a href="#">Help & support</a></li>
                            <li><a href="#">Jobs</a></li>
                        </ul>
                        <div class="clearfix"></div>
                    </div>

                </div>
                <div class="col-md-3 footer-left">
                    <div class="f-mov-list">
                        <h4>Movies by Popular Genre</h4>
                        <ul>
                            <li><a href="movies.html">Action</a></li>
                            <li><a href="movies.html">Romance</a></li>
                            <li><a href="movies.html">Comedy</a></li>
                            <li><a href="movies.html">Adventure</a></li>
                            <li><a href="movies.html">Classic</a></li>
                            <li><a href="movies.html">Crime</a></li>
                            <li><a href="movies.html">Drama</a></li>
                            <li><a href="movies.html">Family </a></li>
                            <li><a href="movies.html">Fantasy</a></li>
                            <li><a href="movies.html">Thriller</a></li>
                        </ul>
                        <div class="clearfix"></div>
                    </div>
                </div>
                <div class="clearfix"></div>
                <div class="col-md-12">
                    <div class="footer-right">
                        <div class="follow-us">
                            <h5 class="f-head">Follow us</h5>
                            <ul class="social-icons">
                                <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                                <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                <li><a href="#"><i class="fa fa-youtube"></i></a></li>
                                <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                            </ul>
                            <div class="clearfix"></div>
                        </div>
                        <div class="subscribe">
                            <h5 class="f-head">Subscribe to our newsletters</h5>
                            <input type="text" class="text" value="Enter Email ID" onfocus="this.value = '';" onblur="if (this.value == 'Enter email...') {
                                        this.value = 'Enter Email ID';
                                    }">
                            <input type="submit" value="submit">
                            <div class="clearfix"></div>
                        </div>
                        <div class="recent_24by7">
                            <a class="play-icon popup-with-zoom-anim" href="#small-dialog" href="#"><i class="fa fa-calendar-o"></i>Something</a>
                            <a href="support.html"><i class="fa fa-code"></i>Come work for us</a>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
            <div class="clearfix"></div>
        </div>
        <div class="copy-rights text-center">
            <p>Cinephiles 2015 - Charles Waite & Lorenzo Koundouris </p>
        </div>
    </div>
    <script type="text/javascript">
        $(document).ready(function () {
            /*
             var defaults = {
             containerID: 'toTop', // fading element id
             containerHoverID: 'toTopHover', // fading element hover id
             scrollSpeed: 1200,
             easingType: 'linear'
             };
             */

            $().UItoTop({easingType: 'easeOutQuart'});

        });
    </script>
    <a href="#home" class="scroll" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>


</body>
</html>
