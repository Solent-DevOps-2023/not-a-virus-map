<%-- 
    Document   : test
    Created on : 14/11/2023, 18:20:38
    Author     : Camponio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%-- Check if the "message" attribute is present (set after creating a user) --%>
<% if (request.getAttribute("message") != null) { %>
    <h3>User Created Successfully!</h3>
    <p>Username: <%= request.getAttribute("message") %></p>
    <hr>
<% } else if (request.getAttribute("deleteMessage") != null) { %>
    <h3>User Deleted Successfully!</h3>
    <p>Deleted User ID: <%= request.getAttribute("deleteMessage") %></p>
    <hr>
<% } else { %>
    <h3>No Action Taken</h3>
    <p>No user creation or deletion operation performed.</p>
<% } %>
    </body>
</html>
