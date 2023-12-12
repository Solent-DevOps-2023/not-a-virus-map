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
    <link rel="stylesheet" type="text/css" href="../resources/css/common.css" />
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
    <jsp:include page="addPoint.jsp" />
    
    <section>
            <div id="map" style="height: 440px; border: 1px solid #AAA;"></div>
            <div id="loader"></div>
            <p><a href="./swagger-ui/index.html" target="_blank">Swagger (OpenAPI) UI</a></p>
            <p><a href="./api-docs" target="_blank">Swagger (OpenAPI) Json Definition</a></p>
        </section>
    

</main>
<jsp:include page="footer.jsp" />
<!-- Our JS map script importing-->
<script type='text/javascript' src='./js/map/map_1.js'></script>
<script type='text/javascript' src='./js/map/markers.js'></script>
<script type='text/javascript' src='../resources/js/popUpForm.js'></script>

<!-- -->
