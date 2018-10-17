<%-- 
    Author     : Chad
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Viewing Sighting</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">  
        <link href="${pageContext.request.contextPath}/css/styles.css?v=1" type="text/css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div align="center">
                <h1>Superhero Sightings</h1>
            </div>
            <hr/>
            <nav>
                <a href="${pageContext.request.contextPath}/displayHomePage">Home</a>
                <a href="${pageContext.request.contextPath}/displayHeroesPage">Superhero</a>
                <a href="${pageContext.request.contextPath}/displayLocationPage">Locations</a>
                <a href="${pageContext.request.contextPath}/displayOrganizationPage">Organizations</a>
                <a href="${pageContext.request.contextPath}/displaySightingPage">Sightings</a>
                <div class="animation start-sighting"></div>
            </nav>
            <p style="color:white;">
                <b>Date of Sighting:</b>
                <fmt:parseDate value="${sightingDate}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
                <fmt:formatDate value="${parsedDate}" var="newParsedDate" type="date" pattern="dd/MM/yyyy" />
                <c:out value="${newParsedDate}"/>
            </p>
            <p style="color:white;">
                <b>Location of Sighting:</b> <c:out value="${location.locationName}"/>
            </p>
            <p style="color:white;">
                <b>Superhero Sighted:</b> <c:out value="${hero.heroName}"/>
            </p>
            <p style="color:white;">
                <b>Sighting Picture:</b>
            </p>
        </div>


        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
