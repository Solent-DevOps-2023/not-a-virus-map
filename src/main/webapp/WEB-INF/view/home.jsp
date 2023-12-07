<%-- 
    Document   : content
    Created on : Jan 4, 2020, 11:19:47 AM
    Author     : cgallen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
// request set in controller
//request.setAttribute("selectedPage", "home");
%>
<!-- Leaflet library import-->
    <link rel="stylesheet" type="text/css" href="./css/leaflet.css" />
    <link rel="stylesheet" type="text/css" href="./css/leaflet-loader.css" />
    <script type='text/javascript' src='./js/jquery.min.js'></script>
    <script type='text/javascript' src='./js/leaflet.js'></script>
    <script type='text/javascript' src='./js/leaflet-loader.js'></script>
    <script type='text/javascript' src='./js/leaflet-layerjson.min.js'></script>
    <!-- -->
    <script type='text/javascript' src='./js/geolocation.js'></script>
<jsp:include page="header.jsp" />
<!-- Begin page content -->
<main role="main" class="container">
    <H1>Home</H1>
    <div style="color:red;">${errorMessage}</div>
    <div style="color:green;">${message}</div>
    
    <section>
            <div id="map" style="height: 440px; border: 1px solid #AAA;"></div>
            <div id="loader"></div>
            <p><a href="./swagger-ui/index.html" target="_blank">Swagger (OpenAPI) UI</a></p>
            <p><a href="./api-docs" target="_blank">Swagger (OpenAPI) Json Definition</a></p>

            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. In ac finibus mi. Morbi porttitor urna dui, sed volutpat justo rutrum sit amet. Etiam elementum lacus eget malesuada egestas. Sed id lectus arcu. Pellentesque molestie dignissim diam non commodo. Nunc nec aliquet lectus. Ut a accumsan sapien. Pellentesque sit amet sem nisl. Nulla fringilla vulputate mauris, eleifend dapibus libero. Sed eu cursus orci. In hac habitasse platea dictumst. Vestibulum vel vulputate ex. Nam gravida blandit nisl, at luctus mi interdum ut. Pellentesque et pharetra mi. Proin id placerat diam. Ut porttitor risus in leo tincidunt, a iaculis velit maximus.</p>
            <p>Nunc bibendum sollicitudin ex, vitae lobortis nunc malesuada eget. Maecenas aliquam aliquam elit, nec rutrum justo blandit sit amet. Cras pellentesque egestas nisi at egestas. Donec lacus ipsum, dignissim a accumsan quis, rutrum ac massa. Vivamus sed dolor nisl. Integer convallis, elit sed euismod molestie, purus velit ornare justo, ac maximus diam odio id felis. Maecenas auctor sed quam ac aliquet. Curabitur tempus sed purus sit amet blandit.</p>
            <p>Curabitur eget arcu elementum, pulvinar odio ut, dictum tellus. In hac habitasse platea dictumst. Proin sagittis a ex id tempor. Proin faucibus risus non pellentesque vulputate. Morbi augue sem, finibus non leo ut, fermentum auctor massa. Mauris nisi felis, posuere ut efficitur eu, gravida nec enim. Curabitur tempus id nulla in iaculis. Mauris varius accumsan nunc ut pellentesque. Sed placerat vel nisl quis ornare. Ut imperdiet felis et massa facilisis, vel vestibulum erat varius. Etiam tristique dignissim leo, congue tincidunt mi finibus vitae. Suspendisse quis risus eget arcu pharetra tincidunt eu non sapien. Cras sollicitudin, lectus non venenatis ullamcorper, eros quam condimentum libero, nec fermentum purus neque eu eros. Nullam eget blandit nunc. Mauris ultricies sed sapien non aliquam. Etiam bibendum lectus scelerisque neque eleifend, non semper nisi condimentum.</p>
        </section>

</main>
<jsp:include page="footer.jsp" />
<!-- Our JS map script importing-->
<script type='text/javascript' src='./js/map/map_1.js'></script>
<script type='text/javascript' src='./js/map/markers.js'></script>

<!-- -->
