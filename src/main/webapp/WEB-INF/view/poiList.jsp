<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="org.solent.spring.map.model.MapPoint"%>
<c:set var = "selectedPage" value = "admin" scope="request"/>
<jsp:include page="header.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>MapApp - Spring Boot + Leaflet</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" />

<link href='https://fonts.googleapis.com/css?family=Roboto'
	rel='stylesheet' type='text/css' />
<link href='https://fonts.googleapis.com/css?family=Exo'
	rel='stylesheet' type='text/css' />
</head>
<body>

	<div class="container">

		<article>
			<header>
				<h1>Map Points</h1>
				<p></p>
			</header>
			<div style="color: red;">${errorMessage}</div>
			<div style="color: green;">${message}</div>

                        <form action="/findMapPoint" method="GET" class="form-inline">
                            <div class="form-group">
                                <label for="searchName">Search by Name:</label>
                                <input type="text" class="form-control" id="name" name="name" placeholder="Enter name">
                            </div>
                            <button type="submit" class="btn btn-primary">Search</button>
                        </form>
			<table class="table">
				<thead>
					<tr>
						<th scope="col">Id</th>
						<th scope="col">name</th>
						<th scope="col">description</th>
						<th scope="col">category</th>
						<th scope="col">Latitude</th>
						<th scope="col">Longitude</th>
                                                <th scope="col">Image</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="mapPoint" items="${mapPoints}">
						<tr>
							<td>${mapPoint.id}</td>
							<td>${mapPoint.name}</td>
							<td>${mapPoint.description}</td>
							<td>${mapPoint.category}</td>
							<td>${mapPoint.lat}</td>
							<td>${mapPoint.lng}</td>
                                                        <td>
                                                           <c:choose>
                <c:when test="${not empty mapPoint.image}">
                    <img src="<%= request.getContextPath() %>/image/${mapPoint.id}" alt="Map Point Image" style="max-width: 100px; max-height: 100px;"/>
                </c:when>
                <c:otherwise>
                    <!-- Display an alternative content or placeholder image when mapPoint.image is null -->
                    <span>No Image Uploaded</span>
                </c:otherwise>
            </c:choose>
                                                        
                                                        </td>
							<td>
								<form action="" method="POST">
									<input type="hidden" name="pointId" value="${mapPoint.id}">
									<input type="hidden" name="action" value="modifyPoint">
									<button class="btn" type="submit">Modify Point</button>
								</form>
								<form action="" method="POST">
									<input type="hidden" name="pointId" value="${mapPoint.id}">
									<input type="hidden" name="action" value="deletePoint">
									<button class="btn" type="submit">Delete Point</button>
								</form>
							</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>







			<section>
			</section>
			<footer> </footer>
			<hr />
		</article>

		<footer style="text-align: center">
			<p>
				<span style="text-decoration: none; color: grey;">/* MapApp -
					<a style="text-decoration: none; color: grey;"
					href="https://github.com/imaginalis"><span
						class="glyphicon glyphicon-star"></span> imaginalis</a> */
				</span>
			</p>
		</footer>

	</div>
                        
                        <jsp:include page="footer.jsp" />


</body>
</html>
