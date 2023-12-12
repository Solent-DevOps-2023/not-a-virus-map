<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${cookie['userLocale'].value}" />
<fmt:setBundle basename="messages" />
<script>
    //Sets locales
    function setLocaleCookie(locale) {
        document.cookie = "userLocale=" + locale + "; path=/";
        location.reload(); //Reload the page after setting the cookie
    }
    console.log(${param.userLocale})
</script>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="../../favicon.ico">
        <!--<link rel="canonical" href="https://getbootstrap.com/docs/3.3/examples/navbar/">-->

        <title>Navbar Template for Bootstrap</title>

        <!-- Bootstrap core CSS -->
        <link href="./resources/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="./resources/css/navbar.css" rel="stylesheet">

    </head>

    <body>

        <div class="container">

            <!-- Static navbar -->
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="#">Not a Virus Map</a>
                    </div>
                    <div id="navbar" class="navbar-collapse collapse">
                        <ul class="nav navbar-nav">
                            <li <% if ("home".equals(request.getAttribute("selectedPage"))) {%> class="active"  <% } %> ><a href="./home"><fmt:message key="label.navbar.home" /></a></li> 
                            <li <% if ("about".equals(request.getAttribute("selectedPage"))) {%>  class="active"  <% } %> ><a href="./about"><fmt:message key="label.navbar.about" /></a></li> 
                            <li <% if ("contact".equals(request.getAttribute("selectedPage"))) {%>  class="active"  <% }%> ><a href="./contact"><fmt:message key="label.navbar.contact" /></a></li>
			    <li class="dropdown" >
				<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> Set language <span class="caret"></span></a>
				<ul class="dropdown-menu">
				    <li><a href="#" onclick="setLocaleCookie('en')"><fmt:message key="label.lang.en" /></a></li>
				    <li><a href="#" onclick="setLocaleCookie('fr')"><fmt:message key="label.lang.fr" /></a></li>
				    <li><a href="#" onclick="setLocaleCookie('de')"><fmt:message key="label.lang.de" /></a></li>
				</ul>
			    </li>

                                <c:if test="${sessionUser.userRole =='ADMINISTRATOR'}">
                                <li class="dropdown" >
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> Admin <span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="./users">Manage Users</a></li>
                                        <li><a href="./catalog">Manage Catalogue</a></li>
                                    </ul>
                                </li>
                            </c:if>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <!-- user role:  ${sessionUser.userRole}-->
                            <c:if test="${sessionUser.userRole =='ANONYMOUS'}">
                                <li><a href="./login"><fmt:message key="label.navbar.login" /></a></li>
                                </c:if>
                                <c:if test="${sessionUser.userRole !='ANONYMOUS'}">
                                <form id="logoutForm" method="POST" action="./logout">
                                </form>
                                <form id="profile" method="GET" action="./viewModifyUser">
                                    <input type="hidden" name="username" value="${sessionUser.username}"/>
                                </form>
                                <p class="text-muted"> Welcome 
                                    <c:if test="${sessionUser.userRole =='ADMINISTRATOR'}"> Admin</c:if>                                   
                                    ${sessionUser.username}&nbsp;&nbsp;
                                    <a onclick="document.forms['logoutForm'].submit()">Logout</a><BR>
                                    <a onclick="document.forms['profile'].submit()">User Profile</a></p>
                                </c:if>
                        </ul>
                    </div><!--/.nav-collapse -->
                </div><!--/.container-fluid -->
            </nav>