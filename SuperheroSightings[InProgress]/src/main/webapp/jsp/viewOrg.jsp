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
        <title>Viewing Organization</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">    
        <link href="${pageContext.request.contextPath}/css/styles.css?v=1" type="text/css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div align="center">
                <h1>Organization</h1>
            </div>
            <hr/>
            <nav>
                <a href="${pageContext.request.contextPath}/displayHomePage">Home</a>
                <a href="${pageContext.request.contextPath}/displayHeroesPage">Superhero</a>
                <a href="${pageContext.request.contextPath}/displayLocationPage">Locations</a>
                <a href="${pageContext.request.contextPath}/displayOrganizationPage">Organizations</a>
                <a href="${pageContext.request.contextPath}/displaySightingPage">Sightings</a>
                <div class="animation start-org"></div>
            </nav>

            <p style="color:white;">
                <b>Organization Name:</b> <c:out value="${org.orgName}"/>
            </p>
            <p style="color:white;">
                <b>Organization Description:</b> <c:out value="${org.orgDescr}"/>
            </p>
            <p style="color:white;">
                <b>Organization Address:</b> <c:out value="${org.orgAddress}"/>
            </p>
            <p style="color:white;">
                <b>Organization Phone:</b> <c:out value="${org.orgPhone}"/>
            </p>
            <p style="color:white;">
                <b>Organization Email:</b> <c:out value="${org.orgEmail}"/>
            </p>
            <p style="color:white;">
                <b>Organization Heroes:</b>
                <c:forEach var="currentHero" items="${hero}">
                    <c:out value="${currentHero.heroName}"/>
                    <b>,</b>
                </c:forEach>
            </p>
        </div>



        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
