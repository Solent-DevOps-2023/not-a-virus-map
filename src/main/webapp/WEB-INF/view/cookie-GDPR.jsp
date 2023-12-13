<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
// request set in controller
//    request.setAttribute("selectedPage","cookie-GDPR");
%>
<jsp:include page="header.jsp" />
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${cookie['userLocale'].value}" />
<fmt:setBundle basename="messages" />

<!-- Begin page content -->
<main role="main" class="container">
    <H1><fmt:message key="label.navbar.about" /></H1>
</main>
<head>
    <title>GDPR Policy for Not a virus map</title>
    <!-- Include any necessary CSS or other metadata -->
</head>
    <body>
        <h1><fmt:message key="label.policy.header" /></h1>
        
        <h2><fmt:message key="label.policy.intro" /></h2>
        <p><fmt:message key="label.policy.intro.text" /></p>
        
        <h2><fmt:message key="label.policy.data-collection" /></h2>
        <h3><fmt:message key="label.policy.data-types" /></h3>
        <ul>
            <li><fmt:message key="label.policy.data-types.personal" /></li>
            <li><fmt:message key="label.policy.data-types.geo" /></li>
            <li><fmt:message key="label.policy.data-types.meta" /></li>
        </ul>
        
        <h3><fmt:message key="label.policy.why" /></h3>
        <ul>
            <li><fmt:message key="label.policy.why.personal" /></li>
            <li><fmt:message key="label.policy.why.geo" /></li>
            <li><fmt:message key="label.policy.why.meta" /></li>
        </ul>
	<h3><fmt:message key="label.policy.lawful-basis" /></h3>
	<ul>
	    <li><fmt:message key="label.policy.lawful-basis.personal" /></li>
	    <li><fmt:message key="label.policy.lawful-basis.geo" /></li>
	    <li><fmt:message key="label.policy.lawful-basis.meta" /></li>
	</ul>

	<h2><fmt:message key="label.user-rights" /></h2>
	<h3><fmt:message key="label.right-to-access" /></h3>
	<p><fmt:message key="label.right-to-access.text" /></p>

	<h3><fmt:message key="label.right-to-rectification" /></h3>
	<p><fmt:message key="label.right-to-rectification.text" /></p>

	<h3><fmt:message key="label.right-to-erasure" /></h3>
	<p><fmt:message key="label.right-to-erasure.text" /></p>

	<h3><fmt:message key="label.right-to-object" /></h3>
	<p><fmt:message key="label.right-to-object.text" /></p>

	<h2><fmt:message key="label.data-security" /></h2>
	<h3><fmt:message key="label.security-practices" /></h3>
	<p><fmt:message key="label.security-practices.text" /></p>
	<p><fmt:message key="label.encryption-storage" /></p>
	<p><fmt:message key="label.encryption-storage.text" /></p>

	<h2><fmt:message key="label.third-party-sharing" /></h2>
	<h3><fmt:message key="label.third-party-services" /></h3>
	<p><fmt:message key="label.third-party-services.text" /></p>
	<p><fmt:message key="label.data-transfer" /></p>

	<h2><fmt:message key="label.cookie-policy" /></h2>
	<h3><fmt:message key="label.cookie-usage" /></h3>
	<p><fmt:message key="label.cookie-usage.text" /></p>
	<p><fmt:message key="label.cookie-consent" /></p>

	<h2><fmt:message key="label.contact-information" /></h2>
	<p><strong><fmt:message key="label.contact-data-requests" />:</strong> <fmt:message key="label.contact-data-requests.text" /></p>

	<h2><fmt:message key="label.policy-updates" /></h2>
	<p><strong><fmt:message key="label.policy-changes" />:</strong> <fmt:message key="label.policy-changes.text" /></p>

	<h2><fmt:message key="label.compliance-statement" /></h2>
	<p><fmt:message key="label.compliance-statement.text" /></p>

	<jsp:include page="footer.jsp" />
    </body>
</html>
