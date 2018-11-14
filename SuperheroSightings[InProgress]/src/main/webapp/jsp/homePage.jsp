<%-- 
    Author     : Chad
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Home</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">  
        <link href="${pageContext.request.contextPath}/css/styles.css?v=8" type="text/css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div align="center">
                <h1>Hero Education and Relationship Organization</h1>
            </div>
            <hr/>
            <nav>
                <a href="${pageContext.request.contextPath}/displayHomePage">Home</a>
                <a href="${pageContext.request.contextPath}/displayHeroesPage">Superhero</a>
                <a href="${pageContext.request.contextPath}/displayLocationPage">Locations</a>
                <a href="${pageContext.request.contextPath}/displayOrganizationPage">Organizations</a>
                <a href="${pageContext.request.contextPath}/displaySightingPage">Sightings</a>
                <div class="animation start-home"></div>
            </nav>

            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <p>Hello : ${pageContext.request.userPrincipal.name}
                    | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                </p>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <a href="${pageContext.request.contextPath}/displayUserList">User Admin Tools</a>
                </sec:authorize>
            </c:if>

            <h2>Latest Reported Sightings</h2>
            <div id="lastTen">
                <c:forEach var="currentSighting" items="${sightingList}">
                    <div class="item">

                        <a href="displaySightingDetails?sightingId=${currentSighting.sightingId}">
                            <c:out value="Sighting Report #${currentSighting.sightingId}"/>
                        </a>
                        <br/> Reported on 
                        <fmt:parseDate value="${currentSighting.sightingDate}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
                        <fmt:formatDate value="${parsedDate}" var="newParsedDate" type="date" pattern="MM/dd/yyyy" />
                        <c:out value="${newParsedDate}"/>
                    </div>
                </c:forEach>
            </div>
            <h2>About Us</h2>
            <p>
                Welcome to the Hero Education and Relationship Organization, otherwise known as "HERO." </br>
                On our website, you can view all types of information on Superheroes documented in our database.</br>
                Non-users may only <i>view</i> our Superheroes/Supervillains. </br>
                Users may only <i>view</i> all categories.
            </p>

            <div align="center">
                <p class="footer">Chad</p>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>

