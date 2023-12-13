<%-- 
    Document   : content
    Created on : Jan 4, 2020, 11:19:47 AM
    Author     : cgallen
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="org.solent.spring.map.user.model.dto.User"%>
<%@page import="org.solent.spring.map.user.model.dto.UserRole"%>
<c:set var = "selectedPage" value = "users" scope="request"/>
<jsp:include page="header.jsp" />

<!-- Begin page content -->
<main role="main" class="container">

    <div>
        <H1>User Details ${modifyPoi.id} </H1>
        <!-- print error message if there is one -->
        <div style="color:red;">${errorMessage}</div>
        <div style="color:green;">${message}</div>

        <form action="./viewModifyPoi" method="POST">
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
                        <td><input type="text" name="name" value="${modifyPoi.name}" /></td>
                    </tr>
                    <tr>
                        <td>Description</td>
                        <td><input type="text" name="description" value="${modifyPoi.description}" /></td>
                    </tr>
                    <tr>
                        <td>Category</td>
                        <td><input type="text" name="category" value="${modifyPoi.category}" /></td>
                    </tr>
                    <tr>
                        <td>Latitude</td>
                        <td><input type="number" name="lat" step="any" value="${modifyPoi.lat}" /></td>
                    </tr>
                    <tr>
                        <td>Longitude</td>
                        <td><input type="number" name="lng" step="any" value="${modifyPoi.lng}" /></td>
                    </tr>
                    <tr>
                        <td>Image</td>
                        <td><input type="file" name="image" value="${modifyPoi.image}" accept="image/* /></td>
                    </tr>
                </tbody>

            </table>

            <c:if test="${sessionUser.userRole !='ADMINISTRATOR'}">
                <p>User Status and role </p>
                <table class="table">
                    <thead>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Role</td>
                            <td>${modifyPoi.userRole}</td>
                        </tr>
                        <tr>
                            <td>enabled</td>
                            <td><c:if test="${modifyPoi.enabled}">ENABLED</c:if><c:if test="${!modifyPoi.enabled}">DISABLED</c:if></td>
                            </tr>
                        </tbody>
                    </table>
            </c:if>

            <c:if test="${sessionUser.userRole =='ADMINISTRATOR'}">
                <p>Manage User Status and role </p>
                <table class="table">
                    <thead>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Role</td>
                            <td>
                                <select class="form-control" name="userRole" >
                                    <c:forEach var="value" items="${UserRole.values()}">
                                        <option value="${value}" <c:if test="${modifyUser.userRole == value}"> selected  </c:if>>${value}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>enabled</td>
                            <td>
                                <select class="form-control" name="userEnabled" >
                                    <option value="true" <c:if test="${modifyUser.enabled}"> selected  </c:if> >ENABLED</option>
                                    <option value="false" <c:if test="${!modifyUser.enabled}"> selected  </c:if> >DISABLED</option>
                                    </select>
                                </td>
                            </tr>
                        </tbody>
                    </table>
            </c:if>

            <input type="hidden" name="username" value="${modifyUser.username}"/>
            <button class="btn" type="submit" >Update User ${modifyUser.username}</button>
        </form>
        <p>Update Password</p>
        <form action="./viewModifyUser" method="post">
            <input type="hidden" name="username" value="${modifyUser.username}"/>
            <input type="hidden" name="action" value="updatePassword"/>
            <p>Password <input type="password" name="password" ></input></p>
            <p>Re Enter Password <input type="password" name="password2" ></input></p>
            <button class="btn" type="submit" >Update ${modifyUser.username} Password</button>
        </form>
        <c:if test="${sessionUser.userRole =='ADMINISTRATOR'}">
            <BR>
            <form action="./users">
                <button class="btn" type="submit" >Return To Users</button>
            </form> 
        </c:if> 

        </div>

    </main>

<jsp:include page="footer.jsp" />
