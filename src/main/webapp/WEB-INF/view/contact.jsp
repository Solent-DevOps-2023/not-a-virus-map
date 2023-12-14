<%-- 
    Document   : content
    Created on : Jan 4, 2020, 11:19:47 AM
    Author     : cgallen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header.jsp" />

<!-- Begin page content -->
<main role="main" class="container">
    <H1><fmt:message key="label.navbar.contact" /></H1>

    <section>
        <h2>Contact Information</h2>
        <p>
            Thank you for reaching out to us. Here is our contact information:
        </p>

        <ul>
            <li><strong>Email:</strong> contact@example.com</li>
            <li><strong>Phone:</strong> +1 (555) 123-4567</li>
            <li><strong>Address:</strong> 123 Main Street, Cityville</li>
        </ul>
    </section>

    <section>
    <h2>Contact Form</h2>
    <p>
        You can use the form below to send us a message:
    </p>

    <form action="#" method="post">
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="message">Message:</label>
            <textarea id="message" name="message" class="form-control" rows="5" required></textarea>
        </div>

        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</section>

</main>

<jsp:include page="footer.jsp" />

