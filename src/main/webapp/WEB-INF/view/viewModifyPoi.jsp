<%-- 
    Document   : content
    Created on : Jan 4, 2020, 11:19:47 AM
    Author     : cgallen
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="org.solent.spring.map.user.model.dto.User"%>
<%@page import="org.solent.spring.map.user.model.dto.UserRole"%>
<c:set var="selectedPage" value="poiList" scope="request"/>
<jsp:include page="header.jsp" />

<!-- Begin page content -->
<main role="main" class="container">

    <div>
        <h1>Poi Details ${modifyPoi.id}</h1>
        <!-- print error message if there is one -->
        <div style="color:red;">${errorMessage}</div>
        <div style="color:green;">${message}</div>

        <form action="./viewModifyPoi" method="POST" enctype="multipart/form-data">
            <table class="table">
                <thead>
                </thead>
                <tbody>
                    <tr>
                        <td>Poi ID</td>
                        <td>${modifyPoi.id}</td>
                    </tr>
                    <tr>
                        <td>Name</td>
                        <td><input type="text" name="name" value="${mapPoint.name}" /></td>
                    </tr>
                    <tr>
                        <td>Description</td>
                        <td><input type="text" name="description" value="${mapPoint.description}" /></td>
                    </tr>
                    <tr>
                        <td>Category</td>
                        <td><input type="text" name="category" value="${mapPoint.category}" /></td>
                    </tr>
                    <tr>
                        <td>Latitude</td>
                        <td><input type="number" name="lat" step="any" value="${mapPoint.lat}" /></td>
                    </tr>
                    <tr>
                        <td>Longitude</td>
                        <td><input type="number" name="lng" step="any" value="${mapPoint.lng}" /></td>
                    </tr>
                    <tr>
                        <td>Image</td>
                        <td><input type="file" name="image" value="${mapPoint.image}" accept="image/*" /></td>
                    </tr>
                </tbody>

            </table>

            <input type="hidden" name="poiId" value="${mapPoint.id}" />
            <button class="btn" type="submit">Update Poi ${mapPoint.id}</button>
        </form>

        <c:if test="${sessionUser.userRole =='ADMINISTRATOR'}">
            <br>
            <form action="./poiList">
                <button class="btn" type="submit">Return To Map Points</button>
            </form> 
        </c:if> 

    </div>

</main>

<jsp:include page="footer.jsp" />
