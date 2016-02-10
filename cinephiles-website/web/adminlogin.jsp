<%-- 
    Document   : adminlogin
    Created on : Nov 26, 2015, 2:31:05 PM
    Author     : charliearlie
--%>
<%
    boolean woo = false;
    String yay = "";
   if (request.getAttribute("success") != null) {
       yay = "You logged in. woooooo";
   }
    
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Cinephile</title>

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
                        <a href="index.html" style="font-family:Montserrat"><h1>Cinephile</h1></a>
                    </div>
                    <div class="search">
                        <div class="search2">
                            <form>
                                <i class="fa fa-search"></i>
                                <input type="text" value="Search for a movie or show.." onfocus="this.value = '';" onblur="if (this.value == '') {
                                            this.value = 'Search for a movie or show..';
                                        }"/>
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
                                <li class="dropdown"><a href="#" data-toggle="dropdown" class="dropdown-toggle">Contact<b class="caret"></b></a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <form id="contact1" action="#" name="contactform" method="post">
                                                <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                                    <input type="text" name="name" id="name1" class="form-control" placeholder="Name">
                                                    <input type="text" name="email" id="email1" class="form-control" placeholder="Email">
                                                </div>
                                                <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                                    <input type="text" name="phone" id="phone1" class="form-control" placeholder="Phone">
                                                    <input type="text" name="subject" id="subject1" class="form-control" placeholder="Subject">
                                                </div>
                                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                    <textarea class="form-control" name="comments" id="comments1" rows="6" placeholder="Your Message ..."></textarea>
                                                </div>
                                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                    <div class="pull-right">
                                                        <input type="submit" value="SEND" id="submit1" class="btn btn-primary small">
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
                    <br><br><br><center>
                        <form method="POST" action="AdminLogin">
                            <input type="text" name="adminLogin" id="adminlogin" placeholder="Email...."><br>
                            <input type="password" name="adminPassword" id="adminpassword" placeholder="Password...."><br><br><br>
                            <input type="submit" value="Log in" class="btn btn-primary">
                        </form></center>
                </div>
                <br>


                <div class="clearfix"></div>
                <p id ="successfulLogin"> <%= yay %></p>
            </div>
            <div class="copy-rights text-center">
                <p>Cinephile 2015 - Charles Waite, Dan Burgess, Lorenzo Koundouris & Stefan </p>
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

