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
    <H1><fmt:message key="label.navbar.policy" /></H1>
</main>
<head>
    <title>GDPR Policy for [Your Web Application Name]</title>
    <!-- Include any necessary CSS or other metadata -->
</head>
    <body>
        <h2>GDPR Policy for Not a Virus Map</h2>
        <br>
        <h3>Introduction</h3>
        <p>Not a Virus Map is dedicated to safeguarding user data, ensuring compliance with the General Data Protection Regulation (GDPR). This policy outlines our approach to collecting, processing, storing, and managing data in line with GDPR guidelines.</p>
        <br>
        <h3>Data Collection and Processing</h3>
        <h1>Types of Data Collected:</h1>
        <ul>
            <li>Personal data: Name, email address, contact information.</li>
            <li>Geolocation data: Usage of location services for mapping features.</li>
            <li>Uploaded metadata: Information added to the map by users.</li>
        </ul>
        
        <h1>Purpose of Data Collection:</h1>
        <ul>
            <li>Personal data: To facilitate login features, communicate updates, and provide personalized services.</li>
            <li>Geolocation data: To enhance user experience with map features and services dependent on location.</li>
            <li>Uploaded metadata: To pin user-provided information to specific locations on the map.</li>
        </ul>
        
        <h1>Lawful Basis for Processing:</h1>
        <ul>
            <li>Personal data: Consent provided during account creation or as necessary for the performance of our services.</li>
            <li>Geolocation data: Consent granted for using location services.</li>
            <li>Uploaded metadata: Consent for storing and displaying user-provided data on the map.</li>
        </ul>
        <br>
        <h3>User Rights</h3>
        <h1>Right to Access:</h1>
        <p>Request access to your personal and geolocation data stored on our servers by contacting 5kanci95@solent.ac.uk</p>
        
        <h1>Right to Rectification:</h1>
        <p>Update or correct your personal information and geolocation data through account settings.</p>
        
        <h1>Right to Erasure:</h1>
        <p>Request deletion of your personal, geolocation data, or uploaded metadata. Contact us to initiate the erasure process.</p>
        
        <h1>Right to Object:</h1>
        <p>Object to specific processing activities related to your data by contacting us.</p>
        <br>
        <h3>Data Security Measures</h3>
        <h1>Data Security Practices:</h1>
        <p>We implement robust security measures to protect all user data, including personal information, geolocation data, and uploaded metadata.</p>
        <p>Access to data is restricted to authorized personnel only.</p>
        
        <h1>Encryption and Storage:</h1>
        <p>Your data, including geolocation information and uploaded metadata, is encrypted and securely stored on external databases.</p>
        <br>

        <h3>Third-Party Data Sharing</h3>
        <h1>Third-Party Services:</h1>
        <p>We use external databases and services to store and manage user data. These providers comply with GDPR regulations.</p>
        <p>Data may be transferred internationally to these external databases for storage and processing.</p>
        <br>


        <a id="cookiesAnchor1"></a>
        <h2>Cookie Policy and Consent</h2>
        <br>
        <h3>Cookie Usage:</h3>
        <p>We use cookies for improved website functionality and user experience. Refer to our Cookie Policy for details.</p>
        <p>By using our web application, you consent to our use of cookies.</p>
        <br>
        <h3>Contact Information</h3>
        <p><strong>Data Protection Officer:</strong> [Igor K., 5kanci95@solent.ac.uk]</p>
        <p><strong>Contact for Data Requests:</strong> For queries or requests concerning your data, contact 5kanci95@solent.ac.uk.</p>

        <h3>Updates to the Policy</h3>
        <p><strong>Policy Changes:</strong> This policy may undergo updates periodically. We will notify users of significant changes via email or through our website.</p>

        <h3>Compliance Statement</h3>
        <p>Not a Virus Map adheres strictly to GDPR compliance standards, ensuring the protection and lawful processing of user data.</p>
        
        <h3>Updates to the Policy</h3>
        <p><strong>Policy Changes:</strong> This policy may undergo updates periodically. We will notify users of significant changes via email or through our website.</p>

        <h3>Compliance Statement</h3>
        <p>Not a Virus Map adheres strictly to GDPR compliance standards, ensuring the protection and lawful processing of user data.</p>

    </body>
</html>




<jsp:include page="footer.jsp" />
