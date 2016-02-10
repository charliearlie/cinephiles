<%-- 
    Document   : view
    Created on : Dec 21, 2015, 2:33:03 PM
    Author     : charliearlie
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="cinephiles.data.model.Media"%>
<%@page import="cinephiles.data.interfaces.IMediaList"%>
<%@page import="cinephiles.data.DAOFactory"%>
<%@page import="cinephiles.DAOFactoryDatabase"%>
<%@page import="cinephiles.data.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String login = "login";
    boolean logged = false;
    boolean onWatchList = false;
    User loggedUser = new User();
    session = request.getSession(false);
    DAOFactory factory = new DAOFactoryDatabase();
    IMediaList mediaList = factory.getMediaList();
    String movieId = "";
    
    ArrayList<Media> watchList = new ArrayList<Media>();
    if (session != null) {
        loggedUser = (User) session.getAttribute("user");
        if (session.getAttribute("user") == null) {

        } else {
            logged = true;
            watchList = mediaList.getWatchlist(loggedUser.getId());
            if (request.getParameter("id") != null) {
                movieId = request.getParameter("id");
            }
        }
    }
    
%>
<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE html>
<html>
    <head>

        <!--This title will be dynamic -->
        <title>Movie</title>

        <!--Core Bootstrap CSS-->
        <link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
        <!-- CSS files -->
        <link rel="stylesheet" href="css/menu.css" />
        <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
        <!-- Bootstrap and JQuery JavaScript files -->
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>

        <!--web fonts-->
        <link href='https://fonts.googleapis.com/css?family=Oxygen:400,700,300' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet' type='text/css'>
        <link rel='icon' href='favicon.ico' type='image/x-icon'/>

        <!-- start menu -->
        <!--<link href="css/megamenu.css" rel="stylesheet" type="text/css" media="all" /> -->
        <script type="text/javascript" src="js/megamenu.js"></script>
        <script>$(document).ready(function () {
        $(".megamenu").megamenu();
    });</script>
        <script type="text/javascript" src="js/jquery.leanModal.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/menu.css" />
        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
        <!---- start-smoth-scrolling---->
        <script type="text/javascript" src="js/move-top.js"></script>
        <script type="text/javascript" src="js/easing.js"></script>
        <script type="text/javascript" src="js/viewing.js"></script>
        <script type="text/javascript">
            jQuery(document).ready(function ($) {
                $(".scroll").click(function (event) {
                    event.preventDefault();
                    $('html,body').animate({scrollTop: $(this.hash).offset().top}, 1200);
                });
            });
        </script>
        <!---- start-smoth-scrolling---->
    </head>
    <body>
        <!-- header-section-starts -->

        <div class="container">
            <div class="main-content">
                <div class="header">
                    <div class="logo">
                        <a href="index.jsp"><h1>Cinephile</h1></a>
                    </div>
                    <div class="search">
                        <div class="search2">
                            <form id="searchmedia" name="searchmedia" onsubmit="this.action = 'search.jsp?search=' + document.getElementById('searchquery').value" action="" method="POST" >
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
                                <li class="active"><a href="index.jsp">Home</a></li>
                                <!-- Mega Menu -->
                                <li class="dropdown w3_megamenu-fw"><a href="movies.html" data-toggle="dropdown" class="dropdown-toggle">Movies<b class="caret"></b></a>
                                    <ul class="dropdown-menu fullwidth">
                                        <li class="w3_megamenu-content">
                                            <div class="row">
                                                <div class="col-sm-6">
                                                    <h3 class="title">Hot movies</h3>
                                                    <ul class="mov_list">
                                                        <li><i class="fa fa-fire fa-lg"></i>99</li>
                                                        <li><a href="movie-single.html">Star Wars: Episode VI - Return of The Jedi</a></li>
                                                    </ul>
                                                    <ul class="mov_list">
                                                        <li><i class="fa fa-fire fa-lg"></i>97</li>
                                                        <li><a href="movie-single.html">Star Wars: Episode V - The Empire Strikes Back</a></li>
                                                    </ul>
                                                    <ul class="mov_list">
                                                        <li><i class="fa fa-fire fa-lg"></i>95</li>
                                                        <li><a href="movie-single.html">Spectre</a></li>
                                                    </ul>
                                                    <ul class="mov_list">
                                                        <li><i class="fa fa-fire fa-lg"></i>93</li>
                                                        <li><a href="movie-single.html">The Martian</a></li>
                                                    </ul>
                                                    <ul class="mov_list">
                                                        <li><i class="fa fa-fire fa-lg"></i>91</li>
                                                        <li><a href="movie-single.html">The Man From U.N.C.L.E</a></li>
                                                    </ul>
                                                </div><!-- end col-4 -->
                                                <div class="col-sm-6 movie-dd">
                                                    <h3 class="title">Coming soon</h3>
                                                    <p><a href="movie-single.html">Star Wars: Episode VII - The Force Awakens</a><span>... 18th December 2015</span></p>
                                                    <p><a href="movie-single.html">The Revenant</a><span>... 8th January 2016</span></p>
                                                    <p><a href="movie-single.html">Dirty Grandpa</a><span>... 22nd January 2016</span></p>
                                                    <p><a href="movie-single.html">Deadpool</a><span>... 12th February 2016</span></p>
                                                    <p><a href="movie-single.html">Batman v Superman: Dawn of Justice</a><span>... 25th March 2016</span></p>
                                                </div>
                                                <div class="clearfix"></div>
                                                <div class="menu-featured-movies">
                                                    <h3 class="title">Featured Trailers</h3>
                                                    <div class="col-md-2 menu-featured-movies-img">
                                                        <a href="movie-single.html"><img src="#" alt="" /></a>
                                                    </div>
                                                    <div class="col-md-2 menu-featured-movies-img">
                                                        <a href="movie-single.html"><img src="#" alt="" /></a>
                                                    </div>
                                                    <div class="col-md-2 menu-featured-movies-img">
                                                        <a href="movie-single.html"><img src="#" alt="" /></a>
                                                    </div>
                                                    <div class="col-md-2 menu-featured-movies-img">
                                                        <a href="movie-single.html"><img src="#" alt="" /></a>
                                                    </div>
                                                    <div class="col-md-2 menu-featured-movies-img">
                                                        <a href="movie-single.html"><img src="#" alt="" /></a>
                                                    </div>
                                                    <div class="col-md-2 menu-featured-movies-img">
                                                        <a href="movie-single.html"><img src="#" alt="" /></a>
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
                                                <h3 class="title">Featured Events</h3>

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

                <!-- AddThis Smart Layers END -->

                <div class="m-single-article">
                    <div class="article-left">
                        <h3 id="name-of-item"></h3>
                        <p><a class="m-green" href="#">Action</a><a class="m-green" href="#">Thriller</a><a class="m-orange" href="#">English</a></p>
                        <div class="clearfix"></div>
                        <div class="article-time-strip">
                            <div class="article-time-strip-left">
                                <p>Duration <span id="runtime"><i class="fa fa-clock-o"></i></span>  &nbsp;&nbsp; Release Date <span id="release-date"><i class="fa fa-calendar"></i></span></p>
                            </div>
                            <div class="clearfix"></div>
                            <div class="article-img" id="mov-show-poster">
                                <!-- <img src="http://img.omdbapi.com/?i=tt2488496&apikey=369fe966&h=500" alt="" /> -->
                            </div>
                            <div class="review-info">
                                <h6 class="span88"></h6>
                                <p class="dirctr"><a href=""></p>
                                <p class="ratingview">Your rating: </p>
                                <div class="rating">
                                    <span>☆</span>
                                    <span>☆</span>
                                    <span>☆</span>
                                    <span>☆</span>
                                    <span>☆</span>
                                </div>
                                <p class="ratingview">
                                    &nbsp;
                                </p>
                                <div class="clearfix"></div>
                                <div class="clearfix"></div>
                                <div class="yrw">
                                    <div class="dropdown-button">
                                        <select class="dropdown" tabindex="9" data-settings="{&quot;wrapperClass&quot;:&quot;flat&quot;}">
                                            <option value="0">Your rating</option>
                                            <option value="1">1.Poor</option>
                                            <option value="2">1.5(Below average)</option>
                                            <option value="3">2.Average</option>
                                            <option value="4">2.5(Above average)</option>
                                            <option value="5">3.Watchable</option>
                                            <option value="6">3.5(Good)</option>
                                            <option value="7">4.5(Very good)</option>
                                            <option value="8">5.Outstanding</option>
                                        </select>
                                    </div>
                                    <div class="rtm text-center">
                                        <form action="AddWatchlist?id=<%= movieId %>" method="POST">
                                            <input type="submit" class="btn btn-primary" value="Add to watchlist" />
                                        </form>
                                    </div>
                                    <div class="clearfix"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="article-right">
                        <div class="grid_3 grid_5">
                            <div class="bs-example bs-example-tabs" role="tabpanel" data-example-id="togglable-tabs">
                                <ul id="myTab" class="nav nav-tabs" role="tablist">
                                    <li role="presentation" class="active"><a href="#home" id="home-tab" role="tab" data-toggle="tab" aria-controls="home" aria-expanded="true">Can You Stream It?</a></li>
                                    <li role="presentation"><a href="#profile" role="tab" id="profile-tab" data-toggle="tab" aria-controls="profile">Related titles</a></li>
                                </ul>
                                <div id="myTabContent" class="tab-content">
                                    <div role="tabpanel" class="tab-pane fade in active" id="home" aria-labelledby="home-tab">
                                        <p class="m-s-t"><img src="images/nf.png" height="60px" /> <span class="streamingav" id="streamingnav1" >&nbsp;&nbsp;&nbsp;&nbsp;  </span></p>
                                        <p class="m-s-t"><img src="images/am.png" height="60px" /> <span class="streamingav">&nbsp;&nbsp;&nbsp;&nbsp;Not available on Amazon</span></p>
                                        <div class="clearfix"></div>
                                        <a class="more-show-time" href="movie-select-show.html">More</a>
                                    </div>
                                    <div role="tabpanel" class="tab-pane fade" id="profile" aria-labelledby="profile-tab">
                                        <p class="m-s-t">Something else</p>
                                        <div class="clearfix"></div>
                                        <a class="more-show-time" href="movie-select-show.html">More</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <div class="reviews-tabs">

                    <ul class="nav nav-tabs responsive hidden-xs hidden-sm" id="myTab">
                        <li class="test-class active"><a class="deco-none misc-class" href="#how-to"> Synopsis</a></li>
                        <li class="test-class"><a href="#features">Critic Reviews (1)</a></li>
                        <li><a class="deco-none" href="#source">User Reviews (11)</a></li>
                    </ul>

                    <div class="tab-content responsive hidden-xs hidden-sm">
                        <div class="tab-pane active" id="how-to">
                            <p id="item-director"><strong> Director : </strong></p>
                            <p id="item-cast"> <strong>Cast & Crew : </strong></p>
                            <p id="plot-summary"><strong>Plot summary: </strong><br></p>


                        </div>
                        <div class="tab-pane" id="features">
                            <p class="cr-left"><strong>Strict-movie-ratings.com</strong></p><div class="rating-cr">
                                <span>☆</span>
                                <span>☆</span>
                                <span>☆</span>
                                <span>☆</span>
                                <span>☆</span>
                            </div>
                            <div class="clearfix"></div>
                            <p>Today is another review by Strict Movie Ratings and this is on a movie we have not even watched yet.
                                Star Wars should have stayed in the 1970s. The new one just isn't what we expect from a sci-fi epic,
                                and we are saying all this without seeing it. That is how good we are.</p>
                            <a href="#">....Read full review</a>
                        </div>
                        <div class="tab-pane" id="source">
                            <div class="response" id="movie-reviews">
                                <div class="media response-info" id="movie-review">
                                    <div class="media-body response-text-right">
                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,There are many variations of passages of Lorem Ipsum available,
                                            sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                                        
                                    </div>
                                    <div class="clearfix"> </div>
                                </div>
                                <div class="media response-info">
                                    <div class="media-left response-text-left">
                                        <a href="#">
                                            <img class="media-object" src="images/icon1.png" alt="">
                                        </a>
                                        <h5><a href="#">Username</a></h5>
                                    </div>
                                    <div class="media-body response-text-right">
                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,There are many variations of passages of Lorem Ipsum available,
                                            sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                                        <ul>
                                            <li>MARCH 25, 2014</li>
                                            <li><a href="single.html">Reply</a></li>
                                        </ul>
                                    </div>
                                    <div class="clearfix"> </div>
                                </div>
                                <div class="media response-info">
                                    <div class="media-left response-text-left">
                                        <a href="#">
                                            <img class="media-object" src="images/icon1.png" alt="">
                                        </a>
                                        <h5><a href="#">Username</a></h5>
                                    </div>
                                    <div class="media-body response-text-right">
                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,There are many variations of passages of Lorem Ipsum available,
                                            sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                                        <ul>
                                            <li>MARCH 25, 2014</li>
                                            <li><a href="single.html">Reply</a></li>
                                        </ul>
                                    </div>
                                    <div class="clearfix"> </div>
                                </div>
                                <div class="media response-info">
                                    <div class="media-left response-text-left">
                                        <a href="#">
                                            <img class="media-object" src="images/icon1.png" alt="">
                                        </a>
                                        <h5><a href="#">Username</a></h5>
                                    </div>
                                    <div class="media-body response-text-right">
                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,There are many variations of passages of Lorem Ipsum available,
                                            sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                                        <ul>
                                            <li>MARCH 25, 2014</li>
                                            <li><a href="single.html">Reply</a></li>
                                        </ul>
                                    </div>
                                    <div class="clearfix"> </div>
                                </div>
                                <div class="media response-info">
                                    <div class="media-left response-text-left">
                                        <a href="#">
                                            <img class="media-object" src="images/icon1.png" alt="">
                                        </a>
                                        <h5><a href="#">Username</a></h5>
                                    </div>
                                    <div class="media-body response-text-right">
                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,There are many variations of passages of Lorem Ipsum available,
                                            sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                                        <ul>
                                            <li>MARCH 25, 2014</li>
                                            <li><a href="single.html">Reply</a></li>
                                        </ul>
                                    </div>
                                    <div class="clearfix"> </div>
                                </div>
                                <div class="media response-info">

                                    <div class="clearfix"> </div>
                                </div>
                            </div>
                        </div>
                    </div>
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
            <p>Cinephile 2015 - Charles Waite, Dan Burgess, Lorenzo Koundouris & Stefan </p>
        </div>
    </div>
    <script src="js/responsive-tabs.js"></script>
    <script type="text/javascript">
                                            $('#myTab a').click(function (e) {
                                                e.preventDefault();
                                                $(this).tab('show');
                                            });

                                            $('#moreTabs a').click(function (e) {
                                                e.preventDefault();
                                                $(this).tab('show');
                                            });

                                            (function ($) {
                                                // Test for making sure event are maintained
                                                $('.js-alert-test').click(function () {
                                                    alert('Button Clicked: Event was maintained');
                                                });
                                                fakewaffle.responsiveTabs(['xs', 'sm']);
                                            })(jQuery);

    </script>
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
