<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>MapApp - Spring Boot + Leaflet</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" />

    <link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'/>
    <link href='https://fonts.googleapis.com/css?family=Exo' rel='stylesheet' type='text/css'/>

    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- Leaflet library import-->
    <link rel="stylesheet" type="text/css" href="./css/leaflet.css" />
    <link rel="stylesheet" type="text/css" href="./css/leaflet-loader.css" />
    <script type='text/javascript' src='./js/jquery.min.js'></script>
    <script type='text/javascript' src='./js/leaflet.js'></script>
    <script type='text/javascript' src='./js/leaflet-loader.js'></script>
    <script type='text/javascript' src='./js/leaflet-layerjson.min.js'></script>
    <!-- -->

</head>
<body>

<div class="container">

    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-5" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">MapApp</a>
            </div>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-5">
                <p class="navbar-text navbar-right"></p>
            </div>
        </div>
    </nav>

    <article>
        <header>
            <h1>Hello world</h1>
            <p></p>
        </header>
        <section>
            <div id="map" style="height: 440px; border: 1px solid #AAA;"></div>
            <div id="loader"></div>
            <p><a href="./swagger-ui/index.html" target="_blank">Swagger (OpenAPI) UI</a></p>
            <p><a href="./api-docs" target="_blank">Swagger (OpenAPI) Json Definition</a></p>

            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. In ac finibus mi. Morbi porttitor urna dui, sed volutpat justo rutrum sit amet. Etiam elementum lacus eget malesuada egestas. Sed id lectus arcu. Pellentesque molestie dignissim diam non commodo. Nunc nec aliquet lectus. Ut a accumsan sapien. Pellentesque sit amet sem nisl. Nulla fringilla vulputate mauris, eleifend dapibus libero. Sed eu cursus orci. In hac habitasse platea dictumst. Vestibulum vel vulputate ex. Nam gravida blandit nisl, at luctus mi interdum ut. Pellentesque et pharetra mi. Proin id placerat diam. Ut porttitor risus in leo tincidunt, a iaculis velit maximus.</p>
            <p>Nunc bibendum sollicitudin ex, vitae lobortis nunc malesuada eget. Maecenas aliquam aliquam elit, nec rutrum justo blandit sit amet. Cras pellentesque egestas nisi at egestas. Donec lacus ipsum, dignissim a accumsan quis, rutrum ac massa. Vivamus sed dolor nisl. Integer convallis, elit sed euismod molestie, purus velit ornare justo, ac maximus diam odio id felis. Maecenas auctor sed quam ac aliquet. Curabitur tempus sed purus sit amet blandit.</p>
            <p>Curabitur eget arcu elementum, pulvinar odio ut, dictum tellus. In hac habitasse platea dictumst. Proin sagittis a ex id tempor. Proin faucibus risus non pellentesque vulputate. Morbi augue sem, finibus non leo ut, fermentum auctor massa. Mauris nisi felis, posuere ut efficitur eu, gravida nec enim. Curabitur tempus id nulla in iaculis. Mauris varius accumsan nunc ut pellentesque. Sed placerat vel nisl quis ornare. Ut imperdiet felis et massa facilisis, vel vestibulum erat varius. Etiam tristique dignissim leo, congue tincidunt mi finibus vitae. Suspendisse quis risus eget arcu pharetra tincidunt eu non sapien. Cras sollicitudin, lectus non venenatis ullamcorper, eros quam condimentum libero, nec fermentum purus neque eu eros. Nullam eget blandit nunc. Mauris ultricies sed sapien non aliquam. Etiam bibendum lectus scelerisque neque eleifend, non semper nisi condimentum.</p>
        </section>
        <footer>

        </footer>
        <hr/>
    </article>

    <footer style="text-align: center">
        <p><span style="text-decoration:none;color:grey;">/* MapApp - <a style="text-decoration:none;color:grey;" href="https://github.com/imaginalis"><span class="glyphicon glyphicon-star"></span>  imaginalis</a> */</span>
        </p>
    </footer>

</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js" ></script>
<!-- Our JS map script importing-->
<script type='text/javascript' src='./js/map/map.js'></script>
<script type='text/javascript' src='./js/map/markers.js'></script>
<!-- -->
</body>
</html>