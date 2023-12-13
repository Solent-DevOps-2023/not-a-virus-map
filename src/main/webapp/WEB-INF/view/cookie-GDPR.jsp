<%-- 
    Document   : content
    Created on : Jan 4, 2020, 11:19:47 AM
    Author     : cgallen
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
// request set in controller
//    request.setAttribute("selectedPage","cookie-GDPR");
%>
<jsp:include page="header.jsp" />
<!-- Begin page content -->
<main role="main" class="container">
    <H1><fmt:message key="label.navbar.about" /></H1>
</main>
<head>
    <title>GDPR Policy for [Your Web Application Name]</title>
    <!-- Include any necessary CSS or other metadata -->
</head>
    <body>
        <h1>GDPR Policy for [Your Web Application Name]</h1>
        
        <h2>Introduction</h2>
        <p>[Your Web Application Name] is dedicated to safeguarding user data, ensuring compliance with the General Data Protection Regulation (GDPR). This policy outlines our approach to collecting, processing, storing, and managing data in line with GDPR guidelines.</p>
        
        <h2>Data Collection and Processing</h2>
        <h3>Types of Data Collected:</h3>
        <ul>
            <li>Personal data: Name, email address, contact information.</li>
            <li>Geolocation data: Usage of location services for mapping features.</li>
            <li>Uploaded metadata: Information added to the map by users.</li>
        </ul>
        
        <h3>Purpose of Data Collection:</h3>
        <ul>
            <li>Personal data: To facilitate login features, communicate updates, and provide personalized services.</li>
            <li>Geolocation data: To enhance user experience with map features and services dependent on location.</li>
            <li>Uploaded metadata: To pin user-provided information to specific locations on the map.</li>
        </ul>
        
        <h3>Lawful Basis for Processing:</h3>
        <ul>
            <li>Personal data: Consent provided during account creation or as necessary for the performance of our services.</li>
            <li>Geolocation data: Consent granted for using location services.</li>
            <li>Uploaded metadata: Consent for storing and displaying user-provided data on the map.</li>
        </ul>

        <h2>User Rights</h2>
        <h3>Right to Access:</h3>
        <p>Request access to your personal and geolocation data stored on our servers by contacting [contact email/phone].</p>
        
        <h3>Right to Rectification:</h3>
        <p>Update or correct your personal information and geolocation data through account settings.</p>
        
        <h3>Right to Erasure:</h3>
        <p>Request deletion of your personal, geolocation data, or uploaded metadata. Contact us to initiate the erasure process.</p>
        
        <h3>Right to Object:</h3>
        <p>Object to specific processing activities related to your data by contacting us.</p>

        <h2>Data Security Measures</h2>
        <h3>Data Security Practices:</h3>
        <p>We implement robust security measures to protect all user data, including personal information, geolocation data, and uploaded metadata.</p>
        <p>Access to data is restricted to authorized personnel only.</p>
        
        <h3>Encryption and Storage:</h3>
        <p>Your data, including geolocation information and uploaded metadata, is encrypted and securely stored on external databases.</p>

        <h2>Third-Party Data Sharing</h2>
        <h3>Third-Party Services:</h3>
        <p>We use external databases and services to store and manage user data. These providers comply with GDPR regulations.</p>
        <p>Data may be transferred internationally to these external databases for storage and processing.</p>

        <h2>Cookie Policy and Consent</h2>
        
        <h3>Cookie Usage:</h3>
        <p>We use cookies for improved website functionality and user experience. Refer to our Cookie Policy for details.</p>
        <p>By using our web application, you consent to our use of cookies.</p>

        <h2>Contact Information</h2>
        <p><strong>Data Protection Officer (if applicable):</strong> [Name, Email, Phone]</p>
        <p><strong>Contact for Data Requests:</strong> For queries or requests concerning your data, contact [Email/Phone].</p>

        <h2>Updates to the Policy</h2>
        <p><strong>Policy Changes:</strong> This policy may undergo updates periodically. We will notify users of significant changes via email or through our website.</p>

        <h2>Compliance Statement</h2>
        <p>[Your Web Application Name] adheres strictly to GDPR compliance standards, ensuring the protection and lawful processing of user data.</p>
        
        <h2>Updates to the Policy</h2>
        <p><strong>Policy Changes:</strong> This policy may undergo updates periodically. We will notify users of significant changes via email or through our website.</p>

        <h2>Compliance Statement</h2>
        <p>[Your Web Application Name] adheres strictly to GDPR compliance standards, ensuring the protection and lawful processing of user data.</p>

    </body>
</html>




<jsp:include page="footer.jsp" />
