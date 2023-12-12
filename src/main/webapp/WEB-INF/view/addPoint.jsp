<%-- 
    Document   : addPoint
    Created on : 09/12/2023, 17:32:35
    Author     : Dário
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Button to open the pop-up form -->
    <button id="openFormBtn">Add New Map Point</button>

    <!-- The pop-up form -->
    <div id="mapPointForm" class="form-popup">
        <form action="/add" method="post" class="form-container" enctype="multipart/form-data">
    <!-- Add form fields for map point details -->
    <input type="text" name="name" placeholder="<fmt:message key="label.addpoint.name" />">
    <input type="text" name="description" placeholder="<fmt:message key="label.addpoint.description" />">
    <input type="text" name="category" placeholder="<fmt:message key="label.addpoint.category" />">
    <input type="number" name="lat" step="any" placeholder="<fmt:message key="label.addpoint.latitude" />" required>
    <input type="number" name="lng" step="any" placeholder="<fmt:message key="label.addpoint.longitude" />" required>
    
    <!-- Input field for image upload -->
        <input type="file" name="image" accept="image/*">
        
    <button type="submit">Add Map Point</button>
        </form>
    </div>
